package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TeacherLogin {
    private JFrame frame;
    private JTextField firstNameField;
    private JPasswordField passwordField;

    public TeacherLogin() {
        createLoginForm();
    }

    private void createLoginForm() {
        frame = new JFrame("Teacher Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
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
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    private void handleLogin() {
        String firstName = firstNameField.getText();
        String password = new String(passwordField.getPassword());

        if (logInTeacher(firstName, password)) {
            JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + firstName + "!");
            frame.dispose();
            // Proceed to teacher dashboard or next screen
            new TeacherDashboard(); // Assuming you have a TeacherDashboard class for the teacher's next actions
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean logInTeacher(String firstName, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("teacher.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] teacherData = line.split(",");
                if (teacherData.length == 4 && teacherData[0].equals(firstName) && teacherData[2].equals(password)) {
                    return true; // Successfully logged in
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Login failed
    }
}
