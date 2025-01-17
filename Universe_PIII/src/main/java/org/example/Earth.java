package org.example;

public class Earth extends Planet implements LifeSustinable,Orbitable{
    public Earth(String name, int rotationSpeed, int diameter) {
        super(name, rotationSpeed, diameter);
    }

    @Override
    public boolean canSustain() {
        return true;
    }

    @Override
    public void haslife() {
        System.out.println("Yes ,has lots of life");
    }

    @Override
    public boolean isOrbitable() {
        return true;
    }

    @Override
    public void orbitsAroundSomething() {
        System.out.println("Orbits around sun");
    }
}

