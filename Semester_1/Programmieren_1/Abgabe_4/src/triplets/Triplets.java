package triplets;

public class Triplets {
    public static void main(String[] args) {
        int dice1;
        int dice2;
        int dice3;
        int sumDice1 = 0;
        int sumDice2 = 0;
        int sumDice3 = 0;

        boolean tripletOfOnes = false;
        boolean tripletOfTwos = false;
        boolean tripletOfThrees = false;

        int counter = 1;

        while (true) {
            dice1 = 1 + (int) (Math.random() * 6);
            dice2 = 1 + (int) (Math.random() * 6);
            dice3 = 1 + (int) (Math.random() * 6);
            sumDice1 += dice1;
            sumDice2 += dice2;
            sumDice3 += dice3;

            System.out.println("Wurf " + counter + ": " + dice1 + ", " + dice2 + ", " + dice3);

            if (dice1 == dice2 && dice2 == dice3) {
                System.out.println("Trippel!");

                switch (dice1) {
                    case 1:
                        tripletOfOnes = true;
                        break;
                    case 2:
                        tripletOfTwos = true;
                        break;
                    case 3:
                        tripletOfThrees = true;
                        break;
                }

                if (tripletOfOnes && tripletOfTwos && tripletOfThrees) {
                    break;
                }
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }

            counter++;
        }

        System.out.println("\nEinser-, Zweier- und Dreier - Trippel waren dabei!");
        System.out.println("Mittelwert Würfel 1: " + ((double) sumDice1 / counter));
        System.out.println("Mittelwert Würfel 2: " + ((double) sumDice2 / counter));
        System.out.println("Mittelwert Würfel 3: " + ((double) sumDice3 / counter));
    }
}
