package escapeRoom;

<<<<<<< HEAD
import escapeRoom.Inventory; // Import the Inventory class
=======
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
import java.util.Set;
=======
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d

public class Library extends JFrame {

    private JButton searchBookshelfBtn;
    private JButton checkDeskBtn;
    private JButton examinePaintingBtn;
    private JButton inspectPaperBtn;
    private JButton translateSymbolsBtn;
    private JButton tryOpeningDrawerBtn;
    private JButton returnToCenterBtn;
    private JButton readInscriptionBtn;
    private JButton lookForMissingDigitsBtn;
    private JButton enterCodeBtn;
<<<<<<< HEAD
    private JButton viewInventoryBtn;  // New inventory button
=======
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
    private JTextArea storyArea;

    private String partialCode = ""; // from bookshelf: "8251"
    private String inscriptionCodePart = ""; // from painting: "36??"
    private boolean partialCodeFound = false;
    private boolean inscriptionFound = false;
    private EscapeRoomApp mainApp;
    private boolean hasJailKey = false;

    private Image backgroundImage;
    private Clip backgroundClip;

<<<<<<< HEAD
    private Inventory inventory; // Inventory instance

    public Library(EscapeRoomApp mainApp) {
        this.mainApp = mainApp;
        this.inventory = new Inventory(); // Initialize inventory
=======
    public Library(EscapeRoomApp mainApp) {
        this.mainApp = mainApp;
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d

        setTitle("Library Escape");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/library_bg.jpg"));
        } catch (IOException e) {
            System.err.println("Library background image not found.");
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
        storyArea.setText("You're now inside the eerie old library. Dusty bookshelves line the walls, and faint whispers seem to echo from nowhere. You need to find the 4-digit code to unlock the main door and escape.");
        JScrollPane scrollPane = new JScrollPane(storyArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
<<<<<<< HEAD
        buttonPanel.setLayout(new GridLayout(11, 1, 10, 10));
=======
        buttonPanel.setLayout(new GridLayout(10, 1, 10, 10));
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        // Buttons
        searchBookshelfBtn = new JButton("Search bookshelf labeled 'Ancient Texts'");
        checkDeskBtn = new JButton("Check librarian’s desk");
        examinePaintingBtn = new JButton("Examine painting on the wall");
        inspectPaperBtn = new JButton("Inspect the fallen paper");
        translateSymbolsBtn = new JButton("Translate symbols");
        tryOpeningDrawerBtn = new JButton("Try opening drawer");
        returnToCenterBtn = new JButton("Return to center of room");
        readInscriptionBtn = new JButton("Read the inscription");
        lookForMissingDigitsBtn = new JButton("Look for missing digits");
        enterCodeBtn = new JButton("Enter code to unlock door");
<<<<<<< HEAD
        viewInventoryBtn = new JButton("View Inventory");  // new button
=======
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d

        // Initial visibility
        inspectPaperBtn.setVisible(false);
        translateSymbolsBtn.setVisible(false);
        tryOpeningDrawerBtn.setVisible(false);
        returnToCenterBtn.setVisible(false);
        readInscriptionBtn.setVisible(false);
        lookForMissingDigitsBtn.setVisible(false);
        enterCodeBtn.setVisible(false);
<<<<<<< HEAD
        // Inventory button always visible
        viewInventoryBtn.setVisible(true);
=======
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d

        buttonPanel.add(searchBookshelfBtn);
        buttonPanel.add(checkDeskBtn);
        buttonPanel.add(examinePaintingBtn);
        buttonPanel.add(inspectPaperBtn);
        buttonPanel.add(translateSymbolsBtn);
        buttonPanel.add(tryOpeningDrawerBtn);
        buttonPanel.add(returnToCenterBtn);
        buttonPanel.add(readInscriptionBtn);
        buttonPanel.add(lookForMissingDigitsBtn);
        buttonPanel.add(enterCodeBtn);
<<<<<<< HEAD
        buttonPanel.add(viewInventoryBtn);
=======
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);

        // --- Action Listeners ---

        searchBookshelfBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You find a book titled \"Secrets of the Forgotten.\" A paper slips out as you open it: “Knowledge lies in numbers.”");
            inspectPaperBtn.setVisible(true);
<<<<<<< HEAD
            hideButtonsExcept(inspectPaperBtn, viewInventoryBtn);
            inventory.addItem("Secrets of the Forgotten"); // Add item to inventory
=======
            hideButtonsExcept(inspectPaperBtn);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
        });

        inspectPaperBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("The paper has cryptic symbols: “VIII – II – V – I”.");
            translateSymbolsBtn.setVisible(true);
<<<<<<< HEAD
            hideButtonsExcept(translateSymbolsBtn, viewInventoryBtn);
=======
            hideButtonsExcept(translateSymbolsBtn);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
        });

        translateSymbolsBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You translate the Roman numerals: 8 – 2 – 5 – 1. These might be part of a code.");
            partialCode = "8251";
            partialCodeFound = true;
            returnToCenterBtn.setVisible(true);
<<<<<<< HEAD
            hideButtonsExcept(returnToCenterBtn, viewInventoryBtn);
            inventory.addItem("Code fragment 8251");
=======
            hideButtonsExcept(returnToCenterBtn);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
        });

        checkDeskBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You find a locked drawer and a half-burnt note: “Not all books are for reading.”");
            tryOpeningDrawerBtn.setVisible(true);
<<<<<<< HEAD
            hideButtonsExcept(tryOpeningDrawerBtn, returnToCenterBtn, viewInventoryBtn);
=======
            hideButtonsExcept(tryOpeningDrawerBtn, returnToCenterBtn);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
        });

        tryOpeningDrawerBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("The drawer is locked tight. You need a key or a code.");
            if(partialCodeFound && inscriptionFound){
                enterCodeBtn.setVisible(true);
<<<<<<< HEAD
                hideButtonsExcept(enterCodeBtn, viewInventoryBtn);
            }
            else {
                returnToCenterBtn.setVisible(true);
                hideButtonsExcept(returnToCenterBtn, viewInventoryBtn);
=======
                hideButtonsExcept(enterCodeBtn);
            }
            else {
                returnToCenterBtn.setVisible(true);
                hideButtonsExcept(returnToCenterBtn);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
            }
        });

        examinePaintingBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You notice the painting is slightly ajar... Behind it is a dusty mirror and an inscription.");
            readInscriptionBtn.setVisible(true);
<<<<<<< HEAD
            hideButtonsExcept(readInscriptionBtn, viewInventoryBtn);
=======
            hideButtonsExcept(readInscriptionBtn);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
        });

        readInscriptionBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("“Four digits unlock what knowledge binds.” Underneath, someone scribbled: 3, 6, ?, ?");
            inscriptionFound = true;
            inscriptionCodePart = "36";
            lookForMissingDigitsBtn.setVisible(true);
<<<<<<< HEAD
            hideButtonsExcept(lookForMissingDigitsBtn, viewInventoryBtn);
            inventory.addItem("Inscription hint 36??");
=======
            hideButtonsExcept(lookForMissingDigitsBtn);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
        });

        lookForMissingDigitsBtn.addActionListener(e -> {
            playSound("click.wav", false);
            if (partialCodeFound) {
                setStory("You match the codes: 3, 6, " + partialCode.charAt(2) + ", " + partialCode.charAt(3) +
                        ". That gives you 3 6 5 1. Try this?");
<<<<<<< HEAD
=======
                //enterCodeBtn.setVisible(true);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
            } else {
                setStory("You’re missing some digits. Maybe check the bookshelf?");
            }
            returnToCenterBtn.setVisible(true);
<<<<<<< HEAD
            hideButtonsExcept(returnToCenterBtn, viewInventoryBtn);
=======
            hideButtonsExcept(returnToCenterBtn);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
        });

        enterCodeBtn.addActionListener(e -> {
            playSound("click.wav", false);
            String input = JOptionPane.showInputDialog(this, "Enter the 4-digit code:");
            if (input != null && input.equals("3651")) {
                setStory("✅ The drawer opens. You've found the key to escape the Library!");
                JOptionPane.showMessageDialog(this, "You unlocked Jail Escape level. Return to the hallway to proceed.", "Key Acquired", JOptionPane.INFORMATION_MESSAGE);
                if (!hasJailKey) {
                    hasJailKey = true;
<<<<<<< HEAD
                    inventory.addItem("Jail Key"); // Add key to inventory
=======
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
                    if (mainApp != null) {
                        mainApp.jailKeyObtained();
                    }
                }
                dispose();
<<<<<<< HEAD
=======
                //mainApp.returnToMainMenu(); // Optional hook back to main menu
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
            } else {
                JOptionPane.showMessageDialog(this, "❌ The code is incorrect. Try again!");
            }
        });

        returnToCenterBtn.addActionListener(e -> {
            setStoryInitial();
            resetButtons();
        });

<<<<<<< HEAD
        viewInventoryBtn.addActionListener(e -> {
            showInventory();
        });

        // Initial display
        setStoryInitial();
        resetButtons();
=======
        // Initial display
        setStoryInitial();
        resetButtons();
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

    // Stop background music
    private void stopBackgroundSound() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
            backgroundClip = null;
        }
    }
    // Helper methods

    private void setStory(String text) {
        storyArea.setText(text);
    }

    private void setStoryInitial() {
        setStory("You're now inside the eerie old library. Dusty bookshelves line the walls, and faint whispers seem to echo from nowhere. You need to find the 4-digit code to unlock the main door and escape.\n\nChoose where to look:");
    }

    private void resetButtons() {
        searchBookshelfBtn.setVisible(true);
        checkDeskBtn.setVisible(true);
        examinePaintingBtn.setVisible(true);
        inspectPaperBtn.setVisible(false);
        translateSymbolsBtn.setVisible(false);
        tryOpeningDrawerBtn.setVisible(false);
        returnToCenterBtn.setVisible(false);
        readInscriptionBtn.setVisible(false);
        lookForMissingDigitsBtn.setVisible(false);
        enterCodeBtn.setVisible(false);
    }

    private void hideButtonsExcept(JButton... buttonsToKeep) {
        JButton[] allButtons = {
                searchBookshelfBtn, checkDeskBtn, examinePaintingBtn,
                inspectPaperBtn, translateSymbolsBtn, tryOpeningDrawerBtn,
                returnToCenterBtn, readInscriptionBtn, lookForMissingDigitsBtn, enterCodeBtn
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
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
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

    // Stop background music
    private void stopBackgroundSound() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
            backgroundClip = null;
        }
    }
    // Helper methods

    private void setStory(String text) {
        storyArea.setText(text);
    }

    private void setStoryInitial() {
        setStory("You're now inside the eerie old library. Dusty bookshelves line the walls, and faint whispers seem to echo from nowhere. You need to find the 4-digit code to unlock the main door and escape.\n\nChoose where to look:");
    }

    private void resetButtons() {
        searchBookshelfBtn.setVisible(true);
        checkDeskBtn.setVisible(true);
        examinePaintingBtn.setVisible(true);
        inspectPaperBtn.setVisible(false);
        translateSymbolsBtn.setVisible(false);
        tryOpeningDrawerBtn.setVisible(false);
        returnToCenterBtn.setVisible(false);
        readInscriptionBtn.setVisible(false);
        lookForMissingDigitsBtn.setVisible(false);
        enterCodeBtn.setVisible(false);
        viewInventoryBtn.setVisible(true);
    }

    private void hideButtonsExcept(JButton... buttonsToKeep) {
        JButton[] allButtons = {
                searchBookshelfBtn, checkDeskBtn, examinePaintingBtn,
                inspectPaperBtn, translateSymbolsBtn, tryOpeningDrawerBtn,
                returnToCenterBtn, readInscriptionBtn, lookForMissingDigitsBtn, enterCodeBtn,
                viewInventoryBtn
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

