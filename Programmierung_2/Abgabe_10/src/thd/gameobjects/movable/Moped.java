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
public class Moped extends Vehicle {


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

        hitTolerance = gamePlayManager.currentLevel().mopedSpeedInPixel;

        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(GameObjectConstants.MOPED_HIT_BOX_X_OFFSET, GameObjectConstants.MOPED_HIT_BOX_Y_OFFSET, GameObjectConstants.MOPED_HIT_BOX_WIDTH_OFFSET, GameObjectConstants.MOPED_HIT_BOX_HEIGHT_OFFSET);

        speedInPixel = gamePlayManager.;
    }

    @Override
    public String toString() {
        return "Moped: %s with %d hits left till destruction".formatted(position, hitTolerance);
    }
}
