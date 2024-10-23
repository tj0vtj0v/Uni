package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.movable.Bullet;

/**
 * Representation of the in-game-object 'Wall'.
 * <p>
 * unmoving
 * indestructible
 * blocks {@link Bullet}
 * BlockImage
 */
public class Tree extends GameObject {

    /**
     * Creates Wall with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public Tree(GameView gameView) {
        super(gameView);

        size = 3;
        rotation = 0;
        width = 16;
        height = 0;

        position.updateCoordinates(400, 400);
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ObjectBlockImages.TREE, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Tree: %s".formatted(position);
    }
}
