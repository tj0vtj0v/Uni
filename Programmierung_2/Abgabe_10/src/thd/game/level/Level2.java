package thd.game.level;

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
        mainCharacterMovementSpeed = 2;
    }
}
