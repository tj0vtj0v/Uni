package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.LinearMovementPattern;
import thd.gameobjects.unmovable.DustExplosion;
import thd.gameobjects.unmovable.Explosion;

abstract class Vehicle extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    int hitTolerance;
    Vehicle(GameView gameView, GamePlayManager gamePlayManager, Direction facing, Position position) {
        super(gameView, gamePlayManager, facing, position);

        distanceToBackground = GameObjectConstants.LAYER_4;

        size = GameObjectConstants.BLOCKIMAGE_SIZE;

        LinearMovementPattern movementPattern = new LinearMovementPattern(this.direction.opposite(), position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= gamePlayManager.currentLevel().vehicleLaunchDistance;
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
            gamePlayManager.spawnGameObject(new DustExplosion(gameView, gamePlayManager, direction, new Position(position.getX(), position.getY()+30)));
            gamePlayManager.spawnGameObject(new DustExplosion(gameView, gamePlayManager, direction, new Position(position.getX()+75, position.getY()+30)));
        }
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
    }

}
