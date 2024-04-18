package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ObjectBlockImages;


/**
 * Representation of the in-game-object 'Humvee'.
 * <p>
 * passive linear movement
 * destructible by 1 {@link Grenade} or 5 {@link Bullet}
 * BlockImage
 */
public class Humvee extends GameObject {

    /**
     * Creates Humvee with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public Humvee(GameView gameView) {
        super(gameView);

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 5;

        HorizontalMovementPattern movementPattern = new HorizontalMovementPattern(GameView.HEIGHT - 200, true);
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ObjectBlockImages.HUMVEE, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public String toString() {
        return "Humvee: %s".formatted(position);
    }
}
