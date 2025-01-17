package org.example;
import java.io.File;


public class Main {


    public static void main(String[] args) {
        Godly_Actions godlyActions = new Godly_Actions();

        System.out.println("Hello ,let's explore the World");
        System.out.println("Program Arguments:");
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            return;
        }

        for(String arg : args){
            System.out.println(arg);
        }

        String first_arg=args[0];
        switch (first_arg){
            case "hello":
                System.out.println("Hello World");
                break;
            case "Planets":
                if(args.length==1){
                    godlyActions.inputPlanets();
                    System.out.println("Planets have been added:\n");
                    godlyActions.displayPlanets();
                    break;
                }
                if(args.length==2){
                    if(args[1].equals("Sort")){
                        godlyActions.inputPlanets();
                        godlyActions.sortPlanets();
                        godlyActions.displayPlanets();
                        break;
                    }
                    else if(args[1].equals("File"))
                    {
                        InputDevice device = new InputDevice(System.in);
                        device.inputPlanetsFile("Planets.txt");
                        device.displayFilePlanets("Planets.txt");
                        //File file = new File("data.txt");
                        //System.out.println("File path: " + file.getAbsolutePath());

                        break;
                    }
                }
                break;
            case "BlackHole":
                if(args.length==1){
                    godlyActions.inputBlackHoles();
                    System.out.println("\nAll Black Holes:");
                    godlyActions.displayBlackHoles();
                    break;
                }

                if (args.length > 2 && args[1].equals("Compare"))
                {
                    BlackHole b1 = new BlackHole("Phoenix A", 39000000, 9800000, 3500000);
                    BlackHole b2 = new BlackHole("IC 1101*", 40000000, 9000000, 3600000);
                    godlyActions.compareBlackHoles(b1,b2);
                }
                break;
            case "Simulate_st":
                godlyActions.simulateSolarSystem_staticTime();
                break;

            case "Simulate":
                if (args.length < 3) {
                    System.out.println("Error: Provide the number of objects and threads as arguments.");
                    break;
                }

                int numObjects = Integer.parseInt(args[1]);
                int numThreads = Integer.parseInt(args[2]);
                godlyActions.simulateSolarSystem(numObjects, numThreads);
                break;

            case "Fx":
                RegistrationLoginApp.main(args);
                break;

            case "DB":

                DataBase db = new DataBase();
                db.interfaces();
                break;

            case "Test":
                SpaceTest test = new SpaceTest();
                test.testFunctionalityBlackHoleMass();
                break;
            default:
                System.out.println("Nuu uhh ,something went wrong");
                break;








        }

    }
}