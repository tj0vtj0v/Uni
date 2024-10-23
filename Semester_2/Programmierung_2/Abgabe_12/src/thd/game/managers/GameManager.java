package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.FileAccess;
import thd.game.utilities.GameView;
import thd.screens.EndScreen;
import thd.screens.StartScreen;


class GameManager extends LevelManager {
    GameManager(GameView gameView) {
        super(gameView);
    }

    private int backgroundMusicID;

    void startNewGame() {

        Difficulty difficulty = FileAccess.readDifficultyFromDisc();

        StartScreen startScreen = new StartScreen(gameView);
        startScreen.showStartScreenWithPreselectedDifficulty(difficulty);
        difficulty = startScreen.getSelectedDifficulty();

        FileAccess.writeDifficultyToDisc(difficulty);
        Level.difficulty = difficulty;

        initializeGame();
    }

    private void gameManagement() {
        if (endOfGame()) {
            if (gameView.timer(GAME_OVER_TOGGLE_TIME, this)) {
                overlay.toggleMessage("Game Over");
            }
            if (gameView.timer(GAME_OVER_DISPLAY_TIME, this)) {
                overlay.stopShowing();

                EndScreen endScreen = new EndScreen(gameView);
                endScreen.showEndScreen(points);

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
        return endReached && !enemyExisting() && !mainCharacter.isDead();
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
        endReached = false;

        gameView.stopSound(backgroundMusicID);
        gameView.stopAllSounds();
        backgroundMusicID = gameView.playSound(level.music + ".wav", true);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }
}

