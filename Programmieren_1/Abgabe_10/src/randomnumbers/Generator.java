package randomnumbers;

import java.util.Random;

public class Generator {
    public static void main(String[] args) {
    }

    private int[] generateRandomNumbers(int size, int minimumInt, int maximumInt, long seed) {
        int[] randoms = new int[size];
        Random random = new Random(seed);

        for (int randomNumber = 0; randomNumber < size; randomNumber++) {
            randoms[randomNumber] = random.nextInt(maximumInt - minimumInt + 1) + minimumInt;
        }

        return randoms;
    }
}
