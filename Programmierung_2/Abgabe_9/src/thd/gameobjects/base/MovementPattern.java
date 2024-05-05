package thd.gameobjects.base;

import java.util.Random;

/**
 * Base class for all Movement Patterns.
 */
public abstract class MovementPattern {
    protected final Random random;
    protected int currentIndex;


    protected MovementPattern() {
        random = new Random(System.currentTimeMillis());
        currentIndex = -1;
    }

    protected abstract Position startPosition(Position... referencePositions);
    protected abstract Position nextTargetPosition(Position... referencePositions);
}
