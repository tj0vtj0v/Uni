package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {
    protected final GameView gameView;
    protected Humvee humvee;
    protected Moped moped;
    protected ScoreBoard scoreBoard;
    protected ShootingBox shootingBox;
    protected Tree tree;
    protected Wall wall;
    protected Stone stone;
    protected MainCharacterUnluckyLuke mainCharacter;
    protected EnemyGunner enemyGunner;
    protected EnemyMortar enemyMortar;

    protected UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKeys) {
            processKeyCode(keyCode);
        }
    }

    private void processKeyCode(int keyCode) {
        if (keyCode == KeyEvent.VK_A) {
            mainCharacter.left();
        } else if (keyCode == KeyEvent.VK_D) {
            mainCharacter.right();
        } else if (keyCode == KeyEvent.VK_W) {
            mainCharacter.up();
        } else if (keyCode == KeyEvent.VK_S) {
            mainCharacter.down();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            mainCharacter.shoot();
        }
    }
}
