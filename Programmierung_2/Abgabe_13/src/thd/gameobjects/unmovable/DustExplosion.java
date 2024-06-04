package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.Position;

/**
 * converts a regular Explosion in a dustExplosion.
 */
public class DustExplosion extends Explosion {
    /**
     * Creates a dustExplosion.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position where to be located.
     */
    public DustExplosion(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        this.blockImage = blockImage.replaceAll("9", "7");
    }
}
