package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
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
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Grenade(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
    }

    @Override
    public void addToCanvas() {}
}
