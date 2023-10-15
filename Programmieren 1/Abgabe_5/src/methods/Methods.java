package methods;

public class Methods {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        int c = 20;

        printInfo();
        printVariable("int a", a);
        printVariable("int b", b);
        printVariable("int c", c);
        printBoolean("even(a)", even(a));
        printRoundedDouble("pythagoras(a, b)", pythagoras(a, b));
        printInt("minimum(a, b, c)", minimum(a, b, c));
        printBoolean("Minimum von a, b und c ist ungerade", even(minimum(a, b, c) + 1));
        printInfo();
    }

    private static boolean even(int number) {
        return number % 2 == 0;
    }

    private static double pythagoras(int length1, int length2) {
        return Math.sqrt(Math.pow(length1, 2) + Math.pow(length2, 2));
    }

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static void printInfo() {
        System.out.println("--- Abgabe 5 ---");
    }

    private static void printVariable(String name, int value) {
        System.out.println(name + ": " + value);
    }

    private static void printInt(String name, int value) {
        System.out.println(name + ": " + value);
    }

    private static void printBoolean(String name, boolean value) {
        System.out.println(name + ": " + value);
    }

    private static void printRoundedDouble(String name, double value) {
        System.out.println(name + ": " + (Math.round(value * 100) / 100f));
    }


}
