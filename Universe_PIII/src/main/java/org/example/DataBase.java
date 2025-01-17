package org.example;

import java.sql.*;
import java.util.Scanner;

public class DataBase {
    public static String url = "jdbc:mysql://localhost:3306/Universe";
    public static String username = "root";
    public static String password = "Urs5658?!";

    public static void main(String[] args) {
        interfaces();
    }

    public static void interfaces() {
        while (true) {
            System.out.println("1. Insert Data\n2. Delete Data\n3. Update Data\n4. View Data\n5. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your choice (1-5): ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    insert_data();
                    break;
                case "2":
                    delete_data();
                    break;
                case "3":
                    update_data();
                    break;
                case "4":
                    view_data();
                    break;
                case "5":
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("You entered an invalid choice. Please try again.\n");
            }
        }
    }

    public static void insert_data() {
        Scanner scanner = new Scanner(System.in);
        int tableChoice = select_table(scanner);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement;

            switch (tableChoice) {
                case 1: // Solar_System
                    System.out.print("Enter Solar System Name: ");
                    String solarSystemName = scanner.nextLine();
                    System.out.print("Enter Sun Name: ");
                    String sunName = scanner.nextLine();
                    System.out.print("Enter Number of Planets: ");
                    int numPlanets = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO Solar_System (name, sun, number_of_planets) VALUES (?, ?, ?)");
                    preparedStatement.setString(1, solarSystemName);
                    preparedStatement.setString(2, sunName);
                    preparedStatement.setInt(3, numPlanets);
                    preparedStatement.executeUpdate();
                    System.out.println("Data inserted into Solar_System.");
                    break;

                case 2: // Planet
                    System.out.print("Enter Planet Name: ");
                    String planetName = scanner.nextLine();
                    System.out.print("Enter Rotation Speed: ");
                    int rotationSpeed = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Diameter: ");
                    int diameter = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Solar System ID: ");
                    int solarSystemId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO Planet (name, rotation_speed, diameter, solar_system_id) VALUES (?, ?, ?, ?)");
                    preparedStatement.setString(1, planetName);
                    preparedStatement.setInt(2, rotationSpeed);
                    preparedStatement.setInt(3, diameter);
                    preparedStatement.setInt(4, solarSystemId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data inserted into Planet.");
                    break;



                case 3: // Asteroid
                    System.out.print("Enter Asteroid Mass: ");
                    int asteroidMass = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Solar System ID: ");
                    int asteroidSolarSystemId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO Asteroid (mass, solar_system_id) VALUES (?, ?)");
                    preparedStatement.setInt(1, asteroidMass);
                    preparedStatement.setInt(2, asteroidSolarSystemId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data inserted into Asteroid.");
                    break;

                case 4: // Black Hole
                    System.out.print("Enter Black Hole Name: ");
                    String blackHoleName = scanner.nextLine();
                    System.out.print("Enter Diameter: ");
                    int blackHoleDiameter = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Mass: ");
                    int blackHoleMass = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Volume: ");
                    int blackHoleVolume = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Solar System ID: ");
                    int blackHoleSolarSystemId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO BlackHole (name, diameter, mass, volume, solar_system_id) VALUES (?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, blackHoleName);
                    preparedStatement.setInt(2, blackHoleDiameter);
                    preparedStatement.setInt(3, blackHoleMass);
                    preparedStatement.setInt(4, blackHoleVolume);
                    preparedStatement.setInt(5, blackHoleSolarSystemId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data inserted into Black Hole.");
                    break;

                case 5: // Moon
                    System.out.print("Enter Moon Name: ");
                    String moonName = scanner.nextLine();
                    System.out.print("Enter Planet ID: ");
                    int moonPlanetId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO Moon (name, planet_id) VALUES (?, ?)");
                    preparedStatement.setString(1, moonName);
                    preparedStatement.setInt(2, moonPlanetId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data inserted into Moon.");
                    break;


                default:
                    System.out.println("Invalid table choice.");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete_data() {
        Scanner scanner = new Scanner(System.in);
        int tableChoice = select_table(scanner);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement;

            switch (tableChoice) {
                case 1: // Solar_System
                    System.out.print("Enter Solar System ID to delete: ");
                    int solarSystemId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement("DELETE FROM Solar_System WHERE solar_system_id = ?");
                    preparedStatement.setInt(1, solarSystemId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data deleted from Solar_System.");
                    break;

                case 2: // Planet
                    System.out.print("Enter Planet ID to delete: ");
                    int planetId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement("DELETE FROM Planet WHERE planet_id = ?");
                    preparedStatement.setInt(1, planetId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data deleted from Planet.");
                    break;

                case 3: // Asteroid
                    System.out.print("Enter Asteroid ID to delete: ");
                    int asteroidId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement("DELETE FROM Asteroid WHERE asteroid_id = ?");
                    preparedStatement.setInt(1, asteroidId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data deleted from Asteroid.");
                    break;

                case 4: // Black Hole
                    System.out.print("Enter Black Hole ID to delete: ");
                    int blackHoleId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement("DELETE FROM BlackHole WHERE blackhole_id = ?");
                    preparedStatement.setInt(1, blackHoleId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data deleted from Black Hole.");
                    break;

                case 5: // Moon
                    System.out.print("Enter Moon ID to delete: ");
                    int moonId = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement("DELETE FROM Moon WHERE moon_id = ?");
                    preparedStatement.setInt(1, moonId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data deleted from Moon.");
                    break;

                default:
                    System.out.println("Invalid table choice.");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update_data() {
        Scanner scanner = new Scanner(System.in);
        int tableChoice = select_table(scanner);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement;

            switch (tableChoice) {
                case 1: // Solar_System
                    System.out.print("Enter Solar System ID to update: ");
                    int solarSystemId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new Sun Name: ");
                    String newSun = scanner.nextLine();

                    preparedStatement = connection.prepareStatement(
                            "UPDATE Solar_System SET name = ?, sun = ? WHERE solar_system_id = ?");
                    preparedStatement.setString(1, newName);
                    preparedStatement.setString(2, newSun);
                    preparedStatement.setInt(3, solarSystemId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data updated in Solar_System.");
                    break;

                case 2: // Planet
                    System.out.print("Enter Planet ID to update: ");
                    int planetId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new Rotation Speed: ");
                    int newRotationSpeed = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement(
                            "UPDATE Planet SET rotation_speed = ? WHERE planet_id = ?");
                    preparedStatement.setInt(1, newRotationSpeed);
                    preparedStatement.setInt(2, planetId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data updated in Planet.");
                    break;

                case 3: // Asteroid
                    System.out.print("Enter Asteroid ID to update: ");
                    int asteroidId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new Mass: ");
                    int newAsteroidMass = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement(
                            "UPDATE Asteroid SET mass = ? WHERE asteroid_id = ?");
                    preparedStatement.setInt(1, newAsteroidMass);
                    preparedStatement.setInt(2, asteroidId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data updated in Asteroid.");
                    break;

                case 4: // Black Hole
                    System.out.print("Enter Black Hole ID to update: ");
                    int blackHoleId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new Name: ");
                    String newBlackHoleName = scanner.nextLine();
                    System.out.print("Enter new Diameter: ");
                    int newBlackHoleDiameter = Integer.parseInt(scanner.nextLine());

                    preparedStatement = connection.prepareStatement(
                            "UPDATE BlackHole SET name = ?, diameter = ? WHERE blackhole_id = ?");
                    preparedStatement.setString(1, newBlackHoleName);
                    preparedStatement.setInt(2, newBlackHoleDiameter);
                    preparedStatement.setInt(3, blackHoleId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data updated in Black Hole.");
                    break;

                case 5: // Moon
                    System.out.print("Enter Moon ID to update: ");
                    int moonId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new Name: ");
                    String newMoonName = scanner.nextLine();

                    preparedStatement = connection.prepareStatement(
                            "UPDATE Moon SET name = ? WHERE moon_id = ?");
                    preparedStatement.setString(1, newMoonName);
                    preparedStatement.setInt(2, moonId);
                    preparedStatement.executeUpdate();
                    System.out.println("Data updated in Moon.");
                    break;

                default:
                    System.out.println("Invalid table choice.");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void view_data() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which table do you want to view?");
        System.out.println("1 Solar System");
        System.out.println("2 Planet");
        System.out.println("3 Asteroid");
        System.out.println("4 Black Hole");
        System.out.println("5 Moon");
        System.out.print("Input your choice (1, 2, ... 5): ");
        int tableChoice = Integer.parseInt(scanner.nextLine());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            switch (tableChoice) {
                case 1: // Solar System
                    resultSet = statement.executeQuery("SELECT * FROM Solar_System;");
                    while (resultSet.next()) {
                        System.out.println("Solar System ID: " + resultSet.getInt("solar_system_id"));
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Sun: " + resultSet.getString("sun"));
                        System.out.println("Number of Planets: " + resultSet.getInt("number_of_planets"));
                        System.out.println("----------------------------");
                    }
                    break;

                case 2: // Planet
                    resultSet = statement.executeQuery("SELECT * FROM Planet;");
                    while (resultSet.next()) {
                        System.out.println("Planet ID: " + resultSet.getInt("planet_id"));
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Rotation Speed: " + resultSet.getInt("rotation_speed"));
                        System.out.println("Diameter: " + resultSet.getInt("diameter"));
                        System.out.println("Solar System ID: " + resultSet.getInt("solar_system_id"));
                        System.out.println("----------------------------");
                    }
                    break;

                case 3: // Asteroid
                    resultSet = statement.executeQuery("SELECT * FROM Asteroid;");
                    while (resultSet.next()) {
                        System.out.println("Asteroid ID: " + resultSet.getInt("asteroid_id"));
                        System.out.println("Mass: " + resultSet.getInt("mass"));
                        System.out.println("Solar System ID: " + resultSet.getInt("solar_system_id"));
                        System.out.println("----------------------------");
                    }
                    break;

                case 4: // Black Hole
                    resultSet = statement.executeQuery("SELECT * FROM BlackHole;");
                    while (resultSet.next()) {
                        System.out.println("Black Hole ID: " + resultSet.getInt("blackhole_id"));
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Diameter: " + resultSet.getInt("diameter"));
                        System.out.println("Mass: " + resultSet.getInt("mass"));
                        System.out.println("Volume: " + resultSet.getInt("volume"));
                        System.out.println("Solar System ID: " + resultSet.getInt("solar_system_id"));
                        System.out.println("----------------------------");
                    }
                    break;

                case 5: // Moon
                    resultSet = statement.executeQuery("SELECT * FROM Moon;");
                    while (resultSet.next()) {
                        System.out.println("Moon ID: " + resultSet.getInt("moon_id"));
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Planet ID: " + resultSet.getInt("planet_id"));
                        System.out.println("----------------------------");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid table number (1-5).");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static int select_table(Scanner scanner) {
        System.out.println("Which table do you choose?");
        System.out.println("1. Solar System");
        System.out.println("2. Planet");
        System.out.println("3. Asteroids");
        System.out.println("4. Black Hole");
        System.out.println("5. Moon");
        System.out.print("Input your choice (1-5): ");
        return Integer.parseInt(scanner.nextLine());
    }
}
