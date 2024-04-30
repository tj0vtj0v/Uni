package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

public class Explosion extends CollidingGameObject implements ShiftableGameObject {
    public Explosion(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        blockImage = ObjectBlockImages.EXPLOSION;
        distanceToBackground = 255;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(-3, -3, 6, 6);

        speedInPixel = 0;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(8000, this)) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
