package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentLogin {
    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;

    public StudentLogin() {
        createLoginForm();
    }

    private void createLoginForm() {
        frame = new JFrame("Student Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 1, 10, 10));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(e -> handleLogin());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new ManagementPanel(); // Go back to the main panel
        });

        frame.add(firstNameLabel);
        frame.add(firstNameField);
        frame.add(lastNameLabel);
        frame.add(lastNameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    private void handleLogin() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String password = new String(passwordField.getPassword());

        Student loggedInStudent = loginStudent(firstName, lastName, password);

        if (loggedInStudent != null) {
            JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + firstName + "!");
            frame.dispose();
            new StudentDashboard(loggedInStudent); // Pass the logged-in student
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Student loginStudent(String firstName, String lastName, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("students.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(firstName) && parts[1].equals(lastName) && parts[2].equals(password)) {
                    // If credentials match, create a Student object
                    Student student = new Student(parts[0], parts[1], parts[2]);

                    // Load grades from the file
                    for (int i = 0; i < 3; i++) { // Assuming 3 subjects
                        if ((line = br.readLine()) != null) {
                            String[] gradeParts = line.split(",");
                            int[] grades = new int[gradeParts.length];
                            for (int j = 0; j < gradeParts.length; j++) {
                                grades[j] = Integer.parseInt(gradeParts[j]);
                            }
                            student.updateGrades(i, grades);
                        }
                    }

                    return student; // Return the logged-in student
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // Return null if login fails
    }

}
