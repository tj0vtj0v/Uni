package thd.game.level;

import thd.gameobjects.base.GameConstants;

/**
 * Baseclass of all Levels.
 */
public class Level implements GameConstants {
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
    public int mainCharacterSpeedInPixel;
    /**
     * Shot Cooldown of MainCharacterImpl.
     */
    public int mainCharacterShotCooldown;
    /**
     * MovementSpeed of all Bullets.
     */
    public int bulletSpeedInPixel;
    /**
     * MovementSpeed of all Grenades.
     */
    public int grenadeSpeedInPixel;
    /**
     * MovementSpeed of Enemy's.
     */
    public int enemySpeedInPixel;
    /**
     * Shot Cooldown of Enemy's.
     */
    public int enemyShotCooldown;
    /**
     * MovementSpeed of all Vehicles.
     */
    public int vehicleSpeedInPixel;
    /**
     * Hit tolerance of the humvee.
     */
    public int humveeHitTolerance;
    /**
     * Hit tolerance of the moped.
     */
    public int mopedHitTolerance;
    /**
     * distance to be activated for vehicles.
     */
    public int vehicleSpawnDistance;

    Level() {
        mainCharacterSpeedInPixel = DEFAULT_MAIN_CHARACTER_SPEED_IN_PIXEL;
        mainCharacterShotCooldown = DEFAULT_SHOOT_COOLDOWN_IN_MILLISECONDS;
        bulletSpeedInPixel = DEFAULT_BULLET_SPEED_IN_PIXEL;
        grenadeSpeedInPixel = DEFAULT_GRENADE_SPEED_IN_PIXEL;
        enemySpeedInPixel = DEFAULT_ENEMY_SPEED_IN_PIXEL;
        vehicleSpeedInPixel = DEFAULT_VEHICLE_SPEED_IN_PIXEL;
        enemyShotCooldown = DEFAULT_SHOOT_COOLDOWN_IN_MILLISECONDS;
        humveeHitTolerance = DEFAULT_HUMVEE_HIT_TOLERANCE;
        mopedHitTolerance = DEFAULT_MOPED_HIT_TOLERANCE;
        vehicleSpawnDistance = DEFAULT_VEHICLE_SPAWN_DISTANCE;
    }
}
