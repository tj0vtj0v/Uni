package thd.game.level;

import thd.game.utilities.GameView;

/**
 * Representation of the fourth Level.
 */
public class Level4 extends Level {
    /**
     * Creates the fourth Level.
     */
    public Level4() {
        name = "Level 4 - No Man's Land";
        number = 4;
        world = World.LEVEL_4;
        worldOffsetLines = Math.max(world.split("\n").length - VISIBLE_COLUMNS, 0);
        worldOffsetColumns = 10;
        worldScale = GameView.WIDTH / (world.split("\n")[0].length() - worldOffsetColumns - 1);

        mainCharacterShotCooldown += 100;
    }
}
