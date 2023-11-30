package approximation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Pi {
    private final MathContext mathContext;

    public static void main(String[] args) {
        System.out.println(new Pi(25).approximatePi(100));
    }

    Pi(int precision) {
        mathContext = new MathContext(precision, RoundingMode.HALF_UP);
    }

    BigDecimal approximatePi(int n) {
        BigDecimal pi = BigDecimal.ZERO;

        BigDecimal prefix;
        BigDecimal bracketOne;
        BigDecimal bracketTwo;
        BigDecimal bracketThree;
        BigDecimal bracketFour;

        for (int k = 0; k < n; k++) {
            prefix = BigDecimal.ONE.divide(new BigDecimal(16).pow(k), mathContext);
            bracketOne = new BigDecimal(4).divide(new BigDecimal(8).multiply(BigDecimal.valueOf(k)).add(BigDecimal.ONE), mathContext);
            bracketTwo = new BigDecimal(2).divide(new BigDecimal(8).multiply(BigDecimal.valueOf(k)).add(new BigDecimal(4)), mathContext);
            bracketThree = BigDecimal.ONE.divide(new BigDecimal(8).multiply(BigDecimal.valueOf(k)).add(new BigDecimal(5)), mathContext);
            bracketFour = BigDecimal.ONE.divide(new BigDecimal(8).multiply(BigDecimal.valueOf(k)).add(new BigDecimal(6)), mathContext);

            pi = pi.add(prefix.multiply(bracketOne.subtract(bracketTwo).subtract(bracketThree).subtract(bracketFour), mathContext), mathContext);
        }

        return pi;
    }
}
