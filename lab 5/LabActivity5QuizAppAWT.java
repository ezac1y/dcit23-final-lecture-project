import java.awt.*;
import java.awt.event.*;

public class LabActivity5QuizAppAWT extends Frame implements ActionListener {
    // Arrays for questions, choices, and correct answers
    String[] questions = {
        "1. What does CPU stand for?",
        "2. What does USB stand for?",
        "3. What does Wi-Fi stand for?"
    };
    String[][] choices = {
        {"Central Processing Unit", "Computer Personal Unit", "Central Performance Utility", "Control Processing Unit"},
        {"Universal System Backup", "Unique Serial Band", "Universal Serial Bus", "United Software Bridge"},
        {"Wireless Fidelity", "Wide Frequency Internet", "Wired File Input", "Web File Interface"}
    };
    int[] answers = {0, 2, 0}; // correct answer indices: CPU (A), USB (C), Wi-Fi (A)

    int current = 0;
    int score = 0;

    Label questionLabel;
    CheckboxGroup optionsGroup;
    Checkbox[] optionBoxes;
    Button nextBtn;
    Panel choicesPanel;
    Label errorLabel; // Add this at the class level

    public LabActivity5QuizAppAWT() {
        setLayout(new GridLayout(5, 1, 10, 10)); // Increase rows to 5
        setTitle("Simple Quiz App");
        setSize(500, 300);

        // Question label
        questionLabel = new Label();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        // Choices panel with 2 columns and 2 rows
        choicesPanel = new Panel(new GridLayout(2, 2, 10, 10));
        optionsGroup = new CheckboxGroup();
        optionBoxes = new Checkbox[4];
        for (int i = 0; i < 4; i++) {
            optionBoxes[i] = new Checkbox("", optionsGroup, false);
            optionBoxes[i].setBackground(new Color(230, 230, 250)); // Lavender
            optionBoxes[i].setFont(new Font("Arial", Font.PLAIN, 14));
            choicesPanel.add(optionBoxes[i]);
        }
        add(choicesPanel);

        // Error label
        errorLabel = new Label("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(errorLabel);

        // Next button
        nextBtn = new Button("Next");
        nextBtn.addActionListener(this);
        nextBtn.setBackground(new Color(139, 195, 74)); // Light green
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font("Arial", Font.BOLD, 14));
        add(nextBtn);

        showQuestion();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0); // Terminate the program
            }
        });

        setVisible(true);
    }

    void showQuestion() {
        questionLabel.setText(questions[current]);
        optionsGroup.setSelectedCheckbox(null); // Reset selection
        errorLabel.setText(""); // Clear error message
        for (int i = 0; i < 4; i++) {
            optionBoxes[i].setLabel(choices[current][i]);
            optionBoxes[i].setState(false);
            optionBoxes[i].setVisible(true);
            optionBoxes[i].setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if an answer is selected
        Checkbox selected = optionsGroup.getSelectedCheckbox();
        if (selected == null) {
            errorLabel.setText("Please select an answer");
            return;
        }

        errorLabel.setText(""); // Clear error if answered

        int selectedIndex = -1;
        for (int i = 0; i < 4; i++) {
            if (optionBoxes[i] == selected) {
                selectedIndex = i;
                break;
            }
        }
        if (selectedIndex == answers[current]) {
            score++;
        }

        if (current < questions.length - 1) {
            current++;
            showQuestion();
        } else {
            questionLabel.setText("Quiz Completed! Your score: " + score + " out of " + questions.length + " ");
            for (Checkbox cb : optionBoxes) {
                cb.setEnabled(false); // Disable choices
            }
            nextBtn.setEnabled(false); 
            errorLabel.setText(""); 
        }
    }

    public static void main(String[] args) {
        new LabActivity5QuizAppAWT();
    }
}
