import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LabActivity6SwingToDoList {
    static ArrayList<Task> tasks = new ArrayList<>();
    static ToDoListForm formWindow = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListViewer::new);
    }
}

class Task {
    String name, description, status;
    Task(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }
}

class ToDoListViewer extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;

    public ToDoListViewer() {
        setTitle("To-Do List Viewer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);

        JButton addTaskBtn = new JButton("Add Task");
        addTaskBtn.addActionListener(e -> {
            if (LabActivity6SwingToDoList.formWindow == null || !LabActivity6SwingToDoList.formWindow.isDisplayable()) {
                LabActivity6SwingToDoList.formWindow = new ToDoListForm(this);
            } else {
                LabActivity6SwingToDoList.formWindow.toFront();
            }
        });

        String[] columns = {"Task Name", "Task Description", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        refreshTable();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(addTaskBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for (Task t : LabActivity6SwingToDoList.tasks) {
            tableModel.addRow(new Object[]{t.name, t.description, t.status});
        }
    }
}
class ToDoListForm extends JFrame {
    public ToDoListForm(ToDoListViewer viewer) {
        setTitle("To-Do List Form");
        setSize(350, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(650, 150);

        JTextField nameField = new JTextField(20);
        JTextArea descArea = new JTextArea(3, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descArea);

        String[] statuses = {"Not Started", "Ongoing", "Completed"};
        JComboBox<String> statusBox = new JComboBox<>(statuses);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String desc = descArea.getText().trim();
            String status = (String) statusBox.getSelectedItem();
            if (!name.isEmpty() && status != null && !status.isEmpty()) {
                LabActivity6SwingToDoList.tasks.add(new Task(name, desc, status));
                viewer.refreshTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in Task Name and Status", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Task Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        formPanel.add(descScroll, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        formPanel.add(statusBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(saveButton, gbc);

        add(formPanel);
        setVisible(true);
    }
}
