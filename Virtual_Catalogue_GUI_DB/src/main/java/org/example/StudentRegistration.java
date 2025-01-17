package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class StudentRegistration {
    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;

    public StudentRegistration() {
        createRegistrationForm();
    }

    private void createRegistrationForm() {
        frame = new JFrame("Student Registration");
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
        frame.add(registerButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    private void handleRegistration() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Validate input fields
        if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required. Please fill in all fields.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Perform registration
        if (registerStudent(firstName, lastName, password)) {
            JOptionPane.showMessageDialog(frame, "Registration successful! You can now log in.");
            frame.dispose();
            new StudentLogin(); // Redirect to login after successful registration
        } else {
            JOptionPane.showMessageDialog(frame, "Error during registration. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean registerStudent(String firstName, String lastName, String password) {
        // Check if the student already exists
        if (isStudentRegistered(firstName, lastName)) {
            JOptionPane.showMessageDialog(frame, "Student already registered. Please log in instead.", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Proceed to save the new student to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.csv", true))) {
            // Write student info and default grades to the CSV file
            bw.write(firstName + "," + lastName + "," + password);
            bw.newLine();
            bw.write("0,0,0,0"); // Default grades for subject 1
            bw.newLine();
            bw.write("0,0,0,0"); // Default grades for subject 2
            bw.newLine();
            bw.write("0,0,0,0"); // Default grades for subject 3
            bw.newLine();
            return true; // Registration successful
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error writing to file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Registration failed
        }
    }

    private boolean isStudentRegistered(String firstName, String lastName) {
        try (BufferedReader br = new BufferedReader(new FileReader("students.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(firstName) && parts[1].equals(lastName)) {
                    return true; // Student already exists
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Student not found
    }

}
