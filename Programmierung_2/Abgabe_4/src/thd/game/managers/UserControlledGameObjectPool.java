package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Stone;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UserControlledGameObjectPool {
    protected GameView gameView;
    protected final Humvee humvee;
    protected final Moped moped;
    protected final Stone stone;
    protected final EnemyGunner enemyGunner;
    protected final MainCharacter mainCharacter;
    protected final Bullet bullet;
    protected final Grenade grenade;

    protected UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;

        humvee = new Humvee(gameView);
        moped = new Moped(gameView);
        stone = new Stone(gameView);

        enemyGunner = new EnemyGunner(gameView);
        mainCharacter = new MainCharacter(gameView);

        bullet = new Bullet(gameView);
        grenade = new Grenade(gameView);
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
