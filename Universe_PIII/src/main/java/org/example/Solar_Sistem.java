package org.example;

public class Solar_Sistem {
    //Solar_Sistem(Planet panet);
    private Planet[] planets;
    private Moon[] moons;
    private String name;
    private String sun;
    private int numberOfPlanets;
    public Solar_Sistem(String sun,Planet planet, String name, int numberOfPlanets) {
        this.sun = sun;
        this.planets = planets;
        this.name = name;
        this.numberOfPlanets = numberOfPlanets;
    }

    public Solar_Sistem() {

    }

    public Planet[] getPlanets() {
        return planets;
    }

    public Moon[] getMoons() {
        return moons;
    }

    public String getName() {
        return name;
    }

    public String getSun() {
        return sun;
    }

    public int getNumberOfPlanets() {
        return numberOfPlanets;
    }
}

