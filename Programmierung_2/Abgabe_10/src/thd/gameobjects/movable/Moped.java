package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ObjectBlockImages;
import thd.gameobjects.unmovable.DustExplosion;
import thd.gameobjects.unmovable.Explosion;


/**
 * Representation of the in-game-object 'Moped'.
 * <p>
 * passive linear movement
 * destructible by 1 {@link Grenade} or 3 {@link Bullet}
 * BlockImage
 */
public class Moped extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private int hitTolerance;


    /**
     * Creates Moped with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position from which to start movement.
     */
    public Moped(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        if (this.direction == Direction.RIGHT) {
            blockImage = ObjectBlockImages.MOPED;
        } else {
            blockImage = mirrorBlockImage(ObjectBlockImages.MOPED);
        }
        distanceToBackground = 200;
        hitTolerance = 3;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(3, 27, -6, -45);

        speedInPixel = 5;

        LinearMovementPattern movementPattern = new LinearMovementPattern(this.direction.opposite(), position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT * .5;
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

    @Override
    public String toString() {
        return "Moped: %s with %d hits left till destruction".formatted(position, hitTolerance);
    }
}
