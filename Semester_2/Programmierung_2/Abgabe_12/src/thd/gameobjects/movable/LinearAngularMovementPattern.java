package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class LinearAngularMovementPattern extends MovementPattern {
    private final int angleInDegree;

    LinearAngularMovementPattern(int startAngle, int angleRange, Position startPosition) {
        super(startPosition);

        angleInDegree = random.nextInt(startAngle, startAngle - angleRange);
    }

    LinearAngularMovementPattern(Direction direction, int range, Position startPosition) {
        super(startPosition);
        int startAngle = 0;

        switch (direction) {
            case UP -> {
                startAngle = 90;
            }
            case DOWN -> {
                startAngle = 270;
            }
            case LEFT -> {
                startAngle = 180;
            }
            case UP_LEFT -> {
                startAngle = 135;
            }
            case UP_RIGHT -> {
                startAngle = 45;
            }
            case DOWN_LEFT -> {
                startAngle = 225;
            }
            case DOWN_RIGHT -> {
                startAngle = 315;
            }
        }

        angleInDegree = random.nextInt(startAngle, startAngle + range) - range / 2;
    }

    @Override
    public Position startPosition(Position... referencePositions) {
        return new Position(startPosition);
    }

    @Override
    public Position nextTargetPosition(Position... referencePositions) {
        int distance = random.nextInt(150, GameView.WIDTH / 2);
        Position targetPosition = new Position(startPosition);

        targetPosition.right(Math.cos(Math.toRadians(angleInDegree)) * distance);
        targetPosition.up(Math.sin(Math.toRadians(angleInDegree)) * distance);

        return targetPosition;
    }
}
