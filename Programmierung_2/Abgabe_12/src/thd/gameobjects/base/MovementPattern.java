package thd.gameobjects.base;

import java.util.Random;

/**
 * Base class for all Movement Patterns.
 */
public abstract class MovementPattern implements GameConstants {
    protected final Random random;
    protected final Position startPosition;


    protected MovementPattern(Position startPosition) {
        random = new Random(System.currentTimeMillis() + startPosition.hashCode());
        this.startPosition = new Position(startPosition);
    }

    public abstract Position startPosition(Position... referencePositions);
    public abstract Position nextTargetPosition(Position... referencePositions);
}
