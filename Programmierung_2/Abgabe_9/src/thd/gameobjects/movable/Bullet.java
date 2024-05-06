package thd.gameobjects.movable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ObjectBlockImages;
import thd.gameobjects.unmovable.Explosion;

/**
 * Representation of the in-game-object 'Bullet'.
 * <p>
 * initial influenced linear movement
 * indestructible
 * png textured
 */
public class Bullet extends CollidingGameObject implements ShiftableGameObject {
    final GameObject creator;

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

        instanceBlockImage = ObjectBlockImages.BULLET;
        distanceToBackground = 250;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(1, 1, -2, -2);

        speedInPixel = 10;

        LinearMovementPattern movementPattern = new LinearMovementPattern(originLocation, position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other != creator && !(other instanceof Explosion)) {
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
