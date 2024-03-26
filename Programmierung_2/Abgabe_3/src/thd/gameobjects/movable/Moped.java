package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;


/**
 * Representation of the in-game-object 'Moped'.
 * <p>
 * passive linear movement
 * destructible by 1 {@link Grenade} or 3 {@link Bullet}
 * png textured
 */
public class Moped extends GameObject {


    /**
     * Creates Moped with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public Moped(GameView gameView) {
        super(gameView);

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 2;

        HorizontalMovementPattern movementPattern = new HorizontalMovementPattern(GameView.HEIGHT / 8, false);
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("moped.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public String toString() {
        return "Moped: %s".formatted(position);
    }
}
