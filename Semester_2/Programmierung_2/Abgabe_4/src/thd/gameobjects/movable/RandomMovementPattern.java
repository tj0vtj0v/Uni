package thd.gameobjects.movable;

import thd.game.utilities.GameView;
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
        int maximumDistance;
        int stepSizeInPixel;

        switch (random.nextInt(8)) {
            case 0:
                maximumDistance = (int) targetPosition.getX() + 1;
                stepSizeInPixel = random.nextInt(maximumDistance);

                targetPosition.left(stepSizeInPixel);
                break;
            case 1:
                maximumDistance = (int) (GameView.WIDTH - targetPosition.getX()) + 1;
                stepSizeInPixel = random.nextInt(maximumDistance);

                targetPosition.right(stepSizeInPixel);
                break;
            case 2:
                maximumDistance = (int) targetPosition.getY() + 1;
                stepSizeInPixel = random.nextInt(maximumDistance);

                targetPosition.up(stepSizeInPixel);
                break;
            case 3:
                maximumDistance = (int) (GameView.HEIGHT - targetPosition.getY()) + 1;
                stepSizeInPixel = random.nextInt(maximumDistance);

                targetPosition.down(stepSizeInPixel);
                break;
            case 4:
                maximumDistance = (int) Math.min(targetPosition.getX(), targetPosition.getY()) + 1;
                stepSizeInPixel = random.nextInt(maximumDistance);

                targetPosition.left(stepSizeInPixel);
                targetPosition.up(stepSizeInPixel);
                break;
            case 5:
                maximumDistance = (int) Math.min(GameView.WIDTH - targetPosition.getX(), targetPosition.getY()) + 1;
                stepSizeInPixel = random.nextInt(maximumDistance);

                targetPosition.right(stepSizeInPixel);
                targetPosition.up(stepSizeInPixel);
                break;
            case 6:
                maximumDistance = (int) Math.min(GameView.WIDTH - targetPosition.getX(), GameView.HEIGHT - targetPosition.getY()) + 1;
                stepSizeInPixel = random.nextInt(maximumDistance);

                targetPosition.right(stepSizeInPixel);
                targetPosition.down(stepSizeInPixel);
                break;
            case 7:
                maximumDistance = (int) Math.min(targetPosition.getX(), GameView.HEIGHT - targetPosition.getY()) + 1;
                stepSizeInPixel = random.nextInt(maximumDistance);

                targetPosition.left(stepSizeInPixel);
                targetPosition.down(stepSizeInPixel);
                break;
        }

        return targetPosition;
    }
}
