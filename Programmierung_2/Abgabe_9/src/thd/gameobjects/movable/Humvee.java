package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.blockImages.Objects;
import thd.gameobjects.unmovable.Explosion;


/**
 * Representation of the in-game-object 'Humvee'.
 * <p>
 * passive linear movement
 * destructible by 1 {@link Grenade} or 5 {@link Bullet}
 * BlockImage
 */
public class Humvee extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private int hitTolerance;

    /**
     * Creates Humvee with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position from which to start movement.
     */
    public Humvee(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        if (this.direction == Direction.LEFT) {
            blockImage = Objects.HUMVEE;
        } else {
            blockImage = mirrorBlockImage(Objects.HUMVEE);
        }
        distanceToBackground = 100;
        hitTolerance = 5;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(0, 3, -12, -18);

        speedInPixel = 5;

        LinearMovementPattern movementPattern = new LinearMovementPattern(this.direction.opposite(), position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT / 2f;
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
        }
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public String toString() {
        return "Humvee: %s with %d hits left till destruction".formatted(position, hitTolerance);
    }
}
