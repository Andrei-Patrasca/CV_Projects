package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String password;
    private List<int[]> grades;

    public Student(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.grades = new ArrayList<>();
        grades.add(new int[]{0, 0, 0, 0}); // Informatics
        grades.add(new int[]{0, 0, 0, 0}); // Mathematics
        grades.add(new int[]{0, 0, 0, 0}); // English
    }

    public int[] getGrades(int disciplineIndex) {
        return grades.get(disciplineIndex);
    }

    public void updateGrades(int disciplineIndex, int[] newGrades) {
        if (disciplineIndex >= 0 && disciplineIndex < grades.size()) {
            grades.set(disciplineIndex, newGrades);
        } else {
            System.out.println("Invalid discipline index.");
        }
    }

    public double computeAverage(int disciplineIndex) {
        int[] disciplineGrades = grades.get(disciplineIndex);
        int total = 0, count = 0;

        for (int grade : disciplineGrades) {
            total += grade;
            if (grade != 0) count++;
        }

        return count > 0 ? (double) total / count : 0.0;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
