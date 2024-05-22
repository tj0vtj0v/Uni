package thd.game.level;

import thd.game.utilities.GameView;

/**
 * Representation of the first Level.
 */
public class Level1 extends Level {
    /**
     * Creates the first Level.
     */
    public Level1() {
        name = "Level 1";
        number = 1;
        music = "theme1";
        world = World.LEVEL_1;
        worldOffsetLines = Math.max(world.split("\n").length - VISIBLE_COLUMNS, 0);
        worldOffsetColumns = 10;
        worldScale = GameView.WIDTH / (world.split("\n")[0].length() - worldOffsetColumns - 1);

        mainCharacterSpeedInPixel = 2;
    }
}
