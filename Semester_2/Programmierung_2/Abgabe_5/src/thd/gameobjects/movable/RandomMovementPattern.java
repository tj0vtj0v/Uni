package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class RandomMovementPattern extends MovementPattern {


    RandomMovementPattern() {
        super();
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(GameView.WIDTH / 2d, GameView.HEIGHT / 2d);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        Position targetPosition = new Position(referencePositions[0]);
        int distance = random.nextInt(150, 720);

        switch (Direction.values()[random.nextInt(Direction.values().length)]) {
            case left:
                targetPosition.left(distance);
                break;
            case right:
                targetPosition.right(distance);
                break;
            case up:
                targetPosition.up(distance);
                break;
            case down:
                targetPosition.down(distance);
                break;
            case upLeft:
                targetPosition.left(distance);
                targetPosition.up(distance);
                break;
            case upRight:
                targetPosition.right(distance);
                targetPosition.up(distance);
                break;
            case downRight:
                targetPosition.right(distance);
                targetPosition.down(distance);
                break;
            case downLeft:
                targetPosition.left(distance);
                targetPosition.down(distance);
                break;
        }

        if (targetPosition.getX() > 0 && targetPosition.getY() > 0 && targetPosition.getY() < GameView.HEIGHT && targetPosition.getX() < GameView.WIDTH) {
            return targetPosition;
        } else {
            return nextTargetPosition(referencePositions);
        }
    }
}
