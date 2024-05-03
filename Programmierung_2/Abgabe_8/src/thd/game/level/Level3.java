package thd.game.level;

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
        world = World.LEVEL_3;
        worldOffsetLines = Math.max(world.split("\n").length - VISIBLE_COLUMNS, 0);
        worldOffsetColumns = 10;
        mainCharacterMovementSpeed = 3;
    }
}
