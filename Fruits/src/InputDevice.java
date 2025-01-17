import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collection;
import java.util.Scanner;

public class InputDevice {

    InputStream inputStream;
    Scanner scanner;
    public String getType() {
        return "random";
    }


    public InputDevice(InputStream inputStream) {
        this.inputStream = inputStream;
        this.scanner = new Scanner(inputStream);
    }

    public int nextInt() {
        Random random = new Random();
        return random.nextInt(100)+1;
    }

    public Integer[] getNumbers(int n) {
        Integer[] arr = new Integer[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100) + 1;
        }

        return arr;
    }

    public String getLine(){
        return scanner.nextLine();
     //String[] mess = new String[]{"The quick brown fox jumps over the lazy dog."};
    }

    public static Fruit[] readFruit() {
        Fruit[] fruits = new Fruit[10];

        fruits[0] = new Apple(150, Fruit.Colors.Red, 15, 80);
        fruits[1] = new Apple(130, Fruit.Colors.Green, 12, 85);
        fruits[2] = new Apple(140, Fruit.Colors.Red, 10, 75);

        fruits[3] = new Banana(120, Fruit.Colors.Yellow, 20, 70);
        fruits[4] = new Banana(110, Fruit.Colors.Yellow, 30, 75);
        fruits[5] = new Banana(130, Fruit.Colors.Yellow, 22, 65);

        fruits[6] = new Mango(200, Fruit.Colors.Green, 25, 60, Fruit.Colors.Yellow);
        fruits[7] = new Mango(180, Fruit.Colors.Orange, 23, 65, Fruit.Colors.Red);
        fruits[8] = new Mango(190, Fruit.Colors.Green, 21, 70, Fruit.Colors.Orange);
        fruits[9] = new Mango(210, Fruit.Colors.Yellow, 24, 68, Fruit.Colors.Green);

        return fruits;
    }

    public static Collection<Fruit> readFruitCollection() {
        Collection<Fruit> fruits = new ArrayList<>();

        fruits.add(new Apple(150, Fruit.Colors.Red, 15, 80));
        fruits.add(new Apple(130, Fruit.Colors.Green, 12, 85));
        fruits.add(new Apple(140, Fruit.Colors.Red, 10, 75));

        fruits.add(new Banana(120, Fruit.Colors.Yellow, 20, 70));
        fruits.add(new Banana(110, Fruit.Colors.Yellow, 18, 75));
        fruits.add(new Banana(130, Fruit.Colors.Yellow, 22, 65));

        fruits.add(new Mango(200, Fruit.Colors.Green, 25, 60, Fruit.Colors.Yellow));
        fruits.add(new Mango(180, Fruit.Colors.Orange, 23, 65, Fruit.Colors.Red));
        fruits.add(new Mango(190, Fruit.Colors.Green, 21, 70, Fruit.Colors.Orange));
        fruits.add(new Mango(210, Fruit.Colors.Yellow, 24, 68, Fruit.Colors.Green));

        return fruits;
    }


}