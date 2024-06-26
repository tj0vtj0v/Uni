package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.EnemyGunnerBlockImages;
import thd.gameobjects.unmovable.DeadEnemy;
import thd.gameobjects.unmovable.Explosion;

import java.util.List;
import java.util.Random;


/**
 * Representation of the in-game-object 'Enemy'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * png textured
 */
public class EnemyGunner extends MovingCharacter implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private final RandomMovementPattern movementPattern;

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView                            window in which it has to be displayed.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param location                            Stores positional information.
     * @param position                            Position from which to start movement.
     * @param collidingGameObjectsForPathDecision List of ObjectBlockImages that block the movement.
     */
    public EnemyGunner(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, location, position, collidingGameObjectsForPathDecision);

        instanceBlockImage = EnemyGunnerBlockImages.DOWN_1;
        distanceToBackground = 100;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(6, 6, -12, -24);

        speedInPixel = 1;

        movementPattern = new RandomMovementPattern(location);
        this.position.updateCoordinates(movementPattern.startPosition(getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet && ((Bullet) other).creator != this || other instanceof Explosion) {
            gamePlayManager.addScorePoints(-1);
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new DeadEnemy(gameView, gamePlayManager, position));
        }
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (gameView.timer(new Random(System.currentTimeMillis()).nextInt(1000, 1500), this)) {
            shoot();
        }
    }

    @Override
    public void updatePosition() {
        Position oldPosition = new Position(getPosition());
        position.moveToPosition(targetPosition, speedInPixel);

        if (pathIsBlocked()) {
            position.updateCoordinates(oldPosition);
        }
        if (gameView.timer(new Random(System.currentTimeMillis()).nextInt(2500, 5000), this)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
        }
    }

    @Override
    public String toString() {
        return "EnemyGunnerBlockImages: %s with movementPattern: %s".formatted(position, movementPattern);
    }
}
