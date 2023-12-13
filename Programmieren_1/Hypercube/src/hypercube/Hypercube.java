package hypercube;

import java.util.Arrays;

public class Hypercube {
    private final Hypercube[] content;
    private final int[] firstCoordinate;

    Hypercube(int dimension, int length) {
        content = new Hypercube[length];
        for (int i = 0; i < content.length; i++) {
            content[i] = dimension > 1 ? new Hypercube(dimension - 1, length) : new HypercubeValue();
        }

        firstCoordinate = new int[dimension];
        Arrays.fill(firstCoordinate, 0);
    }

    public void set(int[] coordinate, Integer value) {
        int cubeIndex = coordinate[0];

        if (coordinate.length > 1) {
            content[cubeIndex].set(Arrays.copyOfRange(coordinate, 1, coordinate.length), value);
        } else {

            if (content[cubeIndex] instanceof HypercubeValue) {
                ((HypercubeValue) content[cubeIndex]).value = value;
            }
        }
    }

    public Integer get(int[] coordinate) {
        int cubeIndex = coordinate[0];

        if (coordinate.length > 1) {
            return content[cubeIndex].get(Arrays.copyOfRange(coordinate, 1, coordinate.length));
        } else {

            if (content[cubeIndex] instanceof HypercubeValue) {
                return ((HypercubeValue) content[cubeIndex]).value;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int[] currentCoordinate = firstCoordinate;

        while (currentCoordinate != null) {
            output.append(entryToString(currentCoordinate));
            currentCoordinate = retrieveNextCoordinateIfAvailable(currentCoordinate);
        }

        return output.toString();
    }

    private String entryToString(int[] coordinate) {
        return Arrays.toString(coordinate) + " = " + get(coordinate) + "\n";
    }

    private int[] retrieveNextCoordinateIfAvailable(int[] coordinate) {

        for (int index = coordinate.length - 1; index >= 0; index--) {
            if (coordinate[index] >= content.length - 1) {
                coordinate[index] = 0;
            } else {
                coordinate[index] += 1;
                return coordinate;
            }
        }

        return null;
    }
}
