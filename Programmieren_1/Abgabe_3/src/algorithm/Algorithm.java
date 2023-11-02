package algorithm;

public class Algorithm {
    public static void main(String[] args) {
        int a = 205;
        int b = 10;
        int i = a;
        int j = b;

        while (i > 0 && j > 0) {
            if (i > j) {
                i = i - j;
            } else {
                j = j - i;
            }
        }

        System.out.println("int a: " + a);
        System.out.println("int b: " + b);
        if (j == 0) {
            System.out.println("Ergebnis: " + i);
        } else {
            System.out.println("Ergebnis: " + j);
        }
    }
}
