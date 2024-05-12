package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.Explosion;

import java.util.Objects;

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

        LinearAngularMovementPattern movementPattern = new LinearAngularMovementPattern(originLocation, 90, position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
        if (position.similarTo(targetPosition)) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new Explosion(gameView, gamePlayManager, direction, position));
        }
    }
}
