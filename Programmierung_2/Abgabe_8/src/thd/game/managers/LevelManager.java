package thd.game.managers;

import thd.game.level.*;
import thd.game.utilities.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class LevelManager extends GameWorldManager {
    private final ListIterator<Level> levelIterator;

    LevelManager(GameView gameView) {
        super(gameView);

        List<Level> levels = new ArrayList<>(List.of(new Level1(), new Level2(), new Level3()));
        levelIterator = levels.listIterator();
        switchToNextLevel();
    }

    protected boolean hasNextLevel() {
        return levelIterator.hasNext();
    }

    protected void switchToNextLevel() {
        if (hasNextLevel()) {
            level = levelIterator.next();
            System.out.println(level);
        } else {
            throw new NoMoreLevelsAvaliableException("There are no more Levels left.");
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
