package exponentialgrowth;

import java.math.BigInteger;

public class ExponentialGrowth {
    public static void main(String[] args) {
        ExponentialGrowth exponentialGrowth = new ExponentialGrowth();
        exponentialGrowth.printInt();
        exponentialGrowth.printLong();
        exponentialGrowth.printBigInteger();
    }
    private void printInt() {
        for (int x = 1; x <= 25; x++) {
            System.out.println(x + " (int): " + (int) Math.pow(10, x));
        }
    }
    private void printLong() {
        for (int x = 1; x <= 25; x++) {
            System.out.println(x + " (long): " + (long) Math.pow(10, x));
        }
    }
    private void printBigInteger() {
        for (int x = 1; x <= 25; x++) {
            System.out.println(x + " (BigInteger): " + new BigInteger("10").pow(x));
        }
    }
}
