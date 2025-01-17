package org.example;

import javax.swing.*;
import java.awt.*;

public class StudentGradesPanel {
    private JFrame frame;

    public StudentGradesPanel(Student student) {
        createGradesPanel(student);
    }

    private void createGradesPanel(Student student) {
        frame = new JFrame("Student Grades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        String[] subjects = {"Informatics", "Mathematics", "English"};
        JPanel gradesPanel = new JPanel();
        gradesPanel.setLayout(new GridLayout(subjects.length + 1, 1));

        for (int i = 0; i < subjects.length; i++) {
            int[] grades = student.getGrades(i);
            double average = student.computeAverage(i);

            StringBuilder gradesText = new StringBuilder(subjects[i] + " Grades: ");
            for (int grade : grades) {
                gradesText.append(grade).append(" ");
            }
            gradesText.append(" | Average: ").append(String.format("%.2f", average));

            JLabel gradeLabel = new JLabel(gradesText.toString(), JLabel.CENTER);
            gradesPanel.add(gradeLabel);
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new StudentDashboard(student); // Return to the student dashboard
        });

        frame.add(gradesPanel, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
