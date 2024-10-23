package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;


class GameManager extends UserControlledGameObjectPool {
    private final GameObjectManager gameObjectManager;

    protected GameManager(GameView gameView) {
        super(gameView);
        this.gameObjectManager = new GameObjectManager();

        scoreBoard = new ScoreBoard(gameView);

        humvee = new Humvee(gameView);
        moped = new Moped(gameView);
        shootingBox = new ShootingBox(gameView);

        stone = new Stone(gameView);
        tree = new Tree(gameView);
        wall = new Wall(gameView);

        mainCharacter = new MainCharacter(gameView);

        enemyGunner = new EnemyGunner(gameView);
        enemyMortar = new EnemyMortar(gameView);

        bullet = new Bullet(gameView);
        grenade = new Grenade(gameView);

        gameObjectManager.add(humvee);
        gameObjectManager.add(moped);
        gameObjectManager.add(shootingBox);
        gameObjectManager.add(enemyGunner);
        gameObjectManager.add(enemyMortar);
        gameObjectManager.add(mainCharacter);
        gameObjectManager.add(stone);
        gameObjectManager.add(tree);
        gameObjectManager.add(wall);
        gameObjectManager.add(scoreBoard);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();

        gameObjectManager.gameLoopUpdate();
    }
}

