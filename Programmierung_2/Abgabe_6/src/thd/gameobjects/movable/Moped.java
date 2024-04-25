package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;


/**
 * Representation of the in-game-object 'Moped'.
 * <p>
 * passive linear movement
 * destructible by 1 {@link Grenade} or 3 {@link Bullet}
 * BlockImage
 */
public class Moped extends CollidingGameObject {


    /**
     * Creates Moped with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Moped(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 2;

        LinearMovementPattern movementPattern = new LinearMovementPattern(Direction.LEFT, new Position(GameView.WIDTH, GameView.HEIGHT / 8f));
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ObjectBlockImages.MOPED, position.getX(), position.getY(), size, rotation);
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
