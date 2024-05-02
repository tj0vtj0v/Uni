package thd.game.managers;

import thd.game.level.*;
import thd.game.utilities.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class LevelManager extends GameWorldManager {
    private List<Level> levels;
    ListIterator<Level> levelIterator;

    LevelManager(GameView gameView) {
        super(gameView);

        levels = new ArrayList<>(List.of(new Level1(), new Level2(), new Level3()));
        levelIterator = levels.listIterator();

        level = levelIterator.next();
    }

    protected boolean hasNextLevel() {
        return true;
    }

    protected void switchToNextLevel() {
        if (hasNextLevel()) {
            level = levelIterator.next();
        } else {
            throw new NoMoreLevelsAvaliableException("There are no more Levels left.");
        }
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        initializeGameObjects();
    }
}
