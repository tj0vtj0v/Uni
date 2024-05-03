package thd.game.managers;

import thd.game.utilities.GameView;


class GameManager extends LevelManager {
    GameManager(GameView gameView) {
        super(gameView);
    }

    private void gameManagement() {
        if (endOfLevel()) {
            switchToNextLevel();
            initializeLevel();
        }
    }

    private boolean endOfLevel() {
        return gameView.timer(3000, this);
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

