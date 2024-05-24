package thd.gameobjects.movable;

import thd.game.managers.ExplosionCountdownExpiredException;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

public class GrenadeMovementPattern extends MovementPattern {
    private int yVelocity;
    private int xVelocity;
    private int targetSteps;
    private int steps;

    public GrenadeMovementPattern(Direction direction, Position startPosition) {
        super(startPosition);
        direction = direction == null ? Direction.DOWN : direction;
        targetSteps = random.nextInt(18, 20);

        if (direction.name().contains("UP")) {
            yVelocity = targetSteps * 2;
        } else if (direction.name().contains("DOWN")) {
            yVelocity = 2;
            targetSteps -= 2;
        } else {
            yVelocity = targetSteps;
        }
        yVelocity += (int) random.nextGaussian(0, 3);

        xVelocity = random.nextInt(15, 25);
        if (direction.name().contains("LEFT")) {
            xVelocity *= -1;
        } else if (!direction.name().contains("RIGHT")) {
            xVelocity = (int) random.nextGaussian(0, 3);
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
