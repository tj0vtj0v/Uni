package square;

public class Square {
    public static void main(String[] args) {
        double length = 20.0;

        double area = length * length;
        double diagonal = length * Math.sqrt(2);

        System.out.println(
        "Seitenlänge des Quadrats: " + length
        + "\nFläche des Quadrats: " + area
        + "\nLänge der Diagonale des Quadrats: " + diagonal);
    }
}
