package multiplicationtable;

public class MultiplicationTable {
    public static void main(String[] args) {
        int startValue = 1;
        int endValue = 9;

        System.out.println("Von: " + startValue + " * " + startValue + " = " + (int) Math.pow(startValue, 2));
        System.out.println("Bis: " + endValue + " * " + endValue + " = " + (int) Math.pow(endValue, 2));
        System.out.println();

        for (int i = startValue; i <= endValue; i++) {
            for (int j = startValue; j <= endValue; j++) {
                System.out.print(j * i + "\t");
            }
            System.out.println();
        }
    }
}
