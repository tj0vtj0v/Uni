package schafkopfen.game;

import schafkopfen.gameview.*;

import java.util.Random;

public class Performance {
    private final Card[] deck;
    private final Card[] hand;
    private Random random;
    private boolean testHasStarted;

    Performance() {
        deck = Card.createFreshDeckWith32Cards();
        hand = new Card[8];
        testHasStarted = false;
        random = new Random();
    }

    public static void main(String[] args) {
        new Performance().startTests();
    }

    private void testSortingAlgorithmAndPrintResults(String algorithm, int runs) {
        System.out.println("Algorithm: " + algorithm + ", runs: " + runs);

        for (int run = 1; run <= runs; run++) {
            for (int index = 0; index < hand.length; index++) {
                hand[index] = deck[random.nextInt(deck.length)];
            }

            if (testHasStarted && hand[0].toString().equals("Eichel O")) {
                continue;
            }

            CardSorter cardSorter = new CardSorter(hand);
            if (algorithm.equals("Selectionsort")) {
                cardSorter.selectionSort();
            } else if (algorithm.equals("Bubblesort")) {
                cardSorter.bubbleSort();
            } else {
                throw new IllegalArgumentException("Illegal argument: " + algorithm);
            }
        }

        System.out.println("Test ended.");
    }

    private void startTests() {
        random = new Random(1234);
        testSortingAlgorithmAndPrintResults("Selectionsort", 1_000);
        testSortingAlgorithmAndPrintResults("Bubblesort", 1_000);
        int seed = (int) System.currentTimeMillis();
        random.setSeed(seed);
        testSortingAlgorithmAndPrintResults("Selectionsort", 500_000);
        random.setSeed(seed);
        testSortingAlgorithmAndPrintResults("Bubblesort", 500_000);
    }
}
