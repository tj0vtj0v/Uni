package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
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
public class EnemyGunner extends MovingCharacter implements ShiftableGameObject, ActivatableGameObject {
    private final RandomMovementPattern movementPattern;

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView                            window in which it has to be displayed.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param location                            Stores positional information.
     * @param position                            Position from which to start movement.
     * @param collidingGameObjectsForPathDecision List of Objects that block the movement.
     */
    public EnemyGunner(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, location, position, collidingGameObjectsForPathDecision);

        blockImage = CharacterBlockImages.Enemy.Gunner.DOWN_1;
        distanceToBackground = 100;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(6, 6, -12, -24);

        speedInPixel = 1;

        movementPattern = new RandomMovementPattern();
        this.position.updateCoordinates(position);
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
    }

    @Override
    public boolean tryToActivate(Object info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet && ((Bullet) other).creator != this || other instanceof Explosion) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addScorePoints(-1);
        }
    }

    @Override
    public void updatePosition() {
        Position oldPosition = new Position(getPosition());
        position.moveToPosition(targetPosition, speedInPixel);

        if (pathIsBlocked()) {
            position.updateCoordinates(oldPosition);
        }
        if (gameView.timer(new Random().nextInt(2500, 5000), this)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
        }

        shoot();
    }

    @Override
    public String toString() {
        return "EnemyGunner: %s with movementPattern: %s".formatted(position, movementPattern);
    }
}
