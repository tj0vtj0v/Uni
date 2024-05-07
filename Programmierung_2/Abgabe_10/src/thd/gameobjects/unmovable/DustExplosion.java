package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.Position;

public class DustExplosion extends Explosion {
    public DustExplosion(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        this.blockImage = blockImage.replaceAll("9", "7");
    }
}
