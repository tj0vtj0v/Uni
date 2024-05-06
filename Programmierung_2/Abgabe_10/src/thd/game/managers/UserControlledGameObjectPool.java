package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;

import java.awt.event.KeyEvent;
import java.util.Arrays;

class UserControlledGameObjectPool {

    protected final GameView gameView;
    protected Level level;
    MainCharacterImpl mainCharacter;

    UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        Arrays.sort(pressedKeys, (i1, i2) -> Integer.compare(i2, i1));

        if (pressedKeys.length != 0 && pressedKeys[0] != KeyEvent.VK_SPACE) {
            mainCharacter.ResetDirection();
        }

        for (int keyCode : pressedKeys) {
            processKeyCode(keyCode);
        }
    }

    private void processKeyCode(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                mainCharacter.left();
                break;
            case KeyEvent.VK_D:
                mainCharacter.right();
                break;
            case KeyEvent.VK_W:
                mainCharacter.up();
                break;
            case KeyEvent.VK_S:
                mainCharacter.down();
                break;
            case KeyEvent.VK_SPACE:
                mainCharacter.shoot();
        }
    }
}
