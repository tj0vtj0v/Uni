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

    private final RandomBall randomBall;
    private final FollowerBall followerBall;

    protected GameManager(GameView gameView) {
        this.gameView = gameView;

        humvee = new Humvee(gameView);
        moped = new Moped(gameView);
        stone = new Stone(gameView);

        enemyGunner = new EnemyGunner(gameView);

        bullet = new Bullet(gameView);
        grenade = new Grenade(gameView);

        randomBall = new RandomBall(gameView);
        followerBall = new FollowerBall(gameView, randomBall);
    }

    void gameLoopUpdate() {
        humvee.updatePosition();
        humvee.addToCanvas();

        moped.updatePosition();
        moped.addToCanvas();

        enemyGunner.updatePosition();
        enemyGunner.addToCanvas();

        randomBall.updatePosition();
        randomBall.addToCanvas();

        followerBall.updatePosition();
        followerBall.addToCanvas();

        stone.addToCanvas();
    }
}
