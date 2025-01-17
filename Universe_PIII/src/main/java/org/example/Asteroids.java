package org.example;

public class Asteroids extends Solar_Sistem implements LifeSustinable,Orbitable{
    public int mass;
    public Asteroids(int mass){
        this.mass = mass;
    }

    @Override
    public boolean canSustain() {
        return false;
    }

    @Override
    public void haslife() {
        System.out.println("Has no life");
    }

    @Override
    public boolean isOrbitable() {
        return false;
    }

    @Override
    public void orbitsAroundSomething() {
        System.out.println("Probably not");
    }

    public double getMass() {
        return mass;
    }
}
