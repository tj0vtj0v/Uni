package schafkopfen.game;

import schafkopfen.gameview.*;

class Player {
    private final Animations animations;
    private final String name;
    private final int playerNumber;
    private Card[] hand;

    Player(Animations animations, String name, int playerNumber) {
        this.animations = animations;
        this.name = name;
        this.playerNumber = playerNumber;
        hand = new Card[8];
    }

    void sortCards() {
        if (playerNumber % 2 == 0) {
            new CardSorter(hand).bubbleSort();
        } else {
            new CardSorter(hand).selectionSort();
        }

        animations.showPlayersHand(playerNumber, name, hand);
    }

    void setHand(Card[] hand) {
        this.hand = hand;
    }
}
