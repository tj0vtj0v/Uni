package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Bullet;

/**
 * Representation of the in-game-object 'Stone'.
 * <p>
 * unmoving
 * indestructible
 * blocks {@link Bullet}
 * BlockImage
 */
public class Stone extends CollidingGameObject {

    /**
     * Creates Stone with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Stone(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        size = 3;
        rotation = 0;
        width = 16;
        height = 0;

        position.updateCoordinates(100, 500);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ObjectBlockImages.STONE, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Stone: %s".formatted(position);
    }
}
