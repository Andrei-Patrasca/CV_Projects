package org.example;

public class BlackHole extends Solar_Sistem implements Comparable<BlackHole> {
    public int diameter;
    public int mass;
    public int volume;
    public String name;

    public BlackHole(String name, int diameter, int mass, int volume) {
        this.name = name;
        this.diameter = diameter;
        this.mass = mass;
        this.volume = volume;
    }

    @Override
    public int compareTo(BlackHole other) {
        if (this.diameter > other.diameter) {
            return 1;
        } else if (this.diameter == other.diameter) {
            return Integer.compare(this.mass, other.mass);
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "BlackHole{name='" + name + "', diameter=" + diameter + " km, mass=" + mass + " solar masses, volume=" + volume + " units}";
    }


    public int getDiameter() {
        return diameter;
    }

    public int getMass() {
        return mass;
    }

    public int getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }
}

