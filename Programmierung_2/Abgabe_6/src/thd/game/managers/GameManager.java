package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;


class GameManager extends GamePlayManager {
    GameManager(GameView gameView) {
        super(gameView);

        scoreBoard = new ScoreBoard(gameView, (GamePlayManager) this);

        humvee = new Humvee(gameView, this);
        moped = new Moped(gameView, this);
        shootingBox = new ShootingBox(gameView, this);

        stone = new Stone(gameView, this);
        tree = new Tree(gameView, this);
        wall = new Wall(gameView, this);

        mainCharacter = new MainCharacterImpl(gameView, this);

        enemyGunner = new EnemyGunner(gameView, this);
        enemyMortar = new EnemyMortar(gameView, this);

        spawnGameObject(humvee);
        spawnGameObject(moped);
        spawnGameObject(shootingBox);
        spawnGameObject(enemyGunner);
        spawnGameObject(enemyMortar);
        spawnGameObject(mainCharacter);
        spawnGameObject(stone);
        spawnGameObject(tree);
        spawnGameObject(wall);
        spawnGameObject(scoreBoard);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }

    private void gameManagement() {
    }
}

