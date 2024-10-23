package thd.game.bin;

import thd.game.managers.GameViewManager;

/**
 * Trigger for the game.
 */
public class StartGame {
    /**
     * Main method for execution at call.
     *
     * @param args given arguments with call.
     */
    public static void main(String[] args) {
        GameViewManager gameViewManager = new GameViewManager();
        gameViewManager.startGame();
    }
}
