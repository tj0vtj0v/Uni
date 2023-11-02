package games.bin;

import games.dice.Dice;

public class DicePlayer {
    public static void main(String[] args) {
        Dice blueDice = new Dice("blau");
        Dice greenDice = new Dice("grün");

        for (int i = 0; i < 3; i++) {
            blueDice.roll();
            greenDice.roll();

            System.out.println("Würfel " + blueDice.getColor() + ": " + blueDice.getPips());
            System.out.println("Würfel " + greenDice.getColor() + ": " + greenDice.getPips());
            System.out.println();
        }
    }
}
