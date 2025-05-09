package escapeRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Library class represents the Library game.
 * Players can interact with elements to complete the game.
 */
public class Library extends JFrame {

    private JButton readBookBtn;
    private JButton solvePuzzleBtn;
    private JTextArea storyArea;

    public Library(EscapeRoomApp mainApp) {
        setTitle("Library");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setText("Welcome to the Library! \n\n" +
                "You have the Library Key. Solve the puzzle to escape!");
        add(new JScrollPane(storyArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        readBookBtn = new JButton("Read Book");
        solvePuzzleBtn = new JButton("Solve Puzzle");

        buttonPanel.add(readBookBtn);
        buttonPanel.add(solvePuzzleBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        readBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storyArea.setText("You read a book about ancient mysteries. It hints at a puzzle to solve.");
            }
        });

        solvePuzzleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String answer = JOptionPane.showInputDialog(Library.this, "What has keys but can't open locks?");
                if (answer != null && answer.equalsIgnoreCase("piano")) {
                    JOptionPane.showMessageDialog(Library.this, "Congratulations! You've solved the puzzle and escaped the Library!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the Library window
                } else {
                    JOptionPane.showMessageDialog(Library.this, "Incorrect answer. Try again!", "Wrong Answer", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
