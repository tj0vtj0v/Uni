package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.Bullet;

/**
 * Representation of the in-game-object 'Stone'.
 * <p>
 * unmoving
 * indestructible
 * blocks {@link Bullet}
 * BlockImage
 */
public class Stone extends CollidingGameObject implements ShiftableGameObject {

    /**
     * Creates Stone with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Stone(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        blockImage = ObjectBlockImages.STONE;
        distanceToBackground = 200;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(3, 6, -6, -18);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public String toString() {
        return "Stone: %s".formatted(position);
    }
}
