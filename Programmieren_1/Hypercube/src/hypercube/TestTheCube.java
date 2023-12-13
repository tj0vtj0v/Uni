package hypercube;

public class TestTheCube {

    public static void main(String[] args) {
        int dimension = 2;
        int length = 4;
        Hypercube hypercube2D = new Hypercube(dimension, length);
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                hypercube2D.set(new int[]{i, j}, count++);
            }
        }
        System.out.println(hypercube2D);

        dimension = 3;
        length = 2;
        Hypercube hypercube3D = new Hypercube(dimension, length);
        count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    hypercube3D.set(new int[]{i, j, k}, count++);
                }
            }
        }
        System.out.println(hypercube3D);


        dimension = 4;
        length = 12;
        Hypercube hypercube4D = new Hypercube(dimension, length);
        count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    for (int l = 0; l < length; l++) {
                        hypercube4D.set(new int[]{i, j, k, l}, count++);
                    }
                }
            }
        }
        System.out.println(hypercube4D);
    }
}
