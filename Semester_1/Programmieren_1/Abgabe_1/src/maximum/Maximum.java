package maximum;

public class Maximum {
    public static void main(String[] args) {
        int x = 100;
        int y = 200;
        int z = -20;

        int maximum;
        if (x > y) {
            maximum = x;
        } else {
            maximum = y;
        }
        if (maximum < z) {
            maximum = z;
        }

        int counter = 0;
        if (0 > x) {
            counter++;
        }
        if (0 > y) {
            counter++;
        }
        if (0 > z) {
            counter++;
        }

        System.out.println("x ist " + x);
        System.out.println("y ist " + y);
        System.out.println("z ist " + z);
        System.out.println("(1) Das Maximum ist " + maximum);
        System.out.println("(2) Die Anzahl der negativen Zahlen ist " + counter);
    }
}
