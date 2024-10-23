package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class HorizontalMovementPattern extends MovementPattern {
    private final boolean toRight;
    private final int height;

    protected HorizontalMovementPattern(int height, boolean toRight) {
        this.height = height;
        this.toRight = toRight;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        if (toRight) {
            return new Position(0, height);
        } else {
            return new Position(GameView.WIDTH, height);
        }
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (toRight) {
            return new Position(GameView.WIDTH - 10, height);
        } else {
            return new Position(0, height);
        }

    }
}
