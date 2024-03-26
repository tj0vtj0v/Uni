package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;
import thd.gameobjects.movable.Humvee;
import thd.gameobjects.movable.Moped;
import thd.gameobjects.unmovable.Stone;


class GameManager {
    private final GameView gameView;
    private final Humvee humvee;
    private final Moped moped;
    private final Stone scoreBoard;
    private final Bullet bullet;
    private final Grenade grenade;

    protected GameManager(GameView gameView) {
        this.gameView = gameView;

        humvee = new Humvee(gameView);
        moped = new Moped(gameView);
        scoreBoard = new Stone(gameView);

        bullet = new Bullet();
        grenade = new Grenade();
    }

    void gameLoopUpdate() {
        humvee.updatePosition();
        humvee.addToCanvas();

        moped.updatePosition();
        moped.addToCanvas();

        scoreBoard.addToCanvas();
    }
}
