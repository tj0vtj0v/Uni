package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.Explosion;

/**
 * Representation of the in-game-object 'ExplodingBullet'.
 * <p>
 * initial influenced linear movement
 * destruction at impact
 * instantiates an explosion at destination arrival.
 * Blockimage
 */
public class ExplodingBullet extends Bullet {

    /**
     * Crates a new Bullet.
     *
     * @param gameView        GameView to show the bullet on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param originLocation  Direction in which the bullet should travel.
     * @param position        Position from which to start movement.
     * @param creator         Instance which shoots this Bullet.
     */
    public ExplodingBullet(GameView gameView, GamePlayManager gamePlayManager, Direction originLocation, Position position, GameObject creator) {
        super(gameView, gamePlayManager, originLocation, position, creator);

        LinearAngularMovementPattern movementPattern = new LinearAngularMovementPattern(originLocation, 75, position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (position.similarTo(targetPosition)) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new Explosion(gameView, gamePlayManager, direction, new Position(position.getX() + SHOOTING_BOX_BULLET_EXPLOSION_X_OFFSET, position.getY())));
        }
    }
}
