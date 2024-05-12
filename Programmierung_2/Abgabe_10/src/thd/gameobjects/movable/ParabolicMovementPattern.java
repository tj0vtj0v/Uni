package thd.gameobjects.movable;

import thd.game.managers.ExplosionCountdownExpiredException;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class ParabolicMovementPattern extends MovementPattern {
    private final Direction direction;
    private final Position startPosition;
    private final int trajectory;
    private final int maximumNumberOfSteps;
    private int steps;

    ParabolicMovementPattern(Direction direction, Position startPosition) {
        super();

        this.direction = direction;
        this.startPosition = new Position(startPosition);

        trajectory = random.nextInt(PARABOLIC_RANDOM_TRAJECTORY_START, PARABOLIC_RANDOM_TRAJECTORY_END);
        maximumNumberOfSteps = random.nextInt(PARABOLIC_RANDOM_STEPS_START, PARABOLIC_RANDOM_STEPS_END);
        steps = 0;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        if (direction == Direction.LEFT) {
            startPosition.right(PARABOLIC_SPAWN_LEFT_X_OFFSET);
        } else {
            startPosition.right(PARABOLIC_SPAWN_RIGHT_X_OFFSET);
        }

        return startPosition;
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (steps >= maximumNumberOfSteps) {
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
