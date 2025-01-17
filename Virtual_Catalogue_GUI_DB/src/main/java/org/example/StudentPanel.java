package org.example;

import javax.swing.*;
import java.awt.*;

public class StudentPanel {
    private JFrame frame;

    public StudentPanel() {
        frame = new JFrame("Student Section");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);

        JLabel label = new JLabel("Student Section");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(130, 30, 200, 30);
        panel.add(label);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(100, 100, 100, 30);
        loginButton.addActionListener(e -> {
            frame.dispose();
            new StudentLogin();
        });
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(220, 100, 100, 30);
        registerButton.addActionListener(e -> {
            frame.dispose();
            new StudentRegistration();
        });
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
