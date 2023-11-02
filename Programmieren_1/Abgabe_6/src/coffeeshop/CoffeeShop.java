package coffeeshop;

public class CoffeeShop {
    public static void main(String[] args) {
        Cake applePie = new Cake("Apfelkuchen", 12, 2.8);
        Cake cheeseCake = new Cake("Käsekuchen", 8, 3.2);

        if (applePie.hasPieces()) {
            applePie.takePiece();
        }

        if (cheeseCake.hasPieces()) {
            cheeseCake.takePiece();
        }

        applePie.print();
        cheeseCake.print();
    }
}
