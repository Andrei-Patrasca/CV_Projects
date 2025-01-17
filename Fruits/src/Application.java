
import java.io.*;
import java.util.*;

public class Application {
    InputDevice inpDev;
    OutputDevice outDev;
    //Application application;
    private
    int nr1,nr2;

    public Application(InputDevice inpDev, OutputDevice outDev)
    {
        this.inpDev=inpDev;
        this.outDev=outDev;
    }

    public void run(int n) throws IOException {
         nr1 = inpDev.nextInt();
         nr2 = inpDev.nextInt();
        String message="The game begins";//Today’s lucky numbers are: " + nr1 + ", " + nr2;
        outDev.writeMessage(message);
        //System.out.println("Today’s lucky numbers are: " + nr1 + ", " + nr2);
        //Application application = this;
        this.playGame(n);
    }

    public void playGame(int n)
    {
        //int player1 = nr1;
        //int player2 = nr2;
        int ok=0,h=0,s=0,cp1=0,cp2=0,c=0;

        while(cp1<n && cp2<n)
        {
            int player1=inpDev.nextInt();
            int player2=inpDev.nextInt();
            c++;
            System.out.println("Round"+c+":");
            if (player1 > player2) {
                ok = 1;
                h = player1;
                s = player2;
            } else {
                h = player2;
                s = player1;
                ok = 2;
            }


            if ((ok == 1 && h % s != 0) || (ok == 2 && h % s == 0 && s >= 10)) {
                System.out.println("Player1 wins!!");
                cp1++;
            } else if ((ok == 2 && h % s != 0) || (ok == 1 && h % s == 0 && s >= 10)) {
                System.out.println("Player2 wins!!");
                cp2++;
            }
            System.out.println("Score:"+cp1+","+cp2);
        }

        if(cp1==n) {
            System.out.println("Player1 is the final winner!!");
        }
        else
        {
            System.out.println("Player2 is the final winner!!");
        }
    }


    public Integer[] sortNumbers(Integer[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public void randomSortedArray() {

        InputDevice inputDevice=new InputDevice(System.in);
        Integer[] arr=inputDevice.getNumbers(10);
        System.out.println(Arrays.toString(arr));
        arr=sortNumbers(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static Integer[] resizeArray(Integer[] oldArray, int newSize) {
        Integer[] newArray = new Integer[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        Arrays.fill(newArray, oldArray.length, newArray.length, 0);
        return newArray;
    }

    public static Integer[] wordSizeHistogram(String sentence) {
        // Split the sentence into words (using spaces as delimiters)
        String[] words = sentence.split("\\s+"); // Split by one or more spaces

        // Initialize an array with a fixed size, assume words up to 20 characters long
        Integer[] histogram = new Integer[21]; // Supports word lengths from 0 to 20 initially
        Arrays.fill(histogram, 0);

        // Iterate over the words and calculate the length of each word
        for (String word : words) {
            // Remove punctuation and trim whitespace
            word = word.replaceAll("[^a-zA-Z0-9]", "").trim();

            int length = word.length(); // Get the length of the cleaned word

            // Resize the histogram array if the word length is greater than the current array size
            if (length >= histogram.length) {
                histogram = resizeArray(histogram, length + 1); // Resize to fit the current word
            }

            // Increment the count for this word length if the word length is not zero
            if (length > 0) {
                histogram[length]++;
            }
        }

        return histogram;
    }

    public void exampleHistogram(String sentence) throws IOException {


        Integer[] wordLengths = wordSizeHistogram(sentence);

        this.outDev.writeMessage(Arrays.toString(wordLengths));
    }

    int computeWeight(Fruit[] fruits)
    {
        int totalWeight=0;
        for(Fruit f: fruits)
        {
            totalWeight+=f.weight;
        }
        return totalWeight;
    }



    public int computeWeight(Collection<Fruit> fruits) {
        int totalWeight = 0;
        for (Fruit f : fruits) {
            totalWeight += f.weight;
        }
        return totalWeight;
    }


    int computeSugarContent(Fruit[] fruits){
        int totalSugar=0;
        for(Fruit f: fruits)
        {
            totalSugar=f.sugar_content;
        }
        return totalSugar;
    }

    int computeSugarContent(Collection<Fruit> fruits){
        int totalSugar=0;
        for(Fruit f: fruits)
        {
            totalSugar=f.sugar_content;
        }
        return totalSugar;
    }

    void prepareFruit(Fruit[] fruits)
    {
        for(Fruit f: fruits)
        {
            if(f instanceof Peelable && f instanceof SeedRemovable)
            {
                ((Peelable)f).peelOff();
                ((SeedRemovable)f).removeSeeds();
                System.out.println("Mango cleaned");
            }
            else if(f instanceof Peelable)
            {
                ((Peelable)f).peelOff();
                System.out.println("Banana peeled");
            }
            else if (f instanceof SeedRemovable)
            {
                ((SeedRemovable)f).removeSeeds();
                System.out.println("Apple cleaned of seeds");
            }
        }
    }

    void prepareFruit(Collection<Fruit> fruits)
    {
        for(Fruit f: fruits)
        {
            if(f instanceof Peelable && f instanceof SeedRemovable)
            {
                ((Peelable)f).peelOff();
                ((SeedRemovable)f).removeSeeds();
                System.out.println("Mango cleaned");
            }
            else if(f instanceof Peelable)
            {
                ((Peelable)f).peelOff();
                System.out.println("Banana peeled");
            }
            else if (f instanceof SeedRemovable)
            {
                ((SeedRemovable)f).removeSeeds();
                System.out.println("Apple cleaned of seeds");
            }
        }
    }


    void countFruit(Collection<Fruit> fruits)
    {
        int a=0 ,b=0, m=0;
        for( Fruit f: fruits)
        {
            if (f instanceof Apple) {
                a++;
            }
            if (f instanceof Banana) {
                b++;
            }
            if (f instanceof Mango) {
                m++;
            }
        }
        System.out.println("Counted fruits:\nApples: "+a+"\nBananas: "+b+"\nMangos: "+m);
    }



    void testFruitComparison(Collection<Fruit> fruits) {
        if (fruits == null || fruits.isEmpty()) {
            System.out.println("The collection is empty or null.");
            return;
        }

        // Find the minimum fruit using the compareTo method
        Fruit minFruit = Collections.min(fruits);

        System.out.println("The minimum fruit is: " + minFruit);
    }


    public static <T extends Fruit> T findFruitWithMostSugar(T[] fruits) {
        if (fruits == null || fruits.length == 0) {
            throw new IllegalArgumentException("Fruit array must not be null or empty");
        }

        T maxSugarFruit = fruits[0];

        for (T fruit : fruits) {
            if (fruit.getSugarContent() > maxSugarFruit.getSugarContent()) {
                maxSugarFruit = fruit;
            }
        }

        return maxSugarFruit;
    }

    void Stream_function(Fruit[] fruits) {
        // 1. Convert array to list
        List<Fruit> fruitsList = Arrays.asList(fruits);

        // 2. Filter out fruits that have sugar content greater than 20
        List<Fruit> filteredFruits = fruitsList.stream()
                .filter(fruit -> fruit.getSugarContent() >= 20)
                .toList(); // Collecting filtered fruits into a list

        System.out.println("Filtered Fruits (sugar >= 20): " + filteredFruits);

        // 3. Sum the sugar content of all fruits (no filter)
        int totalSugar = fruitsList.stream()
                .mapToInt(Fruit::getSugarContent)
                .sum();

        System.out.println("Total Sugar Content of All Fruits: " + totalSugar + "g");

        // 4. Compute the ratio between sugar and water for all fruits
        List<Double> sugarWaterRatios = fruitsList.stream()
                .map(fruit -> {
                    double sugar = fruit.getSugarContent();
                    double water = fruit.getWaterContent();
                    return water != 0 ? sugar / water : 0; // Prevent division by zero
                })
                .toList(); // Collecting the ratios into a list

        System.out.println("Sugar to Water Ratios for All Fruits: " + sugarWaterRatios);
    }

    public static void askUserForFile() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter the file name: ");
            String fileName = scanner.nextLine();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                break; // Exit the loop once the file has been successfully read
            } catch (IOException e) {
                System.out.println("File not found or could not be opened. Please try again.");
            }
        }

        scanner.close();
    }


    public static void writeRandomNumbers(InputDevice inputDevice) {
        Random random = new Random();

        while (true) {
            System.out.print("Please enter the file name to write random numbers: ");
            String fileName = inputDevice.getLine();  // Get filename input using InputDevice

            try {
                // Attempt to open the file in read mode to check if it exists
                new FileReader(fileName).close();

                // If the file exists, open it in write mode and write 10 random numbers
                try (FileWriter writer = new FileWriter(fileName)) {
                    for (int i = 0; i < 10; i++) {
                        int randomNumber = random.nextInt(100); // Generate a random number between 0 and 99
                        writer.write(randomNumber + "\n"); // Write the number followed by a new line
                    }
                    System.out.println("10 random numbers have been successfully written to the file.");
                    break; // Exit the loop after successful write
                }
            } catch (FileNotFoundException e) {
                System.out.println("File does not exist. Please enter an existing file name.");
            } catch (IOException e) {
                System.out.println("Could not open or write to file. Please try another filename.");
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }



}
