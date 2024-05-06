package thd.game.managers;

import thd.game.utilities.GameView;


class GameManager extends LevelManager {
    GameManager(GameView gameView) {
        super(gameView);
    }

    private void gameManagement() {
        if (endOfGame()) {
            initializeGame();
        } else if (endOfLevel()) {
            switchToNextLevel();
            initializeLevel();
        } else if (mainCharacter.isDead() && gameView.timer(2000, this)) {
            initializeLevel();
        }
    }

    private boolean endOfGame() {
        return lives == 0 || (!hasNextLevel() && endOfLevel());
    }

    private boolean endOfLevel() {
        return collidingGameObjectsForPathDecision.isEmpty();
    }

    @Override
    protected void initializeGame() {
        super.initializeGame();
        initializeLevel();
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

