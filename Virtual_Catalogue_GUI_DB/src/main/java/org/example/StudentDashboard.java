package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentDashboard {
    private JFrame frame;
    private Student currentStudent;

    public StudentDashboard(Student student) {
        this.currentStudent = student;
        createDashboard();
    }

    private void createDashboard() {
        frame = new JFrame("Student Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 1, 10, 10)); // Adjust grid to accommodate the new button

        JLabel welcomeLabel = new JLabel("Welcome, " + currentStudent.getFirstName() + " " + currentStudent.getLastName() + "!", JLabel.CENTER);
        JButton seeGradesButton = new JButton("See Grades");
        JButton sendMessageButton = new JButton("Send Message to Teacher");
        JButton sendEmailButton = new JButton("Send Email"); // New button
        JButton backButton = new JButton("Back");

        seeGradesButton.addActionListener(e -> {
            frame.dispose();
            new StudentGradesPanel(currentStudent);
        });

        sendMessageButton.addActionListener(e -> {
            sendMessageToTeacher(currentStudent);
        });

        sendEmailButton.addActionListener(e -> {
            sendEmailAction(); // Call the send email action
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new ManagementPanel(); // Return to the main role selection
        });

        frame.add(welcomeLabel);
        frame.add(seeGradesButton);
        frame.add(sendMessageButton);
        frame.add(sendEmailButton); // Add the new button
        frame.add(backButton);

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
    private void sendMessageToTeacher(Student student) {
        String message = JOptionPane.showInputDialog(frame, "Enter your message for the teacher:", "Send Message", JOptionPane.PLAIN_MESSAGE);

        if (message != null && !message.trim().isEmpty()) {
            String fullMessage = student.getFirstName() + " " + student.getLastName() + ": " + message;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("messages.csv", true))) {
                writer.write(fullMessage);
                writer.newLine();
                JOptionPane.showMessageDialog(frame, "Message sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "An error occurred while sending the message.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
