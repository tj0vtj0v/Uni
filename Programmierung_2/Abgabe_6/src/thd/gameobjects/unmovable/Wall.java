package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
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
public class Wall extends CollidingGameObject {

    /**
     * Creates Wall with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Wall(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        size = 3;
        rotation = 0;
        width = 16;
        height = 0;

        position.updateCoordinates(GameView.WIDTH - 400, 300);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(new ObjectBlockImages().wall(5), position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "Wall: %s".formatted(position);
    }
}
