package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Just for passing the Wichtel.
 */
public class Dummie extends GameObject {
    /**
     * Creates Dummie with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public Dummie(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        distanceToBackground = 15;
    }

    @Override
    public void addToCanvas() {
        gamePlayManager.moveWorldDown(0);
    }
}
