package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.GameView;


class GameManager extends LevelManager {
    GameManager(GameView gameView) {
        super(gameView);
    }
    private int backgroundMusicID;

    void startNewGame() {
        Level.difficulty = Difficulty.EASY;
        initializeGame();
    }

    private void gameManagement() {
        if (endOfGame()) {
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Game Over");
            } else if (gameView.timer(GAME_OVER_DISPLAY_TIME, this)) {
                overlay.stopShowing();
                startNewGame();
            }
        } else if (endOfLevel()) {
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Great Job!");
            } else if (gameView.timer(LEVEL_OVER_DISPLAY_TIME, this)) {
                overlay.stopShowing();
                switchToNextLevel();
                initializeLevel();
            }
        } else if (mainCharacter.isDead()) {
            if (gameView.timer(DEATH_DISPLAY_TOGGLE_TIME, this)) {
                overlay.toggleMessage("YOU DIED");
            }
            if (gameView.timer(DEATH_RESET_DELAY, this)) {
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

        overlay.showMessage(level.name, 2);

        gameView.stopSound(backgroundMusicID);
        backgroundMusicID = gameView.playSound(level.music + ".wav", true);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }
}

