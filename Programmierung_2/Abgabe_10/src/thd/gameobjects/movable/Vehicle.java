package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.unmovable.DustExplosion;
import thd.gameobjects.unmovable.Explosion;

/**
 * Representation of all Vehicles.
 */
public abstract class Vehicle extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    int hitTolerance;
    Vehicle(GameView gameView, GamePlayManager gamePlayManager, Direction facing, Position position) {
        super(gameView, gamePlayManager, facing, position);

        distanceToBackground = LAYER_4;

        size = BLOCK_IMAGE_SIZE;

        LinearMovementPattern movementPattern = new LinearMovementPattern(this.direction.opposite(), position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= gamePlayManager.currentLevel().vehicleSpawnDistance;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet) {
            hitTolerance--;
        } else if (other instanceof Explosion) {
            hitTolerance = 0;
        }

        if (hitTolerance <= 0) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addScorePoints(-1);
            gamePlayManager.spawnGameObject(new DustExplosion(gameView, gamePlayManager, direction, new Position(position.getX(), position.getY()+VEHICLE_EXPLOSION_Y_OFFSET)));
            gamePlayManager.spawnGameObject(new DustExplosion(gameView, gamePlayManager, direction, new Position(position.getX()+VEHICLE_EXPLOSION_2_X_OFFSET, position.getY()+VEHICLE_EXPLOSION_Y_OFFSET)));
        }
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
    }

}
