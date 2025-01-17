package org.example;
import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/Catalogue";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Urs5658?!";

    private Connection connection;

    public DatabaseManager() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void viewStudents() throws SQLException {
        String query = "SELECT * FROM Students";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("StudentID") + ", Name: " + rs.getString("FirstName") + " " + rs.getString("LastName"));
            }
        }
    }

    public void insertStudent(String firstName, String lastName, String password, String informaticsGrades, String mathGrades, String englishGrades) throws SQLException {
        String query = "INSERT INTO Students (FirstName, LastName, Password, InformaticsGrades, MathGrades, EnglishGrades) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, password);
            pstmt.setString(4, informaticsGrades);
            pstmt.setString(5, mathGrades);
            pstmt.setString(6, englishGrades);
            pstmt.executeUpdate();
        }
    }

    public void updateStudent(int studentID, String field, String newValue) throws SQLException {
        String query = "UPDATE Students SET " + field + " = ? WHERE StudentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, newValue);
            pstmt.setInt(2, studentID);
            pstmt.executeUpdate();
        }
    }

    public void deleteStudent(int studentID) throws SQLException {
        String query = "DELETE FROM Students WHERE StudentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();
        }
    }

    public void viewTeachers() throws SQLException {
        String query = "SELECT * FROM Teachers";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("TeacherID") + ", Name: " + rs.getString("FirstName") + " " + rs.getString("LastName") + ", Degree: " + rs.getString("StudyDegree"));
            }
        }
    }

    public void insertTeacher(String firstName, String lastName, String password, String studyDegree) throws SQLException {
        String query = "INSERT INTO Teachers (FirstName, LastName, Password, StudyDegree) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, password);
            pstmt.setString(4, studyDegree);
            pstmt.executeUpdate();
        }
    }

    public void updateTeacher(int teacherID, String field, String newValue) throws SQLException {
        String query = "UPDATE Teachers SET " + field + " = ? WHERE TeacherID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, newValue);
            pstmt.setInt(2, teacherID);
            pstmt.executeUpdate();
        }
    }

    public void deleteTeacher(int teacherID) throws SQLException {
        String query = "DELETE FROM Teachers WHERE TeacherID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, teacherID);
            pstmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            DatabaseManager dbManager = new DatabaseManager();
            System.out.println("Choose table: \n1. Students \n2. Teachers");
            int tableChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (tableChoice == 1) {
                System.out.println("Choose action: \n1. View Students \n2. Insert Student \n3. Update Student \n4. Delete Student");
                int action = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (action) {
                    case 1 -> dbManager.viewStudents();
                    case 2 -> {
                        System.out.println("Enter First Name:");
                        String firstName = scanner.nextLine();
                        System.out.println("Enter Last Name:");
                        String lastName = scanner.nextLine();
                        System.out.println("Enter Password:");
                        String password = scanner.nextLine();
                        System.out.println("Enter Informatics Grades (comma-separated, e.g., 0,0,0,0):");
                        String informaticsGrades = "[" + scanner.nextLine() + "]";
                        System.out.println("Enter Math Grades (comma-separated, e.g., 0,0,0,0):");
                        String mathGrades = "[" + scanner.nextLine() + "]";
                        System.out.println("Enter English Grades (comma-separated, e.g., 0,0,0,0):");
                        String englishGrades = "[" + scanner.nextLine() + "]";
                        dbManager.insertStudent(firstName, lastName, password, informaticsGrades, mathGrades, englishGrades);

                    }
                    case 3 -> {
                        System.out.println("Enter Student ID:");
                        int studentID = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter field to update (FirstName, LastName, Password, InformaticsGrades, MathGrades, EnglishGrades):");
                        String field = scanner.nextLine();
                        System.out.println("Enter new value:");
                        String newValue = scanner.nextLine();
                        dbManager.updateStudent(studentID, field, newValue);
                    }
                    case 4 -> {
                        System.out.println("Enter Student ID:");
                        int studentID = scanner.nextInt();
                        dbManager.deleteStudent(studentID);
                    }
                }
            } else if (tableChoice == 2) {
                System.out.println("Choose action: \n1. View Teachers \n2. Insert Teacher \n3. Update Teacher \n4. Delete Teacher");
                int action = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (action) {
                    case 1 -> dbManager.viewTeachers();
                    case 2 -> {
                        System.out.println("Enter First Name:");
                        String firstName = scanner.nextLine();
                        System.out.println("Enter Last Name:");
                        String lastName = scanner.nextLine();
                        System.out.println("Enter Password:");
                        String password = scanner.nextLine();
                        System.out.println("Enter Study Degree:");
                        String studyDegree = scanner.nextLine();
                        dbManager.insertTeacher(firstName, lastName, password, studyDegree);
                    }
                    case 3 -> {
                        System.out.println("Enter Teacher ID:");
                        int teacherID = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter field to update (FirstName, LastName, Password, StudyDegree):");
                        String field = scanner.nextLine();
                        System.out.println("Enter new value:");
                        String newValue = scanner.nextLine();
                        dbManager.updateTeacher(teacherID, field, newValue);
                    }
                    case 4 -> {
                        System.out.println("Enter Teacher ID:");
                        int teacherID = scanner.nextInt();
                        dbManager.deleteTeacher(teacherID);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
