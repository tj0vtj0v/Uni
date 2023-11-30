package prime;

public class PrimeNumberGenerator {
    public static void main(String[] args) throws IllegalPrimeNumberArgumentException {
        PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator();

        long startNumber = 1_000_000_000_000L;
        int numbers = 100;
        primeNumberGenerator.printPrimeNumbers(startNumber, numbers);
    }

    boolean numberIsPrime(long number) throws IllegalPrimeNumberArgumentException {
        if (number <= 0) {
            throw new IllegalPrimeNumberArgumentException("number needs to be at least 1. Argument was: " + number);
        }

        long limit = (long) Math.floor(Math.sqrt(number));
        for (long divisor = 2; divisor <= limit; divisor++) {
            if (divisor > 1 && (number % divisor) == 0) {
                return false;
            }
        }

        return number >= 2;
    }

    long findNextPrimeNumber(long startNumber) throws IllegalPrimeNumberArgumentException {
        for (long number = startNumber; number <= Math.abs(startNumber) * 2; number++) {
            if (numberIsPrime(number)) {
                return number;
            }
        }

        return -1;
    }

    void printPrimeNumbers(long startNumber, int numbers) throws IllegalPrimeNumberArgumentException {
        long lastPrime = startNumber - 1;

        for (int prime = 0; prime < numbers; prime++) {
            try {
                lastPrime = findNextPrimeNumber(lastPrime + 1);
            } catch (IllegalPrimeNumberArgumentException e) {
                lastPrime = 2;
            }

            System.out.println(lastPrime);
        }
    }
}
