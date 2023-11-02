package coordinates;

public class Coordinates {
    public static void main(String[] args) {
        int highestX = 6;
        int highestY = 5;

        for (int x = 1; x <= highestX; x++) {
            for (int y = 1; y <= highestY; y++) {
                System.out.print("(" + x + "," + y + ") ");
            }
            System.out.println();
        }
    }
}
