package experiments;

import java.util.Random;

public class Experiments {
    private static final int REPETITIONS = 1_000_000;
    private final Binomial binomial;
    private final int seed;
    private Random random;

    Experiments() {
        binomial = new Binomial();
        seed = 1;
        random = new Random();
    }

    public static void main(String[] args) {
        new Experiments().execute();
    }

    private long evaluate(boolean thirdPowerShort) {
        random = new Random(seed);
        long startTime = System.nanoTime();

        for (int repetition = 0; repetition < REPETITIONS; repetition++) {
            double a = random.nextDouble(10);
            double b = random.nextDouble(10);

            if (thirdPowerShort) {
                binomial.thirdPowerShort(a, b);
            } else {
                binomial.thirdPowerLong(a, b);
            }
        }

        return System.nanoTime() - startTime;
    }

    private void printResult(String theorem, long duration) {
        System.out.println(theorem);
        System.out.println("Wiederholungen: " + REPETITIONS);
        System.out.println("Zeit benötigt: " + duration + " ns");
        System.out.println("Durchschnitt pro Berechnung: " + duration / REPETITIONS + " ns");
        System.out.println();
    }

    private void execute() {
        evaluate(true);
        evaluate(false);

        printResult("(a + b)³", evaluate(true));
        printResult("a³ + 3a²b + 3ab² + b³", evaluate(false));
    }
}
