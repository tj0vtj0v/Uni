package thd.gameobjects.base;

import java.util.Random;

/**
 * Base class for all Movement Patterns.
 */
public abstract class MovementPattern implements GameConstants {
    protected final Random random;


    protected MovementPattern() {
        random = new Random(System.currentTimeMillis());
    }

    protected abstract Position startPosition(Position... referencePositions);
    protected abstract Position nextTargetPosition(Position... referencePositions);
}
