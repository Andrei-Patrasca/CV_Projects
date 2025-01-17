public class Apple extends Fruit implements SeedRemovable {

    private Colors color;
    private boolean seeded = true;
    public Apple(int weight, Colors color, int sugar_content, int water_content) {
        super(weight, color, sugar_content, water_content);
    }


    @Override
    public boolean hasSeeds() {
        return seeded;
    }

    @Override
    public void removeSeeds() {
        seeded = false;
    }
}
