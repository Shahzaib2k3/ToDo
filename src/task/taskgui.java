package task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class taskgui {
    private JFrame frame;
    private JTextField titleField;
    private JTextField dateField;
    private JTable table;
    private DefaultTableModel tableModel;

    public taskgui() {
        frame = new JFrame("TaskMate - To-Do App");
        frame.setBounds(100, 100, 600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Task Title:");
        lblTitle.setBounds(20, 20, 100, 20);
        frame.getContentPane().add(lblTitle);

        titleField = new JTextField();
        titleField.setBounds(120, 20, 200, 25);
        frame.getContentPane().add(titleField);

        JLabel lblDate = new JLabel("Due Date:");
        lblDate.setBounds(20, 60, 100, 20);
        frame.getContentPane().add(lblDate);

        dateField = new JTextField();
        dateField.setBounds(120, 60, 200, 25);
        frame.getContentPane().add(dateField);

        JButton btnAdd = new JButton("Add Task");
        btnAdd.setBounds(350, 20, 120, 25);
        frame.getContentPane().add(btnAdd);

        JButton btnComplete = new JButton("Mark Completed");
        btnComplete.setBounds(350, 60, 160, 25);
        frame.getContentPane().add(btnComplete);

        JButton btnDelete = new JButton("Delete Task");
        btnDelete.setBounds(350, 100, 120, 25);
        frame.getContentPane().add(btnDelete);

        JButton btnSort = new JButton("Sort by Date");
        btnSort.setBounds(350, 140, 120, 25);
        frame.getContentPane().add(btnSort);

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(350, 180, 120, 25);
        frame.getContentPane().add(btnExit);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Title", "Due Date", "Completed" }
        );
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 220, 540, 160);
        frame.getContentPane().add(scrollPane);

        // Button Actions
        btnAdd.addActionListener(e -> {
            String title = titleField.getText().trim();
            String date = dateField.getText().trim();

            if (title.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter title and due date.");
                return;
            }

            taskmanager.addTask(title, date);
            refreshTable();
            titleField.setText("");
            dateField.setText("");
        });

        btnComplete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                taskmanager.markCompleted(selected);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Select a task to mark as completed.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                taskmanager.deleteTask(selected);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Select a task to delete.");
            }
        });

        btnSort.addActionListener(e -> {
            taskmanager.sortByDate();
            refreshTable();
        });

        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private void refreshTable() {
        List<Task> tasks = taskmanager.getTasks();
        tableModel.setRowCount(0); // clear table
        for (Task t : tasks) {
            tableModel.addRow(new Object[] {
                t.getTitle(),
                t.getDueDate(),
                t.isCompleted() ? "Yes" : "No"
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(taskgui::new);
    }
}
