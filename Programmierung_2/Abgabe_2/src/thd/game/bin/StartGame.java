package thd.game.bin;

import thd.game.managers.GameViewManager;

public class StartGame {
    public static void main(String[] args) {
        GameViewManager gameViewManager = new GameViewManager();
        gameViewManager.startGame();
    }
}
