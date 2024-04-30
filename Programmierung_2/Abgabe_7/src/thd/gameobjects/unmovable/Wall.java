package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Bullet;

/**
 * Representation of the in-game-object 'Wall'.
 * <p>
 * unmoving
 * indestructible
 * blocks {@link Bullet}
 * BlockImage
 */
public class Wall extends CollidingGameObject {

    /**
     * Creates Wall with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Wall(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position, int segments) {
        super(gameView, gamePlayManager, location, position);

        blockImage = new ObjectBlockImages().wall(segments);
        distanceToBackground = 100;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(15, 3, -33, -18);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "Wall: %s".formatted(position);
    }
}
