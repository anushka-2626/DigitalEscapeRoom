package escapeRoom;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

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
    private JTextArea storyArea;

    private String partialCode = ""; // from bookshelf: "8251"
    private String inscriptionCodePart = ""; // from painting: "36??"
    private boolean partialCodeFound = false;
    private boolean inscriptionFound = false;
    private EscapeRoomApp mainApp;
    private boolean hasJailKey = false;

    private Image backgroundImage;
    private Clip backgroundClip;

    public Library(EscapeRoomApp mainApp) {
        this.mainApp = mainApp;

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
        buttonPanel.setLayout(new GridLayout(10, 1, 10, 10));
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

        // Initial visibility
        inspectPaperBtn.setVisible(false);
        translateSymbolsBtn.setVisible(false);
        tryOpeningDrawerBtn.setVisible(false);
        returnToCenterBtn.setVisible(false);
        readInscriptionBtn.setVisible(false);
        lookForMissingDigitsBtn.setVisible(false);
        enterCodeBtn.setVisible(false);

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

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);

        // --- Action Listeners ---

        searchBookshelfBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You find a book titled \"Secrets of the Forgotten.\" A paper slips out as you open it: “Knowledge lies in numbers.”");
            inspectPaperBtn.setVisible(true);
            hideButtonsExcept(inspectPaperBtn);
        });

        inspectPaperBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("The paper has cryptic symbols: “VIII – II – V – I”.");
            translateSymbolsBtn.setVisible(true);
            hideButtonsExcept(translateSymbolsBtn);
        });

        translateSymbolsBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You translate the Roman numerals: 8 – 2 – 5 – 1. These might be part of a code.");
            partialCode = "8251";
            partialCodeFound = true;
            returnToCenterBtn.setVisible(true);
            hideButtonsExcept(returnToCenterBtn);
        });

        checkDeskBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You find a locked drawer and a half-burnt note: “Not all books are for reading.”");
            tryOpeningDrawerBtn.setVisible(true);
            hideButtonsExcept(tryOpeningDrawerBtn, returnToCenterBtn);
        });

        tryOpeningDrawerBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("The drawer is locked tight. You need a key or a code.");
            if(partialCodeFound && inscriptionFound){
                enterCodeBtn.setVisible(true);
                hideButtonsExcept(enterCodeBtn);
            }
            else {
                returnToCenterBtn.setVisible(true);
                hideButtonsExcept(returnToCenterBtn);
            }
        });

        examinePaintingBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("You notice the painting is slightly ajar... Behind it is a dusty mirror and an inscription.");
            readInscriptionBtn.setVisible(true);
            hideButtonsExcept(readInscriptionBtn);
        });

        readInscriptionBtn.addActionListener(e -> {
            playSound("click.wav", false);
            setStory("“Four digits unlock what knowledge binds.” Underneath, someone scribbled: 3, 6, ?, ?");
            inscriptionFound = true;
            inscriptionCodePart = "36";
            lookForMissingDigitsBtn.setVisible(true);
            hideButtonsExcept(lookForMissingDigitsBtn);
        });

        lookForMissingDigitsBtn.addActionListener(e -> {
            playSound("click.wav", false);
            if (partialCodeFound) {
                setStory("You match the codes: 3, 6, " + partialCode.charAt(2) + ", " + partialCode.charAt(3) +
                        ". That gives you 3 6 5 1. Try this?");
                //enterCodeBtn.setVisible(true);
            } else {
                setStory("You’re missing some digits. Maybe check the bookshelf?");
            }
            returnToCenterBtn.setVisible(true);
            hideButtonsExcept(returnToCenterBtn);
        });

        enterCodeBtn.addActionListener(e -> {
            playSound("click.wav", false);
            String input = JOptionPane.showInputDialog(this, "Enter the 4-digit code:");
            if (input != null && input.equals("3651")) {
                setStory("✅ The drawer opens. You've found the key to escape the Library!");
                JOptionPane.showMessageDialog(this, "You unlocked Jail Escape level. Return to the hallway to proceed.", "Key Acquired", JOptionPane.INFORMATION_MESSAGE);
                if (!hasJailKey) {
                    hasJailKey = true;
                    if (mainApp != null) {
                        mainApp.jailKeyObtained();
                    }
                }
                dispose();
                //mainApp.returnToMainMenu(); // Optional hook back to main menu
            } else {
                JOptionPane.showMessageDialog(this, "❌ The code is incorrect. Try again!");
            }
        });

        returnToCenterBtn.addActionListener(e -> {
            setStoryInitial();
            resetButtons();
        });

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
    }
}
