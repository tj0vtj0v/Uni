package hypercube;

import java.util.Arrays;

public class Hypercube {
    private final Hypercube[] content;

    Hypercube(int dimension, int length) {
        content = new Hypercube[length];
        for (int i = 0; i < content.length; i++) {
            content[i] = dimension > 0 ? new Hypercube(dimension - 1, length) : new HypercubeValue();
        }
    }

    public void set(int[] coordinate, Integer value) {
        int cubeIndex = coordinate[0];

        if (coordinate.length > 1) {
            content[cubeIndex].set(Arrays.copyOfRange(coordinate, 1, coordinate.length), value);
        } else {
            System.out.println("-"+content[cubeIndex]+"-");

            if (content[cubeIndex] instanceof HypercubeValue) {
                ((HypercubeValue) content[cubeIndex]).value = value;
                System.out.println(((HypercubeValue) content[cubeIndex]).value);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int index = 0; index < content.length; index++) {
            output.append(content[index].toString());
            if (content[index] instanceof HypercubeValue) {
                output.replace(0, 1, "[" + index + ", ");
            } else {
                output.replace(0, 1, "[" + index + ", ");
            }
            output.append("\n");
        }

        return output.toString();
    }

    public Integer get(int[] coordinate) {
        return -1;
    }

    private String entryToString(int[] coordinate) {
        return "";
    }

    private int[] retrieveNextCoordinateIfAvailable(int[] coordinate) {
        return new int[0];
    }
}
