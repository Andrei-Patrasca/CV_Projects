public class Banana extends Fruit implements Peelable{
    private boolean peeled = true;
    private Colors color =Colors.Yellow;

    public Banana(int weight, Colors color, int sugar_content, int water_content) {
        super(weight, color, sugar_content, water_content);
    }

    @Override
    public boolean hasPeel() {

        return peeled;
    }

    @Override
    public void peelOff() {
        peeled=false;

    }
}
