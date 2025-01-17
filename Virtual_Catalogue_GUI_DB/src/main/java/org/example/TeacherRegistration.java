package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TeacherRegistration {
    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    private JTextField degreeField;

    public TeacherRegistration() {
        createRegistrationForm();
    }

    private void createRegistrationForm() {
        frame = new JFrame("Teacher Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 1, 10, 10));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JLabel degreeLabel = new JLabel("Major Degree:");
        degreeField = new JTextField(20);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegistration());

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
        frame.add(degreeLabel);
        frame.add(degreeField);
        frame.add(registerButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    private void handleRegistration() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String password = new String(passwordField.getPassword());
        String degree = degreeField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || degree.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required. Please fill in all fields.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (registerTeacher(firstName, lastName, password, degree)) {
            JOptionPane.showMessageDialog(frame, "Registration successful! You can now log in.");
            frame.dispose();
            new TeacherLogin(); // Redirect to login after successful registration
        } else {
            JOptionPane.showMessageDialog(frame, "Error during registration. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean registerTeacher(String firstName, String lastName, String password, String degree) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("teacher.csv", true))) {
            bw.write(firstName + "," + lastName + "," + password + "," + degree);
            bw.newLine();
            return true; // Registration successful
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error writing to the file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Registration failed
        }
    }
}
