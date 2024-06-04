package thd.gameobjects.movable;

import thd.game.managers.ExplosionCountdownExpiredException;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

/**
 * Represents the movement of a mortar grenade.
 */
public class MortarMovementPattern extends MovementPattern {
    private final Direction direction;
    private final int trajectory;
    private final int targetSteps;
    private int steps;

    /**
     * Creates a movement pattern for a mortar grenade.
     *
     * @param direction     where the grenade should fly.
     * @param startPosition where the grenade is launched.
     */
    public MortarMovementPattern(Direction direction, Position startPosition) {
        super(startPosition);

        this.direction = direction;

        trajectory = random.nextInt(PARABOLIC_RANDOM_TRAJECTORY_START, PARABOLIC_RANDOM_TRAJECTORY_END);
        targetSteps = random.nextInt(PARABOLIC_RANDOM_STEPS_START, PARABOLIC_RANDOM_STEPS_END);
        steps = 0;
    }

    @Override
    public Position startPosition(Position... referencePositions) {
        if (direction == Direction.LEFT) {
            startPosition.right(PARABOLIC_SPAWN_LEFT_X_OFFSET);
        } else {
            startPosition.right(PARABOLIC_SPAWN_RIGHT_X_OFFSET);
        }

        return startPosition;
    }

    @Override
    public Position nextTargetPosition(Position... referencePositions) {
        if (steps >= targetSteps) {
            throw new ExplosionCountdownExpiredException("Grenade is exploded.");
        }

        Position targetPosition = new Position(referencePositions[0]);

        targetPosition.up(trajectory - 2 * steps++);

        if (direction.equals(Direction.RIGHT)) {
            targetPosition.right(trajectory);
        } else {
            targetPosition.left(trajectory);
        }

        return targetPosition;
    }
}
