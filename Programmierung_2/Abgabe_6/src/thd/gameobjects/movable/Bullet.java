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
    /**
     * Crates a new Bullet.
     *
     * @param gameView        GameView to show the bullet on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param originPosition  Position from which to start movement.
     * @param direction       Direction in which the bullet should travel.
     */
    public Bullet(GameView gameView, GamePlayManager gamePlayManager, Position originPosition, Direction direction) {
        super(gameView, gamePlayManager);

        blockImage = ObjectBlockImages.BULLET;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(1, 1, -2, -2);

        speedInPixel = 10
        ;

        LinearMovementPattern movementPattern = new LinearMovementPattern(direction, originPosition);
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    /**
     * Crates a new Bullet.
     *
     * @param gameView        GameView to show the bullet on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Bullet(GameView gameView, GamePlayManager gamePlayManager) {
        this(gameView, gamePlayManager, new Position(0, 100), Direction.DOWN);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        gamePlayManager.destroyGameObject(this);
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
        if (0 > position.getY() || position.getY() > GameView.HEIGHT || 0 > position.getX() || position.getX() > GameView.WIDTH) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
