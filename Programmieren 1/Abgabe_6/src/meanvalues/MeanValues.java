package meanvalues;

public class MeanValues {
    public static void main(String[] args) {
        int a = 8;
        int b = 8;
        int c = 9;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("Mean: " + mean(a, b, c));
        System.out.println("Median: " + median(a, b, c));
        System.out.println("Modal: " + modal(a, b, c));
    }

    private static double mean(int a, int b, int c) {
        return ((double) Math.round((a + b + c) * 10 / 3f) / 10); // '10f' hinten -> rundungsprobleme
    }

    private static int median(int a, int b, int c) {
        int min = Math.min(Math.min(a, b), c);
        int max = Math.max(Math.max(a, b), c);

        return (a + b + c) - (min + max);
    }

    private static int modal(int a, int b, int c) {
        if (b == c) {
            return b;
        } else {
            return a;
        }
    }
}
