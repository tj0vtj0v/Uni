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
    private int soundID;

    Vehicle(GameView gameView, GamePlayManager gamePlayManager, Direction facing, Position position) {
        super(gameView, gamePlayManager, facing, position);

        distanceToBackground = LAYER_2;

        size = BLOCK_IMAGE_SIZE;

        LinearDirectionMovementPattern movementPattern = new LinearDirectionMovementPattern(this.direction.opposite(), position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    private void destroy() {
        gameView.stopSound(soundID);
        gamePlayManager.destroyGameObject(this);
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;


        if (infoObject.getPosition().verticalDistance(this.position) <= gamePlayManager.currentLevel().vehicleSpawnDistance) {
            soundID = gameView.playSound("vehicle.wav", true);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet) {
            hitTolerance--;
        } else if (other instanceof Explosion) {
            hitTolerance = 0;
        }

        if (hitTolerance <= 0) {
            destroy();
            gamePlayManager.addScorePoints(-1);
            gamePlayManager.spawnGameObject(new DustExplosion(gameView, gamePlayManager, direction, new Position(position.getX(), position.getY() + VEHICLE_EXPLOSION_Y_OFFSET)));
            gamePlayManager.spawnGameObject(new DustExplosion(gameView, gamePlayManager, direction, new Position(position.getX() + VEHICLE_EXPLOSION_2_X_OFFSET, position.getY() + VEHICLE_EXPLOSION_Y_OFFSET)));
        }
    }


    @Override
    public void updateStatus() {
        if (position.getY() > GameView.HEIGHT || position.getX() < width * -2 || position.getX() > GameView.WIDTH + width) {
            destroy();
        }
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
    }

}
