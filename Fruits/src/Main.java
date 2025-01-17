import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        FileOutputStream fileOut = new FileOutputStream("output.txt");
        OutputDevice outDev = new OutputDevice(fileOut);


        FileInputStream fileInputStream = new FileInputStream("input.txt");
        InputDevice inputDevice = new InputDevice(System.in);

        Application app = new Application(inputDevice,outDev);
        outDev.writeMessage("Hello and welcome!");


        outDev.writeMessage("Program Arguments:");
        if (args.length == 0) {
            outDev.writeMessage("Error: No arguments provided. Expected 'words', 'numbers', or 'play'.");
            return;
        }

        for(String arg : args){
            outDev.writeMessage(arg);
        }
        outDev.writeMessage("");

        String first_arg=args[0];
        switch (first_arg){
            case "words":
                outDev.writeMessage("Example Histogram for:");
                String line = inputDevice.getLine();
                outDev.writeMessage(line);
                app.exampleHistogram(line);
                break;
            case "numbers":
                outDev.writeMessage("Random sorted array:");
                app.randomSortedArray();
                break;
            case "play":
                if(args.length<2) {
                    outDev.writeMessage("Error: Play requires a second argument.");
                    return;
                }

                try{
                    int numberOfRounds = Integer.parseInt(args[1]);
                    app.run(numberOfRounds);
                }
                catch(NumberFormatException e){
                    outDev.writeMessage("Error: The second argument must be a valid number.");
                }
                break;
            case "fruits":
                Fruit[] fruits = InputDevice.readFruit();

//                app.Stream_function(fruits);
//
//                Fruit maxSugarFruit = app.findFruitWithMostSugar(fruits);
//                System.out.println("Fruit with the most sugar: " + maxSugarFruit);
//
//                app.countFruit(List.of(fruits));
//                app.testFruitComparison(Arrays.asList(fruits));
//
//                for (Fruit fruit : fruits) {
//                   System.out.println(fruit);
//                }
//
//                app.prepareFruit(fruits);
                break;
            case "askUser":
                app.askUserForFile();
                break;
            case "RandomNumbers":
                app.writeRandomNumbers(inputDevice);
            case "Thread":
                int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Fill array with numbers 1 to 1,000,000
        }

        int numThreads = 4;
        try {
            long startTime = System.currentTimeMillis();

            int total = ArraySum.computeSum(array, numThreads);

            long endTime = System.currentTimeMillis();

            System.out.println("Total sum: " + total);
            System.out.println("Elapsed time: " + (endTime - startTime) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            case "BankAccount":
                try {
                    BankAccount account = new BankAccount("account.txt");

                    // Create multiple threads simulating different clients
                    Thread mobileApp = new ClientThread(account, true, 100); // Deposit 100
                    Thread bankOffice = new ClientThread(account, false, 50); // Withdraw 50
                    Thread cardPayment = new ClientThread(account, false, 200); // Withdraw 200
                    Thread atm = new ClientThread(account, true, 300); // Deposit 300

                    // Start all threads
                    mobileApp.start();
                    bankOffice.start();
                    cardPayment.start();
                    atm.start();

                    // Wait for all threads to finish
                    mobileApp.join();
                    bankOffice.join();
                    cardPayment.join();
                    atm.join();

                    // Final balance
                    int finalBalance = account.getBalance();
                    System.out.println("Final account balance: " + finalBalance);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            default:
                outDev.writeMessage("Error: Unknown argument '" + args[0] + "'. Expected 'words', 'numbers', or 'play'.");


        }


    }
}