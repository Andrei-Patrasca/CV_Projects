package org.example;

import javax.swing.*;
import java.awt.*;

public class TeacherPanel {
    private JFrame frame;

    public TeacherPanel() {
        frame = new JFrame("Teacher Section");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);

        JLabel label = new JLabel("Teacher Section");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(130, 30, 200, 30);
        panel.add(label);

        // Log In button
        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(100, 100, 100, 30);
        loginButton.addActionListener(e -> {
            frame.dispose(); // Close the current window
            new TeacherManagement(); // Open the Teacher Management panel for login
        });
        panel.add(loginButton);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(220, 100, 100, 30);
        registerButton.addActionListener(e -> {
            frame.dispose(); // Close the current window
            new TeacherRegistration(); // Open the Teacher Management panel for registration
        });
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
