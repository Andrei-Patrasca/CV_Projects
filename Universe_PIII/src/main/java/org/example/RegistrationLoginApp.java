package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RegistrationLoginApp extends Application {

    private Stage primaryStage;
    private final String CSV_FILE = "users.csv";



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Show the login form initially
        showLoginForm();

        primaryStage.setTitle("Registration and Login Form");
        primaryStage.show();
    }

    private void showLoginForm() {
        Label title = new Label("Login");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        Button switchToRegisterButton = new Button("Don't have an account? Register");
        switchToRegisterButton.setOnAction(e -> showRegistrationForm());

        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                messageLabel.setText("All fields are required.");
                messageLabel.setStyle("-fx-text-fill: red;");
            } else {
                LoginHandler loginHandler = new LoginHandler();
                try {
                    if (loginHandler.validateCredentialsByEmail(email, password)) {
                        messageLabel.setText("Login successful!");
                        messageLabel.setStyle("-fx-text-fill: green;");
                        showDashboardPanel(); // Transition to the dashboard panel
                    } else {
                        messageLabel.setText("Invalid email or password.");
                        messageLabel.setStyle("-fx-text-fill: red;");
                    }
                } catch (IOException ex) {
                    messageLabel.setText("Error reading user data. Try again.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            }
        });

        VBox layout = new VBox(10, title, emailField, passwordField, loginButton, switchToRegisterButton, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));

        primaryStage.setScene(new Scene(layout, 400, 300));
    }

    private void showRegistrationForm() {
        Label title = new Label("Register");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button registerButton = new Button("Register");
        Label messageLabel = new Label();

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                messageLabel.setText("All fields are required.");
                messageLabel.setStyle("-fx-text-fill: red;");
            } else if (!email.contains("@")) {
                messageLabel.setText("Invalid email address.");
                messageLabel.setStyle("-fx-text-fill: red;");
            } else {
                try {
                    saveUserData(username, email, password);
                    messageLabel.setText("Registration successful!");
                    messageLabel.setStyle("-fx-text-fill: green;");

                    // Redirect to Login Form after registration
                    showLoginForm();
                } catch (IOException ex) {
                    messageLabel.setText("Error saving data. Try again.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            }
        });

        VBox layout = new VBox(10, title, usernameField, emailField, passwordField, registerButton, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));

        primaryStage.setScene(new Scene(layout, 400, 300));
    }

    void saveUserData(String username, String email, String password) throws IOException {
        // Ensure the CSV file exists
        File file = new File(CSV_FILE);
        boolean fileExists = file.exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            // If the file doesn't exist, write the header
            if (!fileExists) {
                writer.println("Username,Email,Password");
            }
            // Append the new user data
            writer.printf("%s,%s,%s%n", username, email, password);
        }
    }

    private void showDashboardPanel() {
        Label title = new Label("Manage Universe:");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button button1 = new Button("Thread Planets");
        button1.setOnAction(e -> {
            System.out.println("Please imput the number of oblect (o):\n");
            Scanner scanner = new Scanner(System.in);
            int numObjects = Integer.parseInt(scanner.nextLine());
            System.out.println("And the number of threads (t):");
            int numThreads = Integer.parseInt(scanner.nextLine());
            Godly_Actions godlyActions = new Godly_Actions();
            godlyActions.simulateSolarSystem(numObjects, numThreads);
        });

        Button button2 = new Button("Planets");
        button2.setOnAction(e -> {
            Godly_Actions godlyActions = new Godly_Actions();
            godlyActions.inputPlanets();
            System.out.println("Planets have been added:\n");
            godlyActions.displayPlanets();
        });

        Button button3 = new Button("Black Holes");
        button3.setOnAction(e -> {
            Godly_Actions godlyActions = new Godly_Actions();
            godlyActions.inputBlackHoles();
            System.out.println("\nAll Black Holes:");
            godlyActions.displayBlackHoles();
        });

        Button button4 = new Button("Manage Data Base");
        button4.setOnAction(e -> {
            DataBase db = new DataBase();
            db.interfaces();
        });

        VBox layout = new VBox(10, title, button1, button2, button3, button4);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));

        primaryStage.setScene(new Scene(layout, 400, 300));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
