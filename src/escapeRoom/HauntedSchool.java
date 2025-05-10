package escapeRoom;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * HauntedSchool class with background music and click sound effects only.
 */
public class HauntedSchool extends JFrame {

    private JButton readNoteBtn;
    private JButton seeNoticeBoardBtn;
    private JButton checkDrawerBtn;
    private JButton checkBlackboardBtn;
    private JButton answerRiddleBtn;
    private JButton inspectTornPageBtn;
    private JButton checkCalendarBtn;
    private JButton goBackBtn;
    private JTextArea storyArea;
    private boolean hasLibraryKey = false;

    private Image backgroundImage;
    private EscapeRoomApp mainApp;
    private Clip backgroundClip;

    public HauntedSchool(EscapeRoomApp mainApp) {
        this.mainApp = mainApp;

        setTitle("Haunted School");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load background image
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/haunted_school_bg.jpg"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Haunted School background image not found.");
            backgroundImage = null;
        }

        // Background panel with image painting
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
        storyArea.setText("Welcome to the Haunted School!\nChoose your path to find the Library Key.");
        JScrollPane scrollPane = new JScrollPane(storyArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(8, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        // Buttons
        readNoteBtn = new JButton("Read note on desk");
        seeNoticeBoardBtn = new JButton("See notice board");
        checkDrawerBtn = new JButton("Check teacher’s drawer");
        checkBlackboardBtn = new JButton("See blackboard");
        answerRiddleBtn = new JButton("Answer the riddle");
        inspectTornPageBtn = new JButton("Inspect torn page");
        checkCalendarBtn = new JButton("Check behind calendar");
        goBackBtn = new JButton("Go back");

        // Initial button visibility
        checkBlackboardBtn.setVisible(false);
        answerRiddleBtn.setVisible(false);
        inspectTornPageBtn.setVisible(false);
        checkCalendarBtn.setVisible(false);
        goBackBtn.setVisible(false);

        buttonPanel.add(readNoteBtn);
        buttonPanel.add(seeNoticeBoardBtn);
        buttonPanel.add(checkDrawerBtn);
        buttonPanel.add(checkBlackboardBtn);
        buttonPanel.add(answerRiddleBtn);
        buttonPanel.add(inspectTornPageBtn);
        buttonPanel.add(checkCalendarBtn);
        buttonPanel.add(goBackBtn);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);

        // Play spooky background ambience looped
        playSound("spooky_ambience.wav", true);

        // Stop background music on window close
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                stopBackgroundSound();
            }
        });

        // Button listeners with click sound on press
        readNoteBtn.addActionListener(e -> {
            playSound("click.wav", false);
            storyArea.setText("You read a dusty note on the desk:\n'The key is hidden where knowledge is shared.' Perhaps you should check the blackboard.");
            clearButtons();
            checkBlackboardBtn.setVisible(true);
        });

        checkBlackboardBtn.addActionListener(e -> {
            playSound("click.wav", false);
            storyArea.setText("You see a riddle written in chalk:\n'What has keys but can't open locks?'");
            clearButtons();
            answerRiddleBtn.setVisible(true);
        });

        answerRiddleBtn.addActionListener(e -> {
            playSound("click.wav", false);
            String answer = JOptionPane.showInputDialog(this, "Your answer to the riddle?");
            if (answer != null && answer.trim().equalsIgnoreCase("piano")) {
                if (!hasLibraryKey) {
                    hasLibraryKey = true;
                    if (mainApp != null) {
                        mainApp.keyObtained();
                    }
                }
                storyArea.setText("Correct! You found the Library Key in a hollow behind the blackboard.\n\nYou may now proceed to the Library.");
                clearButtons();
                goBackBtn.setVisible(true);
                this.dispose(); // Close window after key obtained
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect. Try again!", "Wrong Answer", JOptionPane.ERROR_MESSAGE);
            }
        });

        seeNoticeBoardBtn.addActionListener(e -> {
            playSound("click.wav", false);
            storyArea.setText("The notice board is filled with faded announcements. One torn page sticks out.");
            clearButtons();
            inspectTornPageBtn.setVisible(true);
            goBackBtn.setVisible(true);
        });

        inspectTornPageBtn.addActionListener(e -> {
            playSound("click.wav", false);
            storyArea.setText("You unfold the page and read a cryptic message:\n'The principal always locked it beneath the calendar.'");
            clearButtons();
            checkCalendarBtn.setVisible(true);
            goBackBtn.setVisible(true);
        });

        checkCalendarBtn.addActionListener(e -> {
            playSound("click.wav", false);
            if (!hasLibraryKey) {
                hasLibraryKey = true;
                if (mainApp != null) {
                    mainApp.keyObtained();
                }
            }
            storyArea.setText("You find a small compartment behind the calendar. Inside it is... the Library Key!\n\nYou may now proceed to the Library.");
            clearButtons();
            goBackBtn.setVisible(true);
            this.dispose(); // Close window after key obtained
        });

        checkDrawerBtn.addActionListener(e -> {
            playSound("click.wav", false);
            storyArea.setText("You try to open the drawer... it's stuck. After a bit of force, it opens—but it's empty.");
            clearButtons();
            goBackBtn.setVisible(true);
        });

        goBackBtn.addActionListener(e -> {
            playSound("click.wav", false);
            if (hasLibraryKey) {
                storyArea.setText("✅ Correct Answer. You've found the key to escape the Classroom!");
                JOptionPane.showMessageDialog(this, "You unlocked Library Escape Level. Return to the hallway to proceed.", "Key Acquired", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close window to return to main menu
            } else {
                storyArea.setText("Welcome back! Choose your path to find the Library Key.");
                clearButtons();
                readNoteBtn.setVisible(true);
                seeNoticeBoardBtn.setVisible(true);
                checkDrawerBtn.setVisible(true);
            }
        });
    }

    // Helper to hide all buttons
    private void clearButtons() {
        readNoteBtn.setVisible(false);
        seeNoticeBoardBtn.setVisible(false);
        checkDrawerBtn.setVisible(false);
        checkBlackboardBtn.setVisible(false);
        answerRiddleBtn.setVisible(false);
        inspectTornPageBtn.setVisible(false);
        checkCalendarBtn.setVisible(false);
        goBackBtn.setVisible(false);
    }

    // Play sound helper with option to loop background ambience
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
}
