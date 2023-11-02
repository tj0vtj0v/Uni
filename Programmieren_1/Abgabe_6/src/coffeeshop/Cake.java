package coffeeshop;

public class Cake {
    String name;
    int pieces;
    double pricePerPiece;

    Cake(String name, int pieces, double pricePerPiece) {
        this.name = name;
        this.pieces = pieces;
        this.pricePerPiece = pricePerPiece;
    }

    public void takePiece() {
        if (pieces > 0) {
            pieces--;
        }
    }

    public boolean hasPieces() {
        return pieces > 0;
    }

    public void print() {
        System.out.println(name + " mit " + pieces + " Stücken. Restwert: " + remainingValue() + " Euro.");
    }

    private float remainingValue() {
        return Math.round(pieces * pricePerPiece * 10) / 10f;
    }
}
