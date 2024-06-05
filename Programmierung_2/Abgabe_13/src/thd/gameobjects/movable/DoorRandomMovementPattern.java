package thd.gameobjects.movable;

import thd.gameobjects.base.Direction;
import thd.gameobjects.base.Position;

/**
 * Represents the Movement pattern of the Enemy's spawning in the door.
 */
public class DoorRandomMovementPattern extends WalkInRandomMovementPattern {
    private boolean firstCheckpoint;

    DoorRandomMovementPattern(Direction launchSide) {
        super(launchSide);
        firstCheckpoint = true;
    }

    @Override
    public Position startPosition(Position... referencePositions) {
        return new Position(referencePositions[0]);
    }

    @Override
    public Position nextTargetPosition(Position... referencePositions) {
        if (firstCheckpoint) {
            firstCheckpoint = false;
            Position targetPosition = new Position(referencePositions[0]);
            targetPosition.down(random.nextInt(100, RANDOM_START_TARGET_DISTANCE * 2));
            return targetPosition;
        }

        return super.nextTargetPosition(referencePositions);
    }
}
