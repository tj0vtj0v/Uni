package thd.gameobjects.movable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

/**
 * Representation of the in-game-object 'Bullet'.
 * <p>
 * initial influenced linear movement
 * indestructible
 * png textured
 */
public class Bullet extends CollidingGameObject {
    final GameObject creator;

    /**
     * Crates a new Bullet.
     *
     * @param gameView        GameView to show the bullet on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param originPosition  Position from which to start movement.
     * @param direction       Direction in which the bullet should travel.
     * @param creator         Instance which shoots this Bullet.
     */
    public Bullet(GameView gameView, GamePlayManager gamePlayManager, Position originPosition, Direction direction, GameObject creator) {
        super(gameView, gamePlayManager);
        this.creator = creator;

        blockImage = ObjectBlockImages.BULLET;
        distanceToBackground = (char) (creator.getDistanceToBackground() + 1);

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(1, 1, -2, -2);

        speedInPixel = 2;

        LinearMovementPattern movementPattern = new LinearMovementPattern(direction, originPosition);
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other != creator) {
            gamePlayManager.destroyGameObject(this);
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
    public void updateStatus() {
        if (position.getY() < 0 || position.getY() > GameView.HEIGHT || position.getX() < 0 || position.getX() > GameView.WIDTH) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
