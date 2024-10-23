package minimum;

public class Minimum {
    public static void main(String[] args) {
        int x = 100;
        int y = 200;
        int z = -20;

        int minimum = x < y ? x : y;
        minimum = minimum < z ? minimum : z;

        System.out.println("x ist " + x);
        System.out.println("y ist " + y);
        System.out.println("z ist " + z);
        System.out.println("Das Minimum ist " + minimum);
    }
}
