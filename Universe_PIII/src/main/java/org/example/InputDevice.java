package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.InputStream;

public class InputDevice {

    InputStream inputStream;
    Scanner scanner;
    public String getType() {
        return "random";
    }


    public InputDevice(InputStream inputStream) {
        this.inputStream = inputStream;
        this.scanner = new Scanner(inputStream);
    }

    public int nextInt() {
        Random random = new Random();
        return random.nextInt(100)+1;
    }

    public String getLine(){
        return scanner.nextLine();
    }

    public void inputPlanetsFile(String filePath) {
        InputDevice inputDevice = new InputDevice(System.in); // Use InputDevice for user input

        System.out.print("Enter number of planets to input: ");
        int n;
        while (true) {
            try {
                n = Integer.parseInt(inputDevice.getLine()); // Use InputDevice for integer input
                if (n < 0) {
                    System.out.print("Please enter a positive number: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid number: ");
            }
        }

        // Open file in append mode
        try (FileOutputStream fileOut = new FileOutputStream(filePath, true); // Append mode
             OutputDevice outDev = new OutputDevice(fileOut)) {

            for (int i = 0; i < n; i++) {
                System.out.println("Enter details for planet " + (i + 1) + ":");

                System.out.print("Name: ");
                String name = inputDevice.getLine(); // Read name from InputDevice

                int rotationSpeed;
                while (true) {
                    System.out.print("Rotation Speed (km/s): ");
                    try {
                        rotationSpeed = Integer.parseInt(inputDevice.getLine()); // Read rotation speed from InputDevice
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Enter an integer for rotation speed.");
                    }
                }

                int diameter;
                while (true) {
                    System.out.print("Diameter (in km): ");
                    try {
                        diameter = Integer.parseInt(inputDevice.getLine()); // Read diameter from InputDevice
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Enter an integer for diameter.");
                    }
                }

                // Write the planet data in the required format, using commas only
                String planetData = name + "," + rotationSpeed + "," + diameter;
                outDev.writeMessage(planetData + "\n"); // Add newline after each entry
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }



    public void displayFilePlanets(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File not found at: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String rotationSpeed = parts[1].trim();
                    String diameter = parts[2].trim();
                    System.out.println("Name: " + name + ", Rotation Speed: " + rotationSpeed + " km/s, Diameter: " + diameter + " km");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }


    public Object getScanner() {
        return null;
    }
}
