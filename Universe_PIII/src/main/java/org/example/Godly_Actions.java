package org.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Godly_Actions {
    private List<Planet> planets;
    private List<BlackHole> blackHoles;

    public Godly_Actions() {
        this.planets = new ArrayList<>();
        this.blackHoles = new ArrayList<>();
    }

    public Planet[] inputPlanets() {
        // Add default planets with name, rotation speed, and diameter
        planets.add(new Planet("Mercury", 47, 4879));
        planets.add(new Planet("Venus", 35, 12104));
        planets.add(new Planet("Earth", 30, 12742));
        planets.add(new Planet("Mars", 24, 6779));
        planets.add(new Planet("Jupiter", 13, 139820));
        planets.add(new Planet("Saturn", 9, 116460));
        planets.add(new Planet("Uranus", 7, 50724));
        planets.add(new Planet("Neptune", 5, 49244));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to add custom planets? (yes/no): ");

        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Enter number of custom planets: ");

            int n;
            while (true) {
                try {
                    n = Integer.parseInt(scanner.nextLine());
                    if (n < 0) {
                        System.out.print("Please enter a positive number: ");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Enter a valid number: ");
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.println("Enter details for planet " + (i + 1) + ":");

                System.out.print("Name: ");
                String name = scanner.nextLine();

                int rotationSpeed;
                while (true) {
                    System.out.print("Rotation Speed (km/s): ");
                    try {
                        rotationSpeed = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Enter an integer for rotation speed.");
                    }
                }

                int diameter;
                while (true) {
                    System.out.print("Diameter (in km): ");
                    try {
                        diameter = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Enter an integer for diameter.");
                    }
                }

                planets.add(new Planet(name, rotationSpeed, diameter));
            }
        }

        scanner.close();

        return planets.toArray(new Planet[0]);
    }

    public void addPlanet(Planet planet) {
        if (planet == null) {
            throw new IllegalArgumentException("Planet cannot be null.");
        }
        this.planets.add(planet);
    }

    public void displayPlanets() {
        System.out.println("Current planets:");
        for (Planet planet : planets) {
            System.out.println("Name: " + planet.getName() + ", Rotation Speed: " + planet.getRotationSpeed() + " km/s, Diameter: " + planet.getDiameter() + " km");
        }
    }

    public void sortPlanets() {
        Collections.sort(planets);
        System.out.println("Planets have been sorted based on diameter and rotation speed (in case of ties).");
    }

    public void compareBlackHoles(BlackHole bh1, BlackHole bh2) {
        int result = bh1.compareTo(bh2);
        if (result > 0) {
            System.out.println(bh1 + " is larger than " + bh2);
        } else if (result < 0) {
            System.out.println(bh1 + " is smaller than " + bh2);
        } else {
            System.out.println(bh1 + " is equal in size to " + bh2);
        }
    }



    public void addBlackHole(BlackHole newBlackHole) throws DuplicateObjectException {
        for (BlackHole bh : blackHoles) {
            if (bh.name.equalsIgnoreCase(newBlackHole.name)) {
                throw new DuplicateObjectException("A black hole with the name '" + newBlackHole.name + "' already exists.");
            }
        }
        blackHoles.add(newBlackHole);
        System.out.println("Black hole " + newBlackHole.name + " added successfully.");
    }

    public void inputBlackHoles() {
        // Adding 4 example black holes with default data
        try {
            addBlackHole(new BlackHole("Sagittarius A*", 24000000, 4100000, 1000000));
            addBlackHole(new BlackHole("M87*", 38000000, 6500000, 2000000));
            addBlackHole(new BlackHole("Cygnus X-1", 44000, 15000, 500));
            addBlackHole(new BlackHole("TON 618", 66000000, 66000000, 4000000));
        } catch (DuplicateObjectException e) {
            System.out.println("Duplicate found in default list: " + e.getMessage());
        }

        // Displaying the default black holes
        System.out.println("Default Black Holes:");
        displayBlackHoles();

        // Asking user if they want to add custom black holes
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to add custom black holes? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Enter the number of custom black holes to add: ");
            int n = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < n; i++) {
                System.out.println("Enter details for Black Hole " + (i + 1) + ":");

                System.out.print("Name: ");
                String name = scanner.nextLine();

                int diameter = getIntInput(scanner, "Diameter (in km): ");
                int mass = getIntInput(scanner, "Mass (in solar masses): ");
                int volume = getIntInput(scanner, "Volume (in arbitrary units): ");

                BlackHole newBlackHole = new BlackHole(name, diameter, mass, volume);

                try {
                    // Attempt to add the new black hole
                    addBlackHole(newBlackHole);
                } catch (DuplicateObjectException e) {
                    System.out.println(e.getMessage());

                    // Ask user for resolution
                    System.out.print("Do you want to rename it? (yes to rename / no to skip): ");
                    if (scanner.nextLine().equalsIgnoreCase("yes")) {
                        System.out.print("Enter a new unique name for the black hole: ");
                        name = scanner.nextLine();
                        newBlackHole.name = name;

                        // Try adding again with the new name
                        try {
                            addBlackHole(newBlackHole);
                        } catch (DuplicateObjectException ex) {
                            System.out.println("Unexpected error: " + ex.getMessage());
                        }
                    } else {
                        System.out.println("Skipping duplicate black hole.");
                    }
                }
            }
        }
        scanner.close();
    }

    private int getIntInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }

    public void displayBlackHoles() {
        for (BlackHole bh : blackHoles) {
            System.out.println("Name: " + bh.name + ", Diameter: " + bh.diameter + " km, Mass: " + bh.mass + " solar masses, Volume: " + bh.volume + " units");
        }
    }


    public void simulateSolarSystem_staticTime() {
        // Setup celestial objects
        Planet earth = new Planet("Earth", 1670, 12742);
        Planet jupiter = new Planet("Jupiter", 45000, 139820);
        BlackHole blackHole = new BlackHole("Sagittarius A*", 1000000, 4, 1);
        Asteroids asteroid = new Asteroids(5000);

        // Sequential Simulation
        long sequentialStart = System.currentTimeMillis();
        simulateSequential_st(earth, jupiter, blackHole, asteroid);
        long sequentialEnd = System.currentTimeMillis();

        System.out.println("Time taken for sequential simulation: " + (sequentialEnd - sequentialStart) + " ms\n");

        // Parallel Simulation
        long parallelStart = System.currentTimeMillis();
        simulateParallel_st(earth, jupiter, blackHole, asteroid);
        long parallelEnd = System.currentTimeMillis();

        System.out.println("Time taken for parallel simulation: " + (parallelEnd - parallelStart) + " ms\n");
    }

    private void simulateSequential_st(Planet earth, Planet jupiter, BlackHole blackHole, Asteroids asteroid) {
        System.out.println("Starting Sequential Simulation:");
        for (int i = 0; i < 5; i++) {
            System.out.println(earth.getName() + " is rotating at speed: " + earth.getRotationSpeed() + " km/h");
            System.out.println(jupiter.getName() + " is rotating at speed: " + jupiter.getRotationSpeed() + " km/h");
            System.out.println("Black Hole " + blackHole.name + " is exerting gravitational pull!");
            System.out.println("Asteroid with mass: " + asteroid.mass + " is tumbling through space.\n");

            try {
                Thread.sleep(500); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void simulateParallel_st(Planet earth, Planet jupiter, BlackHole blackHole, Asteroids asteroid) {
        System.out.println("Starting Parallel Simulation:");

        // Create threads
        Thread earthThread = new Thread(() -> simulatePlanetRotation(earth));
        Thread jupiterThread = new Thread(() -> simulatePlanetRotation(jupiter));
        Thread blackHoleThread = new Thread(() -> simulateBlackHoleEffect(blackHole));
        Thread asteroidThread = new Thread(() -> simulateAsteroidMovement(asteroid));

        // Start threads
        earthThread.start();
        jupiterThread.start();
        blackHoleThread.start();
        asteroidThread.start();

        // Wait for threads to complete
        try {
            earthThread.join();
            jupiterThread.join();
            blackHoleThread.join();
            asteroidThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void simulatePlanetRotation(Planet planet) {
        for (int i = 0; i < 5; i++) {
            System.out.println(planet.getName() + " is rotating at speed: " + planet.getRotationSpeed() + " km/h");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void simulateBlackHoleEffect(BlackHole blackHole) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Black Hole " + blackHole.name + " is exerting gravitational pull!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void simulateAsteroidMovement(Asteroids asteroid) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Asteroid with mass: " + asteroid.mass + " is tumbling through space.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void simulateSolarSystem(int numObjects, int numThreads) {
        List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < numObjects; i++) {
            planets.add(new Planet("Planet-" + i, 1000 + i, 10000 + i));
        }

        // Sequential Simulation
        long sequentialStart = System.currentTimeMillis();
        simulateSequential(planets);
        long sequentialEnd = System.currentTimeMillis();

        System.out.println("Time taken for sequential simulation: " + (sequentialEnd - sequentialStart) + " ms\n");

        // Parallel Simulation
        long parallelStart = System.currentTimeMillis();
        simulateParallel(planets, numThreads);
        long parallelEnd = System.currentTimeMillis();

        System.out.println("Time taken for sequential simulation: " + (sequentialEnd - sequentialStart) + " ms\n");
        System.out.println("Time taken for parallel simulation with " + numThreads + " threads: " + (parallelEnd - parallelStart) + " ms\n");
    }

    private void simulateSequential(List<Planet> planets) {
        System.out.println("Starting Sequential Simulation:");
        for (Planet planet : planets) {
            simulateComputation(planet);
        }
    }

    private void simulateParallel(List<Planet> planets, int numThreads) {
        System.out.println("Starting Parallel Simulation:");
        int chunkSize = (int) Math.ceil((double) planets.size() / numThreads);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, planets.size());

            Thread thread = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    simulateComputation(planets.get(j));
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void simulateComputation(Planet planet) {
        // Simulate computational workload
        long result = 0;
        for (int i = 0; i < 100000; i++) {
            result += (planet.getDiameter() * i) % 12345; // Arbitrary computation
        }
        System.out.println(planet.getName() + " computation completed. Result: " + result);
    }


    public List<Planet> getPlanets() {
        return planets;
    }

    public List<BlackHole> getBlackHoles() {
        return blackHoles;
    }
}
