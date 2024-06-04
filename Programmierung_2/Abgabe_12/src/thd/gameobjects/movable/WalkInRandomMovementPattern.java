package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class WalkInRandomMovementPattern extends MovementPattern {
    private final Direction launchSide;
    private Direction direction;

    WalkInRandomMovementPattern(Direction launchSide) {
        super(new Position());
        this.launchSide = launchSide;
    }

    Direction getDirection() {
        return direction;
    }

    @Override
    public Position startPosition(Position... referencePositions) {
        if (launchSide == Direction.LEFT) {
            return new Position(RANDOM_GLOBAL_SPAWN_LEFT_OFFSET, referencePositions[0].getY());
        } else {
            return new Position(GameView.WIDTH + RANDOM_GLOBAL_SPAWN_RIGHT_OFFSET, referencePositions[0].getY());
        }
    }

    @Override
    public Position nextTargetPosition(Position... referencePositions) {
        Position targetPosition = new Position(referencePositions[0]);
        int distance = random.nextInt(RANDOM_START_TARGET_DISTANCE, RANDOM_END_TARGET_DISTANCE);
        direction = Direction.values()[random.nextInt(Direction.values().length)];

        switch (direction) {
            case LEFT:
                targetPosition.left(distance);
                break;
            case RIGHT:
                targetPosition.right(distance);
                break;
            case UP:
                targetPosition.up(distance);
                break;
            case DOWN:
                targetPosition.down(distance);
                break;
            case UP_LEFT:
                targetPosition.left(distance);
                targetPosition.up(distance);
                break;
            case UP_RIGHT:
                targetPosition.right(distance);
                targetPosition.up(distance);
                break;
            case DOWN_RIGHT:
                targetPosition.right(distance);
                targetPosition.down(distance);
                break;
            case DOWN_LEFT:
                targetPosition.left(distance);
                targetPosition.down(distance);
                break;
        }

        if (targetPosition.getX() > 0 && targetPosition.getX() < GameView.WIDTH) {
            return targetPosition;
        } else {
            return nextTargetPosition(referencePositions);
        }
    }
}
