package org.example;

class AsteroidThread extends Thread {
    private Asteroids asteroid;

    public AsteroidThread(Asteroids asteroid) {
        this.asteroid = asteroid;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Asteroid with mass: " + asteroid.mass + " is tumbling through space.");
            try {
                Thread.sleep(1200); // Simulate movement delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getAsteroid() {
        return asteroid;
    }
}
