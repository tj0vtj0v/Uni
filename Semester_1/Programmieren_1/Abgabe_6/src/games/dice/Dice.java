package games.dice;

public class Dice {
    private final String color;
    private int pips;

    public Dice(String color) {
        pips = -1;
        this.color = color;
    }

    public int getPips() {
        return pips;
    }

    public String getColor() {
        return color;
    }

    public void roll() {
        pips = (int) Math.round(1 + Math.random() * 5);
    }
}
