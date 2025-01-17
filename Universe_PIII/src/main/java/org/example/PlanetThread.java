package org.example;

class PlanetThread extends Thread {
    private Planet planet;

    public PlanetThread(Planet planet) {
        this.planet = planet;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(planet.getName() + " is rotating at speed: " + planet.getRotationSpeed() + " km/h");
            try {
                Thread.sleep(1000); // Simulate rotation delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getPlanet() {
        return planet;
    }
}
