package schafkopfen.game;


import schafkopfen.gameview.*;

class CardSorter {
    private final Card[] hand;
    private static final String[] CARD_COLORS = {"Schelln", "Herz", "Gruen", "Eichel"};
    private static final String[] CARD_RANKS = {"7", "8", "9", "10", "U", "O", "K", "A"};
    private static final String[] BAVARIAN_COLORS = {"Schelln", "Gruen", "Eichel", "Herz"};

    CardSorter(Card[] hand) {
        this.hand = hand;
    }

    void selectionSort() {
        for (int insertionIndex = 0; insertionIndex < hand.length - 1; insertionIndex++) {
            swap(insertionIndex, findHighestCardStartingFromIndex(insertionIndex + 1));
        }
    }

    void bubbleSort() {
        boolean sorted;
        int unsortedLength;

        for (int run = 0; run < hand.length; run++) {
            sorted = true;
            unsortedLength = hand.length - run;

            for (int index = 1; index < unsortedLength; index++) {
                if (firstCardIsHigherThanSecondCard(hand[index], hand[index - 1])) {
                    swap(index - 1, index);
                    sorted = false;
                }
            }

            if (sorted) {
                break;
            }
        }
    }

    private int findHighestCardStartingFromIndex(int startIndex) {
        int highestCardIndex = startIndex - 1;

        for (int index = startIndex; index < hand.length; index++) {
            if (firstCardIsHigherThanSecondCard(hand[index], hand[highestCardIndex])) {
                highestCardIndex = index;
            }
        }

        return highestCardIndex;
    }

    private int indexOf(String toFind, String[] list) {
        for (int index = 0; index < list.length; index++) {
            if (list[index].equals(toFind)) {
                return index;
            }
        }

        return -1;
    }


    }

    private boolean firstCardIsOfHigherRankThanSecondCard(Card firstCard, Card secondCard) {
        return indexOf(firstCard.rank, CARD_RANKS) > indexOf(secondCard.rank, CARD_RANKS);
    }

    private boolean firstCardIsOfHigherColorThanSecondCard(Card firstCard, Card secondCard) {
        return indexOf(firstCard.color, CARD_COLORS) > indexOf(secondCard.color, CARD_COLORS);
    }

    private boolean firstCardIsBavarianHigherThanSecondCard(Card firstCard, Card secondCard) {
        if (indexOf(firstCard.color, BAVARIAN_COLORS) == indexOf(secondCard.color, BAVARIAN_COLORS)) {
            return firstCardIsOfHigherRankThanSecondCard(firstCard, secondCard);
        }

        return indexOf(firstCard.color, BAVARIAN_COLORS) > indexOf(secondCard.color, BAVARIAN_COLORS);
    }

    private void swap(int index1, int index2) {
        if (index1 != index2) {
            Card temp = hand[index1];
            hand[index1] = hand[index2];
            hand[index2] = temp;
        }
    }
}
