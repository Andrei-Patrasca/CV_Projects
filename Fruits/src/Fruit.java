public abstract class Fruit implements Comparable<Fruit> {

    public int weight;
    public Colors color;
    public int sugar_content;
    public int water_content;
    public Fruit(int weight, Colors color, int sugar_content, int water_content) {
        this.weight = weight;
        this.color = color;
        this.sugar_content = sugar_content;
        this.water_content = water_content;
    }

    public int getSugarContent() {
        return sugar_content;
    }

    public int getWaterContent() {
        return water_content;
    }

    @Override
    public int compareTo(Fruit o) {
        if (this.weight > o.weight) {
            return 1;
        } else if (this.weight < o.weight) {
            return -1;
        }
        else {
            if(this.sugar_content > o.sugar_content) {
                return 1;
            }
            if(this.sugar_content < o.sugar_content) {
                return -1;
            }
        }
        return 0;
    }

    public enum Colors {
        Green,
        Blue,
        Yellow,
        Orange,
        Red;
    }

}
