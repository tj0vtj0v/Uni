package temperatures;

public class Temperatures {
    public static void main(String[] args) {
        double[] minimumTemperatures = new double[30];
        double[] maximumTemperatures = new double[30];

        for (int i = 0; i < 30; i++) {
            maximumTemperatures[i] = 5 + Math.random() * 15;
            minimumTemperatures[i] = Math.random() * 10;
        }

        System.out.println("Unsortiert:");
        for (int i = 0; i < 30; i++) {
            System.out.println("Tag " + (i + 1) + ": Minimum = " + minimumTemperatures[i] + ", Maximum = " + maximumTemperatures[i]);
        }

        double save;
        for (int i = 0; i < 30; i++) {
            if (maximumTemperatures[i] < minimumTemperatures[i]) {
                save = maximumTemperatures[i];
                maximumTemperatures[i] = minimumTemperatures[i];
                minimumTemperatures[i] = save;
            }
        }

        System.out.println("\nSortiert:");
        for (int i = 0; i < 30; i++) {
            System.out.println("Tag " + (i + 1) + ": Minimum = " + minimumTemperatures[i] + ", Maximum = " + maximumTemperatures[i]);
        }
    }
}
