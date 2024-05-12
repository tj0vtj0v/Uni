package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.EnemyGunnerBlockImages;
import thd.gameobjects.unmovable.DeadEnemy;

import java.lang.constant.Constable;
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
    private int changeDirectionInterval;

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
        distanceToBackground = LAYER_2;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(size * 2, size * 2, size * -4, size * -8);

        speedInPixel = gamePlayManager.currentLevel().enemySpeedInPixel;

        movementPattern = new RandomMovementPattern(location);
        this.position.updateCoordinates(movementPattern.startPosition(getPosition()));
        random.setSeed(hashCode());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));

        changeDirectionInterval = random.nextInt(ENEMY_CHANGE_DIRECTION_INTERVAL_START_IN_MILLISECONDS, ENEMY_CHANGE_DIRECTION_INTERVAL_END_IN_MILLISECONDS);
    }

    private boolean hitPossible() {
        boolean hasVertical = direction.name().contains("UP") || direction.name().contains("DOWN");
        boolean matchingUp = direction.name().contains("UP") && (position.getY() > gamePlayManager.mainCharacterPosition().getY());
        boolean matchingDown = direction.name().contains("DOWN") && (position.getY() < gamePlayManager.mainCharacterPosition().getY());
        boolean hasHorizontal = direction.name().contains("RIGHT") || direction.name().contains("LEFT");
        boolean matchingRight = direction.name().contains("RIGHT") && (position.getX() < gamePlayManager.mainCharacterPosition().getX());
        boolean matchingLeft = direction.name().contains("LEFT") && (position.getX() > gamePlayManager.mainCharacterPosition().getX());

        return (!hasVertical || matchingUp || matchingDown) && (!hasHorizontal || matchingRight || matchingLeft);
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (fatallyHit(other)) {
            gamePlayManager.addScorePoints(-1);
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new DeadEnemy(gameView, gamePlayManager, position));
        }
    }

    @Override
    public void updateStatus() {
        super.updateStatus();

        System.out.println(direction);
        if (hitPossible()) {
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
        if (gameView.timer(changeDirectionInterval, this)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
            direction = movementPattern.getDirection();

            changeDirectionInterval = random.nextInt(ENEMY_CHANGE_DIRECTION_INTERVAL_START_IN_MILLISECONDS, ENEMY_CHANGE_DIRECTION_INTERVAL_END_IN_MILLISECONDS);
        }
    }

    @Override
    public String toString() {
        return "EnemyGunnerBlockImages: %s with movementPattern: %s".formatted(position, movementPattern);
    }
}
