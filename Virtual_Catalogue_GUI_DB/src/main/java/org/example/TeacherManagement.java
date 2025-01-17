package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TeacherManagement {
    private JFrame frame;
    private JTextField firstNameField;
    private JPasswordField passwordField;
    private JTextField lastNameField;

    public TeacherManagement() {
        showLoginForm();
    }

    // Method to show the login form
    private void showLoginForm() {
        frame = new JFrame("Teacher Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 2, 10, 10)); // Adjusted grid layout for fewer buttons

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
            frame.dispose(); // Close the login frame
            // Navigate back to the main menu or previous screen
            JOptionPane.showMessageDialog(null, "Returning to the main menu."); // Example message
            // Example: new MainMenu(); Uncomment if a MainMenu class exists
            new TeacherPanel();
        });

        // Add components to the frame
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

    // Method to handle teacher login
    private void handleLogin() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String password = new String(passwordField.getPassword());

        if (loginTeacher(firstName, lastName, password)) {
            JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + firstName + "!");
            frame.dispose();
            new TeacherDashboard(); // Open Teacher Dashboard after successful login
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to check teacher credentials from CSV
    private boolean loginTeacher(String firstName, String lastName, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("teacher.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] teacherData = line.split(",");
                if (teacherData.length == 4 && teacherData[0].equals(firstName) && teacherData[1].equals(lastName) && teacherData[2].equals(password)) {
                    return true;  // Credentials match
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;  // Credentials do not match
    }
}
