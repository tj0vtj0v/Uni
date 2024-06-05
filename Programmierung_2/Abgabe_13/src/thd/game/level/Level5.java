package thd.game.level;

import thd.game.utilities.GameView;

/**
 * Representation of the fifth Level.
 */
public class Level5 extends Level {
    /**
     * Creates the fifth Level.
     */
    public Level5() {
        name = "Level 5 - The Minefield";
        number = 5;
        world = World.LEVEL_5;
        worldOffsetLines = Math.max(world.split("\n").length - VISIBLE_COLUMNS, 0);
        worldOffsetColumns = 10;
        worldScale = GameView.WIDTH / (world.split("\n")[0].length() - worldOffsetColumns - 1);

        mainCharacterShotCooldown += 100;
        bulletSpeedInPixel += 2;
        enemyShotCooldown -= 50;
        mortarLoadingTime -= 200;
    }
}
