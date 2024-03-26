package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;


/**
 * Framework component for movements.
 * Copied from Moped.
 */
public class RandomBall extends GameObject {
    private final QuadraticMovementPattern quadraticMovementPattern;
    private boolean stop;


    /**
     * Creates RandomBall with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public RandomBall(GameView gameView) {
        super(gameView);

        size = 50;
        stop = false;
        rotation = 0;
        width = 50;
        height = 50;

        speedInPixel = 4;

        quadraticMovementPattern = new QuadraticMovementPattern();
        position.updateCoordinates(new RandomMovementPattern().startPosition());
        targetPosition.updateCoordinates(quadraticMovementPattern.nextTargetPosition());
    }

    @Override
    public void addToCanvas() {
        if (gameView.gameTimeInMilliseconds() < 5000) {
            gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 2, true, Color.YELLOW);
        } else {
            gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 2, true, Color.RED);
        }
        gameView.addOvalToCanvas(position.getX(), position.getY(), speedInPixel, speedInPixel, 2, false, Color.BLUE);
        gameView.addOvalToCanvas(targetPosition.getX(), targetPosition.getY(), width, height, 2, false, Color.WHITE);
    }

    @Override
    public void updatePosition() {
        if (gameView.timer(5000, this)) {
            speedInPixel += 1;
        }

        if (stop) {
            if (gameView.timer(2000, this)) {
                stop = !stop;
            }

        } else {
            if (gameView.timer(8000, this)) {
                stop = !stop;
            }

            position.moveToPosition(targetPosition, speedInPixel);

            if (position.similarTo(targetPosition)) {
                targetPosition.updateCoordinates(quadraticMovementPattern.nextTargetPosition());
            }
        }
    }

    @Override
    public String toString() {
        return "Moped: %s".formatted(position);
    }
}
