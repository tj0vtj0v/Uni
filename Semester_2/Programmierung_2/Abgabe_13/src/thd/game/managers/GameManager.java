package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.FileAccess;
import thd.game.utilities.GameView;
import thd.screens.EndScreen;
import thd.screens.LostScreen;
import thd.screens.StartScreen;
import thd.screens.WonScreen;

import java.awt.*;
import java.util.Random;


class GameManager extends LevelManager {
    GameManager(GameView gameView) {
        super(gameView);
    }

    private int backgroundMusicID;

    void startNewGame() {
        Difficulty difficulty = FileAccess.readDifficultyFromDisc();

        difficulty = executeStartSequence(difficulty);

        FileAccess.writeDifficultyToDisc(difficulty);
        Level.difficulty = difficulty;

        initializeGame();
    }

    private Difficulty executeStartSequence(Difficulty preselectedDifficulty) {
        gameView.changeBackgroundColor(new Color(98, 63, 29));
        gameView.playSound("startmelody.wav", true);

        StartScreen startScreen = new StartScreen(gameView);
        startScreen.showStartScreenWithPreselectedDifficulty(preselectedDifficulty);

        gameView.changeBackgroundColor(new Color(162, 102, 35));
        gameView.stopAllSounds();

        return startScreen.getSelectedDifficulty();
    }

    private void executeEndSequence() {
        gameView.changeBackgroundColor(new Color(98, 63, 29));
        gameView.stopAllSounds();

        EndScreen endScreen;
        if (lives <= 0) {
            gameView.playSound("lostmelody.wav", true);
            endScreen = new LostScreen(gameView, points);
        } else {
            gameView.playSound("wonmelody.wav", true);
            endScreen = new WonScreen(gameView, points);
            if (points > highScore) {
                highScore = points;
                FileAccess.writeHighScoreToDisc(highScore);
            }
        }
        endScreen.showEndScreen();

        gameView.changeBackgroundColor(new Color(162, 102, 35));
        gameView.stopAllSounds();
    }

    private void gameManagement() {
        if (endOfGame()) {
            if (gameView.timer(GAME_OVER_TOGGLE_TIME, this)) {
                overlay.toggleMessage("Game Over");
            }
            if (gameView.timer(GAME_OVER_DISPLAY_TIME, this)) {
                overlay.stopShowing();

                executeEndSequence();

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
        return lives <= 0 || (!hasNextLevel() && endOfLevel());
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
        backgroundMusicID = gameView.playSound("theme" + new Random().nextInt(1, 8) + ".wav", true);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }
}

