package thd.gameobjects.movable;


import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Representation of the in-game-object 'Bullet'.
 * <p>
 * initial influenced linear movement
 * indestructible
 * png textured
 */
public class Bullet extends GameObject {
    /**
     * Crates a new Bullet.
     *
     * @param gameView GameView to show the bullet on.
     */
    public Bullet(GameView gameView) {
        super(gameView);
    }
}
