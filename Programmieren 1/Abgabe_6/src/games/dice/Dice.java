package games.dice;

public class Dice {
    private final String color;
    private int pips;

    public Dice(String color) {
        this.pips = -1;
        this.color = color;
    }

    public int getPips() {
        return this.pips;
    }

    public String getColor() {
        return this.color;
    }

    public void roll() {
        this.pips = (int) Math.round(1 + Math.random() * 5);
    }
}
