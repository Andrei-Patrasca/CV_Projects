package org.example;

import javax.swing.*;
import java.awt.*;

public class ManagementPanel {
    private JFrame frame;

    public ManagementPanel() {
        frame = new JFrame("Virtual Catalogue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to Virtual Catalogue");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBounds(80, 30, 250, 30);
        panel.add(welcomeLabel);

        JLabel roleLabel = new JLabel("Please choose your role:");
        roleLabel.setForeground(Color.LIGHT_GRAY);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roleLabel.setBounds(120, 70, 200, 30);
        panel.add(roleLabel);

        JButton teacherButton = new JButton("Teacher");
        teacherButton.setBounds(80, 120, 100, 30);
        teacherButton.addActionListener(e -> {
            frame.dispose();
            new TeacherPanel();
        });
        panel.add(teacherButton);

        JButton studentButton = new JButton("Student");
        studentButton.setBounds(200, 120, 100, 30);
        studentButton.addActionListener(e -> {
            frame.dispose();
            new StudentPanel();
        });
        panel.add(studentButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
