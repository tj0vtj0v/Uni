package thd.gameobjects.movable;

import thd.game.managers.ExplosionCountdownExpiredException;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

/**
 * Represents the movement of an thrown grenade.
 */
public class GrenadeMovementPattern extends MovementPattern {
    private int yVelocity;
    private int xVelocity;
    private int targetSteps;
    private int steps;

    /**
     * Creates the movement of a thrown grenade.
     *
     * @param direction     where the grenade is heading.
     * @param startPosition the grenade is thrown.
     */
    public GrenadeMovementPattern(Direction direction, Position startPosition) {
        super(startPosition);
        Direction saveDirection = direction == null ? Direction.DOWN : direction;
        targetSteps = random.nextInt(18, 20);

        if (saveDirection.name().contains("UP")) {
            yVelocity = (int) (targetSteps * 1.5);
        } else if (saveDirection.name().contains("DOWN")) {
            yVelocity = 2;
            targetSteps -= 2;
        } else {
            yVelocity = targetSteps;
        }
        yVelocity += (int) random.nextGaussian(0, 2);

        xVelocity = random.nextInt(18, 25);
        if (saveDirection.name().contains("LEFT")) {
            xVelocity *= -1;
        } else if (!saveDirection.name().contains("RIGHT")) {
            xVelocity = (int) random.nextGaussian(0, 2);
        }

        steps = 0;
    }

    @Override
    public Position startPosition(Position... referencePositions) {
        return startPosition;
    }

    @Override
    public Position nextTargetPosition(Position... referencePositions) {
        if (steps >= targetSteps) {
            throw new ExplosionCountdownExpiredException("Grenade is exploded.");
        }

        Position targetPosition = new Position(referencePositions[0]);

        targetPosition.up(yVelocity - 2 * steps++);
        targetPosition.right(xVelocity);


        return targetPosition;
    }
}
