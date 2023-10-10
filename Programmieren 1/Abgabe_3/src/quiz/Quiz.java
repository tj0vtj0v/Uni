package quiz;

import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input;

        long durationInSecs;
        long currentTimeInMilliseconds = System.currentTimeMillis();
        int random = 1 + (int) (Math.random() * 1_000);
        int tries = 0;

        while (true) {
            System.out.print("Bitte eine Zahl eingeben: ");
            input = scanner.nextInt();
            tries++;

            if (input == random) {
                durationInSecs = Math.round((System.currentTimeMillis() - currentTimeInMilliseconds) / 1000);
                break;
            } else if (input > random) {
                System.out.println("Ihre Zahl ist zu hoch!");
            } else {
                System.out.println("Ihre Zahl ist zu niedrig!");
            }

        }

        System.out.println("\nTreffer!");
        System.out.println("Sie haben " + tries + " Versuche benötigt.");
        System.out.println("Sie haben " + durationInSecs + " Sekunden benötigt.");

        scanner.close();
    }
}
