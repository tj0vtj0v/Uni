package thd.gameobjects.base;

import java.util.Random;

/**
 * Base class for all Movement Patterns.
 */
public abstract class MovementPattern {
    protected final Random random;
    protected int currentIndex;


    protected MovementPattern() {
        random = new Random();
        currentIndex = -1;
    }

    protected Position startPosition(Position... referencePositions) {
        return new Position();
    }

    protected Position nextTargetPosition(Position... referencePositions) {
        return new Position();
    }
}
