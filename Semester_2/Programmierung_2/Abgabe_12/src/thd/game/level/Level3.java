package thd.game.level;

import thd.game.utilities.GameView;

/**
 * Representation of the third Level.
 */
public class Level3 extends Level {
    /**
     * Creates the third Level.
     */
    public Level3() {
        name = "Level 3";
        number = 3;
        music = "theme3";
        world = World.LEVEL_3;
        worldOffsetLines = Math.max(world.split("\n").length - VISIBLE_COLUMNS, 0);
        worldOffsetColumns = 10;
        worldScale = GameView.WIDTH / (world.split("\n")[0].length() - worldOffsetColumns - 1);

        mainCharacterSpeedInPixel = 1;
        mainCharacterShotCooldown += 200;
        bulletSpeedInPixel += 2;
        enemyShotCooldown -= 50;
        mortarLoadingTime -= 200;
    }
}
