package thd.game.level;

import thd.game.utilities.GameView;

/**
 * Representation of the second Level.
 */
public class Level2 extends Level {
    /**
     * Creates the second Level.
     */
    public Level2() {
        name = "Level 2";
        number = 2;
        world = World.LEVEL_2;
        worldOffsetLines = Math.max(world.split("\n").length - VISIBLE_COLUMNS, 0);
        worldOffsetColumns = 10;
        worldScale = GameView.WIDTH / (world.split("\n")[0].length() - worldOffsetColumns - 1);

        bulletSpeedInPixel -= 2;
        grenadeSpeedInPixel -= 1;
        mopedHitTolerance = 1;
        humveeHitTolerance = 1;
    }
}
