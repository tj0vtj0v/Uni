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
        if (this.pieces > 0) {
            this.pieces--;
        }
    }

    public boolean hasPieces() {
        return this.pieces > 0;
    }

    public void print() {
        System.out.println(this.name + " mit " + this.pieces + " Stücken. Restwert: " + remainingValue() + " Euro.");
    }

    private float remainingValue() {
        return Math.round(this.pieces * this.pricePerPiece * 10) / 10f;
    }
}
