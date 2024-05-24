package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameConstants;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.unmovable.Overlay;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

class UserControlledGameObjectPool implements GameConstants {
    private int spaceHold;
    private boolean spacePressed;

    protected final GameView gameView;
    protected Level level;
    MainCharacterImpl mainCharacter;
    Overlay overlay;

    UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
        spaceHold = 0;
        spacePressed = false;
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        Arrays.sort(pressedKeys, (i1, i2) -> Integer.compare(i2, i1));
        spacePressed = false;

        if (pressedKeys.length != 0 && pressedKeys[0] != KeyEvent.VK_SPACE) {
            mainCharacter.resetDirection();
        }

        for (int keyCode : pressedKeys) {
            processKeyCode(keyCode);
        }

        spaceHold += (spacePressed || spaceHold == 1) ? 1 : -spaceHold;

        switch (spaceHold) {
            case 1, 2:
                mainCharacter.shoot();
                break;
            case 50, 70:
                mainCharacter.throwGrenade();
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
                spacePressed = true;
        }
    }
}
