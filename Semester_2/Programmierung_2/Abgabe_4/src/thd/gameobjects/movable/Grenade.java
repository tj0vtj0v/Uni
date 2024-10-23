package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Representation of the in-game-object 'Bullet'.
 * <p>
 * initial influenced parabolic movement
 * indestructible
 * BlockImage
 */
public class Grenade extends GameObject {
    /**
     * Crates a new Grenade.
     *
     * @param gameView GameView to show the grenade on.
     */
    public Grenade(GameView gameView) {
        super(gameView);
    }
}
