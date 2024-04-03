package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Bullet;

/**
 * Representation of the in-game-object 'Stone'.
 * <p>
 * unmoving
 * indestructible
 * blocks {@link Bullet}
 * png textured
 */
public class Stone extends GameObject {

    /**
     * Creates Stone with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public Stone(GameView gameView) {
        super(gameView);

        size = 3;
        rotation = 0;
        width = 16;
        height = 0;

        position.updateCoordinates(GameView.WIDTH - (width * size), 0);
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ObjectBlockImages.STONE, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Tree: %s".formatted(position);
    }
}
