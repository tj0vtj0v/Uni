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
    private int hitTolerance = 3;


    /**
     * Creates Moped with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Moped(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        blockImage = ObjectBlockImages.MOPED;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(3, 15, -6, -42);

        speedInPixel = 1;

        LinearMovementPattern movementPattern = new LinearMovementPattern(Direction.LEFT, new Position(GameView.WIDTH, GameView.HEIGHT / 8f));
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet) {
            hitTolerance--;

            if (hitTolerance <= 0) {
                gamePlayManager.destroyGameObject(this);
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
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
