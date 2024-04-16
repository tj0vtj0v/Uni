package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Stone;

import java.awt.*;
import java.awt.event.KeyEvent;


class GameManager extends UserControlledGameObjectPool {
    protected GameManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();

        humvee.updatePosition();
        humvee.addToCanvas();

        moped.updatePosition();
        moped.addToCanvas();

        enemyGunner.updatePosition();
        enemyGunner.addToCanvas();

        mainCharacter.updateStatus();
        mainCharacter.updatePosition();
        mainCharacter.addToCanvas();

        stone.addToCanvas();
        scoreBoard.addToCanvas();
    }
}
