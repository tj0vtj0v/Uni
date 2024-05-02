package thd.game.managers;

import thd.game.utilities.GameView;


class GameManager extends LevelManager {
    GameManager(GameView gameView) {
        super(gameView);
    }

    private void gameManagement() {
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }
}

