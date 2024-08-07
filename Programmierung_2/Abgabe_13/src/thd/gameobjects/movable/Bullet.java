package thd.gameobjects.movable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ObjectBlockImages;
import thd.gameobjects.unmovable.AmmoBox;
import thd.gameobjects.unmovable.Explosion;
import thd.gameobjects.unmovable.Mine;

/**
 * Representation of the in-game-object 'Bullet'.
 * <p>
 * initial influenced linear movement
 * Blockimage
 */
public class Bullet extends CollidingGameObject implements ShiftableGameObject {
    private final GameObject creator;

    /**
     * Crates a new Bullet.
     *
     * @param gameView        GameView to show the bullet on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param position        Position from which to start movement.
     * @param originLocation  Direction in which the bullet should travel.
     * @param creator         Instance which shoots this Bullet.
     */
    public Bullet(GameView gameView, GamePlayManager gamePlayManager, Direction originLocation, Position position, GameObject creator) {
        super(gameView, gamePlayManager, originLocation, position);
        this.creator = creator;

        blockImage = ObjectBlockImages.BULLET;
        distanceToBackground = LAYER_5;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(size / 3, size / 3, size * -2 / 3, size * -2 / 3);

        speedInPixel = gamePlayManager.currentLevel().bulletSpeedInPixel;

        LinearDirectionMovementPattern movementPattern = new LinearDirectionMovementPattern(originLocation, position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());

        gameView.playSound("shot.wav", false);
    }

    /**
     * Creator is passed for reasonable collision detection.
     *
     * @return creator of the Bullet.
     */
    public GameObject getCreator() {
        return creator;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other != creator && !(other instanceof Explosion) && !(other instanceof AmmoBox) && !(other instanceof Mine)) {
            gamePlayManager.destroyGameObject(this);
        }
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
