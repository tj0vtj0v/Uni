package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class LinearMovementPattern extends MovementPattern {
    private final Direction direction;
    private final Position startPosition;

    LinearMovementPattern(Direction direction, Position startPosition) {
        super();
        this.direction = direction;
        this.startPosition = startPosition;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(startPosition);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        final double oldX = startPosition.getX();
        final double oldY = startPosition.getY();
        final double saveDistance = GameView.WIDTH * 2;


        return switch (direction) {
            case UP -> new Position(oldX, oldY - saveDistance);
            case DOWN -> new Position(oldX, oldY + saveDistance);
            case LEFT -> new Position(oldX - saveDistance, oldY);
            case RIGHT -> new Position(oldX + saveDistance, oldY);
            case UP_LEFT -> new Position(oldX - saveDistance, oldY - saveDistance);
            case UP_RIGHT -> new Position(oldX + saveDistance, oldY - saveDistance);
            case DOWN_LEFT -> new Position(oldX - saveDistance, oldY + saveDistance);
            case DOWN_RIGHT -> new Position(oldX + saveDistance, oldY + saveDistance);
        };
    }
}
