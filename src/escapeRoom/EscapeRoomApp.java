package escapeRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main application class that displays the main menu and handles navigation.
 */
public class EscapeRoomApp extends JFrame {

    private JButton hauntedSchoolBtn;
    private JButton libraryBtn;
    private JButton jailBtn;
    private JButton hospitalBtn;
    private JButton forestBtn;
    private JLabel statusLabel;

    private boolean jailKeyObtained = false;

    public EscapeRoomApp() {
        setTitle("Digital Escape Room - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Digital Escape Room", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        hauntedSchoolBtn = new JButton("Haunted School");
        libraryBtn = new JButton("Library (Locked)");
        jailBtn = new JButton("Jail (Locked)");
        hospitalBtn = new JButton("Hospital (Coming Soon)");
        forestBtn = new JButton("Forest (Coming Soon)");

        hauntedSchoolBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        libraryBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        jailBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        hospitalBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        forestBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));

        //jailBtn.setEnabled(false); // Initially locked

        buttonPanel.add(hauntedSchoolBtn);
        buttonPanel.add(libraryBtn);
        buttonPanel.add(jailBtn);
        buttonPanel.add(hospitalBtn);
        buttonPanel.add(forestBtn);

        add(buttonPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Get the Library Key from Haunted School to unlock Library.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        add(statusLabel, BorderLayout.SOUTH);

        // Initial button state
        updateButtonStates();

        // Button actions
        hauntedSchoolBtn.addActionListener(e ->
                new HauntedSchool(EscapeRoomApp.this).setVisible(true));

        libraryBtn.addActionListener(e -> {
            if (KeyManager.hasLibraryKey()) {
                new Library(EscapeRoomApp.this).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(EscapeRoomApp.this,
                        "You need the Library Key from Haunted School to enter the Library.",
                        "Access Denied", JOptionPane.WARNING_MESSAGE);
            }
        });

        jailBtn.addActionListener(e -> {
            if (KeyManager.hasJailKey()) {
<<<<<<< HEAD
                /*JOptionPane.showMessageDialog(this,
                        "Welcome to Jail! (Not implemented yet.)",
                        "Jail", JOptionPane.INFORMATION_MESSAGE);*/
                new Jail(EscapeRoomApp.this).setVisible(true);
=======
                JOptionPane.showMessageDialog(this,
                        "Welcome to Jail! (Not implemented yet.)",
                        "Jail", JOptionPane.INFORMATION_MESSAGE);
>>>>>>> 3013dce9e0d43a722457cd1cc1bf3640f115611d
            } else {
                JOptionPane.showMessageDialog(this,
                        "You need the Jail Key from Library Escape to enter the Jail.",
                        "Access Denied", JOptionPane.WARNING_MESSAGE);
            }
        });

        hospitalBtn.addActionListener(e -> JOptionPane.showMessageDialog(EscapeRoomApp.this,
                "Hospital is coming soon!", "Coming Soon", JOptionPane.INFORMATION_MESSAGE));

        forestBtn.addActionListener(e -> JOptionPane.showMessageDialog(EscapeRoomApp.this,
                "Forest is coming soon!", "Coming Soon", JOptionPane.INFORMATION_MESSAGE));
    }

    /**
     * Updates the Library and Jail button labels and enable states based on key possessions.
     */
    public void updateButtonStates() {
        if (KeyManager.hasLibraryKey()) {
            libraryBtn.setText("Library");
            libraryBtn.setEnabled(true);
            statusLabel.setText("You have the Library Key! You can enter the Library now.");
        } else {
            libraryBtn.setText("Library (Locked)");
            libraryBtn.setEnabled(true);
            statusLabel.setText("Get the Library Key from Haunted School to unlock Library.");
        }

        if (jailKeyObtained) {
            jailBtn.setText("Jail");
            jailBtn.setEnabled(true);
            statusLabel.setText("You have the Jail Key! You can enter the Jail now.");
        } else {
            jailBtn.setText("Jail (Locked)");
            jailBtn.setEnabled(true);
            statusLabel.setText("Get the Jail Key from Library to unlock Jail.");
        }
    }

    /**
     * Call this method when the player obtains the Library Key in Haunted School.
     */
    public void keyObtained() {
        KeyManager.obtainLibraryKey();
        updateButtonStates();
        JOptionPane.showMessageDialog(this,
                "Library Key obtained! You can now enter the Library.",
                "Key Acquired", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Call this method when the player obtains the Jail Key in Library Escape.
     */
    public void jailKeyObtained() {
        jailKeyObtained = true;
        KeyManager.obtainJailKey();
        updateButtonStates();
        JOptionPane.showMessageDialog(this,
                "Jail Key obtained! You can now enter the Jail.",
                "Key Acquired", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Set system look and feel if possible
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            EscapeRoomApp app = new EscapeRoomApp();
            app.setVisible(true);
        });
    }
}

