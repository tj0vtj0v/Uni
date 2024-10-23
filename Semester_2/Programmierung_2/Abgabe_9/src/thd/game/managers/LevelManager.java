package thd.game.managers;

import thd.game.level.*;
import thd.game.utilities.GameView;

import java.util.List;

class LevelManager extends GameWorldManager {
    private List<Level> levels;

    protected LevelManager(GameView gameView) {
        super(gameView);
    }

    protected void initializeGame() {
        levels = List.of(new Level1(), new Level2(), new Level3());
        level = levels.get(0);
        lives = LIVES;
        points = 0;
    }

    protected boolean hasNextLevel() {
        return levels.size() > levels.indexOf(level) + 1;
    }

    protected void switchToNextLevel() {
        if (hasNextLevel()) {
            level = levels.get(levels.indexOf(level) + 1);
        } else {
            throw new NoMoreLevelsAvailableException("There are no more Levels left.");
        }
    }

    private void initializeGameObjects() {
        // adjust background
        // update lives
        // take over points
        // restart countdown
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        initializeGameObjects();
    }
}
