package escapeRoom;

import escapeRoom.Inventory;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Jail extends JFrame {

    private JButton searchCellBtn;
    private JButton checkToiletBtn;
    private JButton examineBarsBtn;
    private JButton inspectWallBtn;
    private JButton digSpotBtn;
    private JButton tryLockerBtn;
    private JButton tryOpeningDoorBtn;
    private JButton returnToCenterBtn;
    private JButton readNoteBtn;
    private JButton lookForClueBtn;
    private JButton enterCodeBtn;
    private JButton viewInventoryBtn;
    private JTextArea storyArea;

    // Puzzle states
    private boolean noteFound = false;
    private boolean spoonFound = false;
    private boolean lockerUnlocked = false;
    private boolean masterKeyFound = false;

    private EscapeRoomApp mainApp;

    private Image backgroundImage;
    private Clip backgroundClip;

    private Inventory inventory;

    // For symbol puzzle
    private final String[] symbolSequence = {"Δ", "☉", "♢", "▲"};  // correct order
    private final HashSet<String> symbolSet = new HashSet<>();
    private JButton[] symbolButtons;
    private StringBuilder chosenSequence = new StringBuilder();

    public Jail(EscapeRoomApp mainApp) {
        this.mainApp = mainApp;
        this.inventory = new Inventory();

        setTitle("Jail Escape");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/jail_bg.jpg"));
        } catch (IOException e) {
            System.err.println("Jail background image not found.");
            backgroundImage = null;
        }

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setOpaque(false);

        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setFont(new Font("Serif", Font.PLAIN, 20));
        storyArea.setOpaque(false);
        storyArea.setForeground(Color.WHITE);
        storyArea.setText("You're trapped in a dark, damp jail cell. Find a way to escape by searching and solving puzzles.");
        JScrollPane scrollPane = new JScrollPane(storyArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(13, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        searchCellBtn = new JButton("Search the cell for clues");
        checkToiletBtn = new JButton("Check the toilet");
        examineBarsBtn = new JButton("Examine the cell bars");
        inspectWallBtn = new JButton("Inspect the wall");
        digSpotBtn = new JButton("Dig ground spot (needs Spoon)");
        tryLockerBtn = new JButton("Try opening the underground locker");
        tryOpeningDoorBtn = new JButton("Try opening the door");
        returnToCenterBtn = new JButton("Return to center of cell");
        readNoteBtn = new JButton("Read the note on the floor");
        lookForClueBtn = new JButton("Look for more clues");
        enterCodeBtn = new JButton("Enter code to unlock door");
        viewInventoryBtn = new JButton("View Inventory");

        // Initially invisible buttons
        checkToiletBtn.setVisible(false);
        examineBarsBtn.setVisible(false);
        inspectWallBtn.setVisible(false);
        digSpotBtn.setVisible(false);
        tryLockerBtn.setVisible(false);
        tryOpeningDoorBtn.setVisible(false);
        returnToCenterBtn.setVisible(false);
        readNoteBtn.setVisible(false);
        lookForClueBtn.setVisible(false);
        enterCodeBtn.setVisible(false);

        buttonPanel.add(searchCellBtn);
        buttonPanel.add(checkToiletBtn);
        buttonPanel.add(examineBarsBtn);
        buttonPanel.add(inspectWallBtn);
        buttonPanel.add(digSpotBtn);
        buttonPanel.add(tryLockerBtn);
        buttonPanel.add(tryOpeningDoorBtn);
        buttonPanel.add(returnToCenterBtn);
        buttonPanel.add(readNoteBtn);
        buttonPanel.add(lookForClueBtn);
        buttonPanel.add(enterCodeBtn);
        buttonPanel.add(viewInventoryBtn);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);

        // Action Listeners

        searchCellBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You find an old, rusted plate with a spoon on it. Maybe you can take the spoon.");
            if (!spoonFound) {
                inventory.addItem("Spoon");
                spoonFound = true;
                JOptionPane.showMessageDialog(this, "You took the Spoon and added it to your inventory.", "Item Acquired", JOptionPane.INFORMATION_MESSAGE);
            }
            readNoteBtn.setVisible(true);
            hideButtonsExcept(readNoteBtn, viewInventoryBtn);
        });

        readNoteBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("The note says: 'The sequence of the symbols Δ, ☉, ♢, ▲ unlocks what lies beneath.'");
            noteFound = true;
            inspectWallBtn.setVisible(true);
            lookForClueBtn.setVisible(true);
            hideButtonsExcept(inspectWallBtn, lookForClueBtn, viewInventoryBtn);
            inventory.addItem("Mysterious Note");
        });

        inspectWallBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("On the wall, you see strange symbols drawn in a sequence: Δ, ☉, ♢, ▲. They seem important.");
            digSpotBtn.setVisible(spoonFound);
            lookForClueBtn.setVisible(true);
            hideButtonsExcept(digSpotBtn, lookForClueBtn, viewInventoryBtn);
        });

        lookForClueBtn.addActionListener(e -> {
            playSound("click.wav", false);
            if (!noteFound) {
                setStory("You don't find anything new. Maybe read the note again?");
                readNoteBtn.setVisible(true);
                hideButtonsExcept(readNoteBtn, viewInventoryBtn);
            } else {
                setStory("Remember the symbol sequence on the wall: Δ, ☉, ♢, ▲. It might open something hidden.");
                digSpotBtn.setVisible(spoonFound);
                hideButtonsExcept(digSpotBtn, viewInventoryBtn);
            }
        });

        digSpotBtn.addActionListener(e -> {
            playSound("click.wav", false);
            if (!spoonFound) {
                setStory("You don't have anything to dig with. Maybe look around for something?");
                hideButtonsExcept(viewInventoryBtn);
                return;
            }
            setStory("Using the spoon, you dig a spot on the ground and find a locked underground locker with symbols on its keypad.");
            tryLockerBtn.setVisible(true);
            hideButtonsExcept(tryLockerBtn, returnToCenterBtn, viewInventoryBtn);
        });

        tryLockerBtn.addActionListener(e -> {
            playSound("click.wav", false);
            if (lockerUnlocked) {
                setStory("The locker is already open. You took the Master Key.");
                hideButtonsExcept(returnToCenterBtn, viewInventoryBtn);
                return;
            }
            // Show symbol puzzle dialog
            showSymbolPuzzle();
        });

        tryOpeningDoorBtn.addActionListener(e -> {
            playSound("click.wav", false);
            if (masterKeyFound) {
                setStory("You use the master key to unlock the door. It creaks open.");
                JOptionPane.showMessageDialog(this, "You've escaped the jail! Congratulations!", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (mainApp != null) {
                    //mainApp.jailEscapeCompleted();
                }
                dispose();
            } else if (noteFound) {
                setStory("The door is locked tight. You either need a key or solve the locker puzzle underground.");
                digSpotBtn.setVisible(spoonFound);
                hideButtonsExcept(digSpotBtn, viewInventoryBtn);
            } else {
                setStory("The door is locked tight. Look around for clues.");
                searchCellBtn.setVisible(true);
                hideButtonsExcept(searchCellBtn, viewInventoryBtn);
            }
        });

        returnToCenterBtn.addActionListener(e -> {
            setStoryInitial();
            resetButtons();
        });

        viewInventoryBtn.addActionListener(e -> {
            showInventory();
        });

        // Initial display
        setStoryInitial();
        resetButtons();
    }

    private void showSymbolPuzzle() {
        JDialog puzzleDialog = new JDialog(this, "Symbol Puzzle - Enter the sequence", true);
        puzzleDialog.setSize(400, 250);
        puzzleDialog.setLocationRelativeTo(this);
        puzzleDialog.setLayout(new BorderLayout());

        JLabel instructions = new JLabel("<html>Click the symbols in the correct order to unlock the locker.<br/>Symbols: Δ, ☉, ♢, ▲</html>", SwingConstants.CENTER);
        instructions.setFont(new Font("Serif", Font.BOLD, 16));
        puzzleDialog.add(instructions, BorderLayout.NORTH);

        JPanel symbolsPanel = new JPanel();
        symbolsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        symbolSet.clear();
        chosenSequence.setLength(0);

        symbolButtons = new JButton[symbolSequence.length];
        for (int i = 0; i < symbolSequence.length; i++) {
            String symbol = symbolSequence[i];
            JButton btn = new JButton(symbol);
            btn.setFont(new Font("Serif", Font.BOLD, 32));
            btn.setPreferredSize(new Dimension(60, 60));
            btn.addActionListener(e -> {
                if (symbolSet.contains(symbol)) {
                    // symbol already chosen
                    return;
                }
                symbolSet.add(symbol);
                chosenSequence.append(symbol);
                btn.setEnabled(false);
                if (symbolSet.size() == symbolSequence.length) {
                    // Check solution
                    puzzleDialog.dispose();
                    checkSymbolSolution();
                }
            });
            symbolsPanel.add(btn);
            symbolButtons[i] = btn;
        }

        puzzleDialog.add(symbolsPanel, BorderLayout.CENTER);

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> {
            symbolSet.clear();
            chosenSequence.setLength(0);
            for (JButton b : symbolButtons) {
                b.setEnabled(true);
            }
        });
        puzzleDialog.add(resetBtn, BorderLayout.SOUTH);

        puzzleDialog.setVisible(true);
    }

    private void checkSymbolSolution() {
        String solution = String.join("", symbolSequence);
        if (chosenSequence.toString().equals(solution)) {
            setStory("✅ The locker clicks open! Inside you find the Master Key.");
            JOptionPane.showMessageDialog(this, "You obtained the Master Key to escape!", "Locker Unlocked", JOptionPane.INFORMATION_MESSAGE);
            lockerUnlocked = true;
            masterKeyFound = true;
            inventory.addItem("Master Key");
            tryOpeningDoorBtn.setVisible(true);
            hideButtonsExcept(tryOpeningDoorBtn, returnToCenterBtn, viewInventoryBtn);
        } else {
            setStory("❌ Wrong symbol sequence. The locker remains locked. Try again.");
            tryLockerBtn.setVisible(true);
            hideButtonsExcept(tryLockerBtn, returnToCenterBtn, viewInventoryBtn);
        }
    }

    private void showInventory() {
        Set<String> items = inventory.getItems();
        StringBuilder itemList = new StringBuilder();
        if (items.isEmpty()) {
            itemList.append("Your inventory is empty.");
        } else {
            for (String item : items) {
                itemList.append("- ").append(item).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, itemList.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }

    private void playSound(String soundFileName, boolean loop) {
        try {
            URL soundURL = getClass().getResource("/sounds/" + soundFileName);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + soundFileName);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                backgroundClip = clip;
            }
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopBackgroundSound() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
            backgroundClip = null;
        }
    }

    private void setStory(String text) {
        storyArea.setText(text);
    }

    private void setStoryInitial() {
        setStory("You're trapped in a dark, damp jail cell. Find a way to escape by searching and solving puzzles.\n\nChoose where to look:");
    }

    private void resetButtons() {
        searchCellBtn.setVisible(true);
        checkToiletBtn.setVisible(false);
        examineBarsBtn.setVisible(false);
        inspectWallBtn.setVisible(false);
        digSpotBtn.setVisible(false);
        tryLockerBtn.setVisible(false);
        tryOpeningDoorBtn.setVisible(false);
        returnToCenterBtn.setVisible(false);
        readNoteBtn.setVisible(false);
        lookForClueBtn.setVisible(false);
        enterCodeBtn.setVisible(false);
        viewInventoryBtn.setVisible(true);
    }

    private void hideButtonsExcept(JButton... buttonsToKeep) {
        JButton[] allButtons = {
                searchCellBtn, checkToiletBtn, examineBarsBtn,
                inspectWallBtn, digSpotBtn, tryLockerBtn,
                tryOpeningDoorBtn, returnToCenterBtn, readNoteBtn,
                lookForClueBtn, enterCodeBtn, viewInventoryBtn
        };

        for (JButton btn : allButtons) {
            boolean keep = false;
            for (JButton btk : buttonsToKeep) {
                if (btn == btk) {
                    keep = true;
                    break;
                }
            }
            btn.setVisible(keep);
        }
    }

}

