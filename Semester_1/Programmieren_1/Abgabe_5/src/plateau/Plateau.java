package plateau;


public class Plateau {
    public static void main(String[] args) {
        int[] altitudes = {10, 17, 17, 17, 17, 17, 11, 10, 14, 10, 11, 11, 11, 9, 13, 15, 16, 16, 13};
        int plateauStart = 0;
        int plateauLength = 0;
        int height = 0;
        boolean onPlateau = false;

        for (int index = 1; index < altitudes.length; index++) {
            if (altitudes[index - 1] < altitudes[index]) {
                plateauStart = index;
                plateauLength = 1;
                onPlateau = true;

            } else if (altitudes[index - 1] == altitudes[index] && onPlateau) {
                plateauLength++;
                height = altitudes[index];

            } else if (onPlateau && plateauLength >= 2) {
                onPlateau = false;

                System.out.println("Index: " + plateauStart + " Height: " + height + " Length: " + plateauLength);
            } else {
                onPlateau = false;
            }
        }
    }
}
