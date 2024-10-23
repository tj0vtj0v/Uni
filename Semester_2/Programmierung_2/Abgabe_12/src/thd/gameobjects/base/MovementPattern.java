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

    /**
     * Calculates the start position of a movement.
     *
     * @param referencePositions reference for calculation.
     * @return position where to start from.
     */
    public abstract Position startPosition(Position... referencePositions);

    /**
     * Calculates the next Position of a movement.
     *
     * @param referencePositions reference for calculation.
     * @return position where to go to.
     */
    public abstract Position nextTargetPosition(Position... referencePositions);
}
