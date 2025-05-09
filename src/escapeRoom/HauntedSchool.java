package escapeRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HauntedSchool class represents the Haunted School game.
 * Players can interact with elements to solve puzzles and obtain the Library Key.
 */
public class HauntedSchool extends JFrame {

    private JButton readNoteBtn;
    private JButton checkBlackboardBtn;
    private JButton answerRiddleBtn;
    private JTextArea storyArea;
    private boolean riddleAnswered = false;

    public HauntedSchool(EscapeRoomApp mainApp) {
        setTitle("Haunted School");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setText("Welcome to the Haunted School! \n\n" +
                "You need to find the key to the Library. \n" +
                "Interact with the elements to solve the puzzles.");
        add(new JScrollPane(storyArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        readNoteBtn = new JButton("Read Note");
        checkBlackboardBtn = new JButton("Check Blackboard");
        answerRiddleBtn = new JButton("Answer Riddle");

        buttonPanel.add(readNoteBtn);
        buttonPanel.add(checkBlackboardBtn);
        buttonPanel.add(answerRiddleBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        readNoteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storyArea.setText("You read a note: 'The key lies where knowledge is shared.'");
            }
        });

        checkBlackboardBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storyArea.setText("The blackboard has a riddle: 'I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?'");
            }
        });

        answerRiddleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String answer = JOptionPane.showInputDialog(HauntedSchool.this, "What is your answer?");
                if (answer != null && answer.equalsIgnoreCase("echo")) {
                    riddleAnswered = true;
                    KeyManager.obtainLibraryKey();
                    storyArea.setText("Correct! You have obtained the Library Key!");
                    mainApp.updateButtonStates(); // Update main app button states
                    answerRiddleBtn.setEnabled(false); // Disable the riddle button after answering
                } else {
                    JOptionPane.showMessageDialog(HauntedSchool.this, "Incorrect answer. Try again!", "Wrong Answer", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
