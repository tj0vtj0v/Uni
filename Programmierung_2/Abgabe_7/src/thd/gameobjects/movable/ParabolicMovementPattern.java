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

        trajectory = random.nextInt(20, 30);
        maximumNumberOfSteps = random.nextInt(20, 40);
        steps = 0;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        if (direction == Direction.LEFT) {
            startPosition.left(27);
        } else {
            startPosition.right(54);
        }

        return new Position(startPosition);
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
