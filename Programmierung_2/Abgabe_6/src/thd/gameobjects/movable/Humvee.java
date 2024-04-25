package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;


/**
 * Representation of the in-game-object 'Humvee'.
 * <p>
 * passive linear movement
 * destructible by 1 {@link Grenade} or 5 {@link Bullet}
 * BlockImage
 */
public class Humvee extends CollidingGameObject {

    /**
     * Creates Humvee with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Humvee(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 5;

        LinearMovementPattern movementPattern = new LinearMovementPattern(Direction.RIGHT, new Position(0, GameView.HEIGHT / 2));
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

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
