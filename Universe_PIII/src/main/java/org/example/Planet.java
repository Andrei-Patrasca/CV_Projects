package org.example;

class Planet extends Solar_Sistem implements Comparable<Planet> {
    private String name;
    private int rotationSpeed;
    private int diameter;

    public Planet(String name, int rotationSpeed, int diameter) {
        this.name = name;
        this.rotationSpeed = rotationSpeed;
        this.diameter = diameter;
    }

    public String getName() {
        return name;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public int compareTo(Planet o) {
        if (this.diameter > o.diameter) {
            return 1;
        } else if (this.diameter == o.diameter) {
            return Integer.compare(this.rotationSpeed, o.rotationSpeed);
        } else {
            return -1;
        }
    }
}
