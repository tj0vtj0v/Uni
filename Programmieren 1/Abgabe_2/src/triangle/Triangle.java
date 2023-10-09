package triangle;

public class Triangle {
    public static void main(String[] args) {
        double a = 5.0;
        double b = 5.0;
        double c = 7.071;

        boolean rightTriangle = Math.abs(Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) < 0.1;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

        if (rightTriangle) {
            System.out.println("Das Dreieck ist rechtwinklig.");
        } else {
            System.out.println("Das Dreieck ist nicht rechtwinklig.");
        }
    }
}
