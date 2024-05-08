package thd.game.level;

/**
 * Baseclass of all Levels.
 */
public class Level {
    /**
     * Constant Representing the Visible Lines on the gameView.
     */
    public static final int VISIBLE_COLUMNS = 32;
    /**
     * Represents the difficulty of the game.
     */
    public static Difficulty difficulty = Difficulty.STANDARD;
    /**
     * Represents the name of the Level.
     */
    public String name;
    /**
     * Represents the number of the Level. TODO: change GameManager.initializeLevel
     */
    int number;
    /**
     * Represents the World with its ObjectBlockImages in it.
     */
    public String world;
    /**
     * Represents the translation from worldString position to position on canvas.
     */
    public int worldScale;
    /**
     * Amount of downshift for every Map.
     */
    public int worldOffsetLines;
    /**
     * Amount of characters on the left side of the {@see worldString} that aren't shown.
     */
    public int worldOffsetColumns;
    /**
     * MovementSpeed of MainCharacterImpl.
     */
    public int mainCharacterMovementSpeed;
}
