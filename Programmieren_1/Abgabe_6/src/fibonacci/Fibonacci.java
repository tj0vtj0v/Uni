package fibonacci;

import java.util.Arrays;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 21;
        int m = 25;

        System.out.println("fibonacci(" + n + ") = " + fibonacci(n));
        System.out.println("fibonacciLoop(" + n + ") = " + fibonacciLoop(n));
        System.out.println("fibonacciSequence(" + n + ", " + m + ") = " + Arrays.toString(fibonacciSequence(n, m)));
    }

    private static int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    private static int fibonacciLoop(int n) {
        int lastIteration = 1;
        int thisIteration = 1;
        int temp;

        for (int depth = 2; depth < n; depth++) {
            temp = lastIteration;
            lastIteration = thisIteration;
            thisIteration += temp;
        }

        return thisIteration;
    }

    private static int[] fibonacciSequence(int n, int m) {
        int[] sequence = new int[m - n + 1];

        for (int fibonacciNumber = n; fibonacciNumber <= m; fibonacciNumber++) {
            sequence[fibonacciNumber - n] = fibonacci(fibonacciNumber);
        }

        return sequence;
    }
}
