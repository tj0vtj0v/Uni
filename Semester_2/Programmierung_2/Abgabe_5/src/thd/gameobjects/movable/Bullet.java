package thd.gameobjects.movable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.base.Position;

/**
 * Representation of the in-game-object 'Bullet'.
 * <p>
 * initial influenced linear movement
 * indestructible
 * png textured
 */
public class Bullet extends GameObject {
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

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 5;

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
        this(gameView, gamePlayManager, new Position(0, 100), Direction.down);
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ObjectBlockImages.BULLET, position.getX(), position.getY(), size, rotation);
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
