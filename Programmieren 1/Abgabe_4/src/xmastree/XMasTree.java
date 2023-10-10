package xmastree;

public class XMasTree {
    public static void main(String[] args) {
        int height = 5;
        int numberOfStars = 0;

        for (int i = 1; i < height; i++) {
            System.out.print(" ");
        }
        System.out.println("+");

        for (int line = height; line > 0; line--) {
            numberOfStars = numberOfStars == 0 ? 1 : numberOfStars + 2;

            for (int space = line - 1; space > 0; space--) {
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
