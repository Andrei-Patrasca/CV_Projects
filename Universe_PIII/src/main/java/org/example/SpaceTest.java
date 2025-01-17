package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {

    @Test
    void testAsteroidsConstructor() {
        Asteroids asteroid = new Asteroids(100);
        assertEquals(100, asteroid.getMass(), "Asteroid mass should be correctly initialized.");
    }

    @Test
    void testAsteroidThreadConstructor() {
        Asteroids asteroid = new Asteroids(200);
        AsteroidThread thread = new AsteroidThread(asteroid);
        assertEquals(asteroid, thread.getAsteroid(), "AsteroidThread should correctly hold the asteroid instance.");
    }

    @Test
    void testBlackHoleConstructor() {
        BlackHole blackHole = new BlackHole("Singularity", 5000, 100000, 8000);
        assertEquals("Singularity", blackHole.getName(), "Black hole name should match.");
        assertEquals(5000, blackHole.getDiameter(), "Black hole diameter should match.");
        assertEquals(100000, blackHole.getMass(), "Black hole mass should match.");
        assertEquals(8000, blackHole.getVolume(), "Black hole volume should match.");
    }

    @Test
    void testBlackHoleThreadConstructor() {
        BlackHole blackHole = new BlackHole("EventHorizon", 6000, 150000, 10000);
        BlackHoleThread thread = new BlackHoleThread(blackHole);
        assertEquals(blackHole, thread.getBlackHole(), "BlackHoleThread should hold the black hole instance correctly.");
    }

    @Test
    void testDuplicateObjectExceptionConstructor() {
        DuplicateObjectException exception = new DuplicateObjectException("Duplicate object detected");
        assertEquals("Duplicate object detected", exception.getMessage(), "Exception message should match.");
    }

    @Test
    void testEarthConstructor() {
        var earth = new Earth("Earth", 1670, 12742);
        assertEquals("Earth", earth.getName(), "Earth name should match.");
        assertEquals(1670, earth.getRotationSpeed(), "Earth rotation speed should match.");
        assertEquals(12742, earth.getDiameter(), "Earth diameter should match.");
    }

    @Test
    void testGodlyActionsConstructor() {
        Godly_Actions godlyActions = new Godly_Actions();
        assertNotNull(godlyActions.getPlanets(), "Planets list should be initialized.");
        assertNotNull(godlyActions.getBlackHoles(), "Black holes list should be initialized.");
    }

    @Test
    void testInputDeviceConstructor() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Test Input".getBytes());
        InputDevice inputDevice = new InputDevice(inputStream);
        assertNotNull(inputDevice.getScanner(), "Scanner should be initialized.");
    }

    @Test
    void testMoonConstructor() {
        Moon moon = new Moon("Luna", 16, 3474);
        assertEquals("Luna", moon.getName(), "Moon name should match.");
        assertEquals(16, moon.getRotationSpeed(), "Moon rotation speed should match.");
        assertEquals(3474, moon.getDiameter(), "Moon diameter should match.");
    }

    @Test
    void testOutputDeviceConstructor() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputDevice outputDevice = new OutputDevice(outputStream);
        assertNotNull(outputDevice.getOutputStream(), "Output stream should be initialized.");
    }

    @Test
    void testPlanetConstructor() {
        Planet planet = new Planet("Mars", 868, 6792);
        assertEquals("Mars", planet.getName(), "Planet name should match.");
        assertEquals(868, planet.getRotationSpeed(), "Planet rotation speed should match.");
        assertEquals(6792, planet.getDiameter(), "Planet diameter should match.");
    }

    @Test
    void testPlanetThreadConstructor() {
        Planet planet = new Planet("Jupiter", 45300, 139820);
        PlanetThread thread = new PlanetThread(planet);
        assertEquals(planet, thread.getPlanet(), "PlanetThread should correctly hold the planet instance.");
    }

    @Test
    void testSolarSystemConstructor() {
        Planet earth = new Planet("Earth", 1670, 12742);
        Solar_Sistem solarSystem = new Solar_Sistem("Sun", earth, "Milky Way", 8);
        assertEquals("Sun", solarSystem.getSun(), "Sun should match.");
        assertEquals(earth, solarSystem.getPlanets(), "Planets should match.");
        assertEquals("Milky Way", solarSystem.getName(), "Solar system name should match.");
        assertEquals(8, solarSystem.getNumberOfPlanets(), "Number of planets should match.");
    }

    @Test
    void testFunctionalityGodlyActionsAddPlanet() {
        Godly_Actions godlyActions = new Godly_Actions();
        Planet planet = new Planet("Venus", 1770, 12104);
        godlyActions.addPlanet(planet);
        assertTrue(godlyActions.getPlanets().contains(planet), "Planet should be added to the list.");
    }

    @Test
    void testFunctionalityEarthRotationSpeed() {
        Earth earth = new Earth("Earth", 1670, 12742);
        assertEquals(1670, earth.getRotationSpeed(), "Earth rotation speed should return the correct value.");
    }

    @Test
    void testFunctionalityBlackHoleMass() {
        BlackHole blackHole = new BlackHole("Void", 7000, 500000, 15000);
        assertEquals(500000, blackHole.getMass(), "Black hole mass should return the correct value.");
    }

    @Test
    void testFunctionalityDisplayPlanetsWithPlanets() {
        Godly_Actions godlyActions = new Godly_Actions();
        godlyActions.addPlanet(new Planet("Mercury", 10, 4879));
        godlyActions.addPlanet(new Planet("Venus", 6, 12104));

        // Capture the output to verify it
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        godlyActions.displayPlanets();

        String output = outputStream.toString().trim();
        String expectedOutput = "Current planets:\n"
                + "Name: Mercury, Rotation Speed: 10 km/s, Diameter: 4879 km\n"
                + "Name: Venus, Rotation Speed: 6 km/s, Diameter: 12104 km";
        assertEquals(expectedOutput, output, "Display should list all added planets with correct details.");
    }


//    @Test
//    void tesFunctionalitySaveUserData() throws IOException {
//        // Use a temporary file for the test
//        File tempFile = File.createTempFile("test_users", ".csv");
//        tempFile.deleteOnExit(); // Ensure the file is deleted after the test
//
//        RegistrationLoginApp app = new RegistrationLoginApp(tempFile.getAbsolutePath());
//        app.saveUserData("testuser", "testuser@example.com", "password123");
//
//        // Verify the file content
//        List<String> lines = Files.readAllLines(tempFile.toPath());
//        assertEquals(2, lines.size(), "File should contain a header and one user entry.");
//        assertEquals("Username,Email,Password", lines.get(0), "Header row should be correct.");
//        assertEquals("testuser,testuser@example.com,password123", lines.get(1), "User data should be saved correctly.");
//    }

}
