package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ObjectBlockImages;


/**
 * Representation of the in-game-object 'Humvee'.
 * <p>
 * passive linear movement
 * destructible by 1 {@link Grenade} or 5 {@link Bullet}
 * BlockImage
 */
public class Humvee extends Vehicle {

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
            blockImage = ObjectBlockImages.HUMVEE;
        } else {
            blockImage = mirrorBlockImage(ObjectBlockImages.HUMVEE);
        }

        hitTolerance = gamePlayManager.currentLevel().humveeHitTolerance;

        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(0, size, size * -4, size * -6);

        speedInPixel = gamePlayManager.currentLevel().vehicleSpeedInPixel;
    }
    @Override
    public String toString() {
        return "Humvee: %s with %d hits left till destruction".formatted(position, hitTolerance);
    }
}
