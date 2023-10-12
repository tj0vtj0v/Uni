package xmastree;

public class XMasTree {
    public static void main(String[] args) {
        int height = 5;
        int numberOfStars = 0; // " = 0" für den Wichtel
        int numberOfSpaces;

        for (int i = 1; i < height; i++) {
            System.out.print(" ");
        }
        System.out.println("+");

        for (int line = 1; line <= height; line++) {
            numberOfStars = 2 * line -1;
            numberOfSpaces = height - line;

            for (int space = numberOfSpaces; space > 0; space--) {
                System.out.print(" ");
            }

            for (int star = numberOfStars; star > 0; star--) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 1; i < height; i++) {
            System.out.print(" ");
        }
        System.out.println("U");
    }
}
