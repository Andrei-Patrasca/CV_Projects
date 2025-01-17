package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherDashboard {
    private JFrame frame;

    public TeacherDashboard() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Teacher Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500); // Adjusted size for extra buttons
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 1, 10, 10)); // Adjusted grid for extra buttons

        JButton viewGradesButton = new JButton("View Student Grades");
        viewGradesButton.addActionListener(e -> viewStudentGrades());

        JButton modifyGradesButton = new JButton("Modify Student Grades");
        modifyGradesButton.addActionListener(e -> modifyStudentGrades());

        JButton viewClassDetailsButton = new JButton("View Class Details");
        viewClassDetailsButton.addActionListener(e -> viewClassDetails());

        JButton showMessagesButton = new JButton("Show Messages");
        showMessagesButton.addActionListener(e -> showMessages());

        JButton sendEmailButton = new JButton("Send Email"); // New button for sending email
        sendEmailButton.addActionListener(e -> sendEmailAction());

        JButton manageDatabaseButton = new JButton("Manage Database"); // New button for managing database
        manageDatabaseButton.addActionListener(e -> manageDatabaseAction());

        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Logged out successfully!");
            // Redirect to main menu if applicable
        });

        frame.add(viewGradesButton);
        frame.add(modifyGradesButton);
        frame.add(viewClassDetailsButton);
        frame.add(showMessagesButton);
        frame.add(sendEmailButton); // Add the new "Send Email" button
        frame.add(manageDatabaseButton); // Add the new "Manage Database" button
        frame.add(logoutButton);

        frame.setVisible(true);
    }

    private void sendEmailAction() {
        try {
            EmailService.main(new String[0]); // Call the EmailService main method
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to send email: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageDatabaseAction() {
        try {
            DatabaseManager.main(new String[0]); // Call the DatabaseManager main method
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to manage database: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStudentGrades() {
        String firstName = JOptionPane.showInputDialog(frame, "Enter the student's first name:");
        String lastName = JOptionPane.showInputDialog(frame, "Enter the student's last name:");

        if (firstName == null || lastName == null) return;

        try (BufferedReader br = new BufferedReader(new FileReader("students.csv"))) {
            String line;
            boolean studentFound = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(firstName) && parts[1].equals(lastName)) {
                    studentFound = true;

                    // Prepare data for JTable
                    String[][] data = new String[3][3];
                    String[] columnNames = {"Subject", "Grades", "Average"};
                    String[] subjects = {"Informatics", "Mathematics", "English"};

                    for (int i = 0; i < subjects.length; i++) {
                        if ((line = br.readLine()) != null) {
                            String[] grades = line.split(",");
                            double average = Arrays.stream(grades).mapToInt(Integer::parseInt).average().orElse(0.0);
                            data[i][0] = subjects[i];
                            data[i][1] = String.join(", ", grades);
                            data[i][2] = String.format("%.2f", average);
                        }
                    }

                    // Create JTable
                    JTable table = new JTable(data, columnNames);
                    table.setEnabled(false); // Make the table non-editable
                    table.setRowHeight(25);
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

                    JScrollPane scrollPane = new JScrollPane(table);

                    // Display the table in a dialog
                    JOptionPane.showMessageDialog(frame, scrollPane, "Grades for " + firstName + " " + lastName, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            if (!studentFound) {
                JOptionPane.showMessageDialog(frame, "Student not found in the records.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private String formatGrades(String subject, String gradesLine) {
        String[] grades = gradesLine.split(",");
        double average = Arrays.stream(grades).mapToInt(Integer::parseInt).average().orElse(0.0);
        return subject + " Grades: " + String.join(", ", grades) + " (Average: " + average + ")";
    }

    private void modifyStudentGrades() {
        String firstName = JOptionPane.showInputDialog(frame, "Enter the student's first name:");
        String lastName = JOptionPane.showInputDialog(frame, "Enter the student's last name:");

        if (firstName == null || lastName == null) return;

        File file = new File("students.csv");
        File tempFile = new File("students_temp.csv");
        boolean studentFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(firstName) && parts[1].equals(lastName)) {
                    studentFound = true;
                    bw.write(line);
                    bw.newLine();

                    String[] subjects = {"Informatics", "Mathematics", "English"};
                    Map<String, String> gradesMap = new LinkedHashMap<>();
                    for (String subject : subjects) {
                        if ((line = br.readLine()) != null) {
                            gradesMap.put(subject, line);
                        }
                    }

                    String selectedSubject = (String) JOptionPane.showInputDialog(frame,
                            "Select the subject to modify:", "Modify Grades",
                            JOptionPane.QUESTION_MESSAGE, null, subjects, subjects[0]);

                    if (selectedSubject != null) {
                        String gradesLine = gradesMap.get(selectedSubject);
                        String[] grades = gradesLine.split(",");
                        for (int i = 0; i < grades.length; i++) {
                            String newGrade = JOptionPane.showInputDialog(frame,
                                    "Enter new grade for test " + (i + 1) + ":", grades[i]);
                            if (newGrade != null) {
                                grades[i] = newGrade;
                            }
                        }
                        gradesMap.put(selectedSubject, String.join(",", grades));
                    }

                    for (String updatedGrades : gradesMap.values()) {
                        bw.write(updatedGrades);
                        bw.newLine();
                    }

                    JOptionPane.showMessageDialog(frame, "Grades updated successfully!");
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }

            if (!studentFound) {
                JOptionPane.showMessageDialog(frame, "Student not found in the records.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error modifying grades.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (studentFound) {
            if (file.delete()) {
                tempFile.renameTo(file);
            } else {
                JOptionPane.showMessageDialog(frame, "Error updating file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            tempFile.delete();
        }
    }

    private void viewClassDetails() {
        try (BufferedReader br = new BufferedReader(new FileReader("students.csv"))) {
            List<String[]> data = new ArrayList<>();
            String[] columnNames = {"Name", "Informatics Avg", "Mathematics Avg", "English Avg"};
            String[] disciplines = {"Informatics", "Mathematics", "English"};

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                String firstName = parts[0];
                String lastName = parts[1];
                double[] averages = new double[disciplines.length];

                for (int i = 0; i < disciplines.length; i++) {
                    if ((line = br.readLine()) != null) {
                        String[] grades = line.split(",");
                        averages[i] = Arrays.stream(grades).mapToInt(Integer::parseInt).average().orElse(0.0);
                    }
                }

                data.add(new String[]{
                        firstName + " " + lastName,
                        String.format("%.2f", averages[0]),
                        String.format("%.2f", averages[1]),
                        String.format("%.2f", averages[2])
                });
            }

            // Create JTable
            JTable table = new JTable(data.toArray(new String[0][0]), columnNames);
            table.setEnabled(false); // Make the table non-editable
            table.setRowHeight(25);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            JScrollPane scrollPane = new JScrollPane(table);

            // Display the table in a dialog
            JOptionPane.showMessageDialog(frame, scrollPane, "Class Details", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading class details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void showMessages() {
        JFrame messagesFrame = new JFrame("Student Messages");
        messagesFrame.setSize(600, 400);
        messagesFrame.setLocationRelativeTo(null);

        // Define table columns
        String[] columns = {"Student Name", "Message"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Read messages from the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("messages.csv"))) {
            String line;
            boolean hasMessages = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String studentName = parts[0].trim();
                    String message = parts[1].trim();
                    tableModel.addRow(new Object[]{studentName, message});
                    hasMessages = true;
                }
            }

            if (!hasMessages) {
                JOptionPane.showMessageDialog(messagesFrame, "No messages available.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(messagesFrame, "Error reading messages file.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Create JTable to display messages
        JTable messagesTable = new JTable(tableModel);
        messagesTable.setFillsViewportHeight(true);

        // Add table to a scroll pane for better display
        JScrollPane scrollPane = new JScrollPane(messagesTable);

        // Add scroll pane to the frame
        messagesFrame.add(scrollPane);
        messagesFrame.setVisible(true);
    }


}
