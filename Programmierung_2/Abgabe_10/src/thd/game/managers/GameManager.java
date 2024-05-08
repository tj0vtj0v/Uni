package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.GameView;


class GameManager extends LevelManager {
    GameManager(GameView gameView) {
        super(gameView);
    }

    public void startNewGame() {
        Level.difficulty = Difficulty.EASY;
        initializeGame();
    }

    private void gameManagement() {
        if (endOfGame()) {
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Game Over");
            } else if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                startNewGame();
            }
        } else if (endOfLevel()) {
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Great Job!");
            } else if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                switchToNextLevel();
                initializeLevel();
            }
        } else if (mainCharacter.isDead()) {
            if (gameView.timer(200, this)) {
                overlay.toggleMessage("YOU DIED");
            }
            if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                initializeLevel();
            }
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
        overlay.showMessage("Level %d\n%s".formatted(level.number, level.name), 2);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }
}

