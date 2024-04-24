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
        final double saveDistance = GameView.WIDTH / 2f;


        return switch (direction) {
            case up -> new Position(oldX, oldY - saveDistance);
            case down -> new Position(oldX, oldY + saveDistance);
            case left -> new Position(oldX - saveDistance, oldY);
            case right -> new Position(oldX + saveDistance, oldY);
            case upLeft -> new Position(oldX - saveDistance, oldY - saveDistance);
            case upRight -> new Position(oldX + saveDistance, oldY - saveDistance);
            case downLeft -> new Position(oldX - saveDistance, oldY + saveDistance);
            case downRight -> new Position(oldX + saveDistance, oldY + saveDistance);
        };
    }
}
