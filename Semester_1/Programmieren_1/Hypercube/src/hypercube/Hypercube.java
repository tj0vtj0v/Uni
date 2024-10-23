package hypercube;

import java.util.Arrays;

public class Hypercube {
    private final Hypercube[] content;
    private Integer value;
    private final int dimension;

    Hypercube(int dimension, int length) {
        this.dimension = dimension;
        content = new Hypercube[length];

        if (dimension > 0) {
            for (int i = 0; i < content.length; i++) {
                content[i] = new Hypercube(dimension - 1, length);
            }
        }
    }

    public void set(int[] coordinate, Integer value) {
        int cubeIndex = coordinate[0];

        if (coordinate.length > 1) {
            content[cubeIndex].set(Arrays.copyOfRange(coordinate, 1, coordinate.length), value);
        } else {
            content[cubeIndex].value = value;
        }
    }

    public Integer get(int[] coordinate) {
        int cubeIndex = coordinate[0];

        if (coordinate.length > 1) {
            return content[cubeIndex].get(Arrays.copyOfRange(coordinate, 1, coordinate.length));
        } else {
            return content[cubeIndex].value;
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        int[] currentCoordinate = new int[dimension];
        Arrays.fill(currentCoordinate, 0);

        while (currentCoordinate != null) {
            output.append(Arrays.toString(currentCoordinate)).append(" = ").append(get(currentCoordinate)).append("/n");
            currentCoordinate = retrieveNextCoordinateIfAvailable(currentCoordinate);
        }

        return output.toString();
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
