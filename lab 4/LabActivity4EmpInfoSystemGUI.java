import javax.swing.*;
import java.awt.Font;

public class LabActivity4EmpInfoSystemGUI {
    private static JPanel panel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Laboratory Activity 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        frame.add(panel);
        placeComponents(frame);

        frame.setVisible(true);
    }

    public static void placeComponents(JFrame frame) {
        panel.setLayout(null);

        JLabel FirstNameLabel = new JLabel("First Name: ");
        FirstNameLabel.setBounds(10, 20, 80, 25);
        FirstNameLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
        panel.add(FirstNameLabel);

        JTextField firstNameText = new JTextField(20);
        firstNameText.setBounds(150, 20, 165, 25);
        panel.add(firstNameText);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(10, 50, 80, 25);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
        panel.add(lastNameLabel);

        JTextField lastNameText = new JTextField(20);
        lastNameText.setBounds(150, 50, 165, 25);
        panel.add(lastNameText);

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setBounds(10, 80, 80, 25);
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
        panel.add(ageLabel);

        JTextField ageText = new JTextField(20);
        ageText.setBounds(150, 80, 165, 25);
        panel.add(ageText);

        JLabel HoursWorkedLabel = new JLabel("Hours Worked: ");
        HoursWorkedLabel.setBounds(10, 110, 100, 25);
        HoursWorkedLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
        panel.add(HoursWorkedLabel);

        JTextField HoursWorkedText = new JTextField(20);
        HoursWorkedText.setBounds(150, 110, 165, 25);
        panel.add(HoursWorkedText);

        JLabel HourlyRateLabel = new JLabel("Hourly Rate: ");
        HourlyRateLabel.setBounds(10, 140, 80, 25);
        HourlyRateLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
        panel.add(HourlyRateLabel);

        JTextField HourlyRateText = new JTextField(20);
        HourlyRateText.setBounds(150, 140, 165, 25);
        panel.add(HourlyRateText);

        JButton submitButton = new JButton("Submit");

        int buttonWidth = 100;
        int buttonHeight = 25;
        int frameWidth = frame.getWidth(); 
        int xPosition = (frameWidth - buttonWidth) / 2; 
        int yPosition = 180; 

        submitButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
        submitButton.addActionListener(e -> {
            String firstName = firstNameText.getText().trim();
            String lastName = lastNameText.getText().trim();
            String age = ageText.getText().trim();
            String hoursWorked = HoursWorkedText.getText().trim();
            String hourlyRate = HourlyRateText.getText().trim();

            // Check if any field is empty
            if (firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || hoursWorked.isEmpty() || hourlyRate.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: All fields must be filled.");
                return; // Stop further execution
            }

            try {
                int ageValue;
                try {
                    ageValue = Integer.parseInt(age);
                    if (ageValue <= 0) {
                        throw new NumberFormatException("Age must be a positive integer.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Age must be a valid positive integer.");
                    return;
                }

                double hours = Double.parseDouble(hoursWorked);
                double rate = Double.parseDouble(hourlyRate);

                double dailyWage = hours * rate;

                JOptionPane.showMessageDialog(frame, "Data Submitted:\n" +
                        "First Name: " + firstName +" "+ lastName +"\n" +
                        "Age: " + ageValue + " years old\n" +
                        "Daily Wage: PHP " + dailyWage);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error: Hours Worked and Hourly Rate must be valid numbers.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage());
            }
        });
        panel.add(submitButton);
    }
}
