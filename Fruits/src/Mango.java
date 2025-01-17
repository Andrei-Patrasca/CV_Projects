public class Mango extends Fruit implements Peelable,SeedRemovable{
    private Colors color;
    public boolean seeded = true;
    private boolean peeled=true;

    public Mango(int weight, Colors color, int sugar_content, int water_content, Colors color1) {
        super(weight, color, sugar_content, water_content);
        this.color = color1;
    }

    @Override
    public boolean hasSeeds() {
        return seeded;
    }
    @Override
    public void removeSeeds(){
        seeded = false;
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
