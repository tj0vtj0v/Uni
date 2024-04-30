package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;


/**
 * Representation of the in-game-object 'Moped'.
 * <p>
 * passive linear movement
 * destructible by 1 {@link Grenade} or 3 {@link Bullet}
 * BlockImage
 */
public class Moped extends CollidingGameObject implements ShiftableGameObject {
    private int hitTolerance;


    /**
     * Creates Moped with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Moped(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        if (this.location == Direction.RIGHT) {
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

        speedInPixel = 1;

        LinearMovementPattern movementPattern = new LinearMovementPattern(this.location.opposite(), position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition());
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet) {
            hitTolerance--;

            if (hitTolerance <= 0) {
                gamePlayManager.destroyGameObject(this);
                gamePlayManager.addScorePoints(-1);
            }
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
