package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Stone;


class GameManager {
    private final GameView gameView;
    private final Humvee humvee;
    private final Moped moped;
    private final Stone stone;
    private final EnemyGunner enemyGunner;
    private final Bullet bullet;
    private final Grenade grenade;


    protected GameManager(GameView gameView) {
        this.gameView = gameView;

        humvee = new Humvee(gameView);
        moped = new Moped(gameView);
        stone = new Stone(gameView);

        enemyGunner = new EnemyGunner(gameView);

        bullet = new Bullet(gameView);
        grenade = new Grenade(gameView);
    }

    void gameLoopUpdate() {
        humvee.updatePosition();
        humvee.addToCanvas();

        moped.updatePosition();
        moped.addToCanvas();

        enemyGunner.updatePosition();
        enemyGunner.addToCanvas();

        stone.addToCanvas();
    }
}
