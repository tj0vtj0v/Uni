package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class RandomMovementPattern extends MovementPattern {
    private final Direction launchSide;

    RandomMovementPattern(Direction launchSide) {
        super();
        this.launchSide = launchSide;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        if (launchSide == Direction.LEFT) {
            return new Position(random.nextInt(-50, -30), referencePositions[0].getY());
        } else {
            return new Position(GameView.WIDTH + random.nextInt(5, 25), referencePositions[0].getY());
        }
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        Position targetPosition = new Position(referencePositions[0]);
        int distance = random.nextInt(150, 500);

        switch (Direction.values()[random.nextInt(Direction.values().length)]) {
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
