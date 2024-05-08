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
        shotCooldownInMilliseconds = gamePlayManager.currentLevel().enemyShotCooldown;

        blockImage = EnemyGunnerBlockImages.DOWN_1;
        distanceToBackground = GameObjectConstants.LAYER_2;

        size = GameObjectConstants.BLOCKIMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(GameObjectConstants.MOVING_CHARACTER_HIT_BOX_X_OFFSET, GameObjectConstants.MOVING_CHARACTER_HIT_BOX_Y_OFFSET, GameObjectConstants.MOVING_CHARACTER_HIT_BOX_WIDTH_OFFSET, GameObjectConstants.MOVING_CHARACTER_HIT_BOX_HEIGHT_OFFSET);

        speedInPixel = gamePlayManager.currentLevel().enemySpeedInPixel;

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
        if (other instanceof Bullet && ((Bullet) other).getCreator() != this || other instanceof Explosion) {
            gamePlayManager.addScorePoints(-1);
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new DeadEnemy(gameView, gamePlayManager, position));
        }
    }

    @Override
    public void updateStatus() {
        super.updateStatus();

        // TODO implement better shoot mechanic
        if (gameView.timer(new Random(hashCode()).nextInt(1000, 1500), this)) {
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
        if (gameView.timer(new Random(hashCode()).nextInt(GameObjectConstants.ENEMY_CHANGE_DIRECTION_INTERVALL_START_IN_MILLISECONDS, GameObjectConstants.ENEMY_CHANGE_DIRECTION_INTERVALL_END_IN_MILLISECONDS), this)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
            direction = movementPattern.getDirection();
        }
    }

    @Override
    public String toString() {
        return "EnemyGunnerBlockImages: %s with movementPattern: %s".formatted(position, movementPattern);
    }
}
