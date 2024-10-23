package schafkopfen.game;

import schafkopfen.gameview.*;

import java.util.Arrays;
import java.util.Random;

class Dealer {
    private final Animations animations;
    private final Card[] deck;

    private final Player[] players;

    private Dealer() {
        animations = new Animations();
        deck = Card.createFreshDeckWith32Cards();
        players = new Player[]{
                new Player(animations, "Fritz", 1),
                new Player(animations, "Max", 2),
                new Player(animations, "Franz", 3),
                new Player(animations, "Paul", 4)
        };
    }

    public static void main(String[] args) {
        new Dealer().play();
    }

    private void play() {
        try {
            animations.showCardDeckStraight(deck);
            sleep(2000);
            animations.showShuffleAnimation();
            shuffle(System.currentTimeMillis());
            animations.showCardDeckCurved(deck);
            sleep(2000);
            dealCards();

        } catch (IllegalCardDeckException e) {
            System.exit(1);
        }
    }

    private void shuffle(long seed) {
        Random random = new Random(seed);

        for (int index = 0; index < deck.length; index++) {
            swap(index, random.nextInt(deck.length));
        }
    }

    private void swap(int index1, int index2) {
        if (index1 != index2) {
            Card temp = deck[index1];
            deck[index1] = deck[index2];
            deck[index2] = temp;
        }
    }

    private void dealCards() throws IllegalCardDeckException {
        checkCardDeckValidity();

        for (int index = 0; index < players.length; index++) {
            players[index].setHand(Arrays.copyOfRange(deck, index * 8, index * 8 + 8));
            players[index].sortCards();
        }
    }

    private void checkCardDeckValidity() throws IllegalCardDeckException {
        for (int index = 0; index < deck.length; index++) {
            if (deck[index] == null) {
                throw new IllegalCardDeckException("Faulty index: " + index);
            }

            for (int reoccurrenceIndex = index + 1; reoccurrenceIndex < deck.length; reoccurrenceIndex++) {
                if (deck[index].toString().equals(deck[reoccurrenceIndex].toString())) {
                    throw new IllegalCardDeckException("Reoccurring cards index: " + index + ", " + reoccurrenceIndex);
                }
            }
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {
        }
    }
}
