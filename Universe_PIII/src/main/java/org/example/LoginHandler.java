package org.example;
//package org.example.fx_lab9;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginHandler {

    private final String CSV_FILE = "users.csv";

    /**
     * Validates the username and password against the CSV file.
     *
     * @param username the username to check
     * @param password the password to check
     * @return true if the credentials are valid, false otherwise
     * @throws IOException if there is an issue reading the CSV file
     */
    public boolean validateCredentials(String username, String password) throws IOException {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            return false; // If the file doesn't exist, no valid users exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Skip the header
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String storedUsername = data[0];
                    String storedPassword = data[2];

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean validateCredentialsByEmail(String email, String password) throws IOException {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            return false; // No valid users exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Skip the header
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String storedEmail = data[1];
                    String storedPassword = data[2];

                    if (storedEmail.equals(email) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}


