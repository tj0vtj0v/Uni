package thd.gameobjects.base;

import thd.game.utilities.GameView;

/**
 * Storage class for all constants in the game.
 */
public interface GameObjectConstants {
    // Display constants
    public static final char BACKGROUND = 0;
    public static final char LAYER_1 = 50;
    public static final char LAYER_2 = 100;
    public static final char LAYER_3 = 150;
    public static final char LAYER_4 = 200;
    public static final char LAYER_5 = 250;
    public static final char FOREGROUND = 255;
    public static final int BLOCKIMAGE_SIZE = 3;

    // Moving Character constants
    public static final int MOVING_CHARACTER_BULLET_X_OFFSET = 7;
    public static final int MOVING_CHARACTER_BULLET_Y_OFFSET = 36;
    public static final int MOVING_CHARACTER_HIT_BOX_X_OFFSET = 6;
    public static final int MOVING_CHARACTER_HIT_BOX_Y_OFFSET = 6;
    public static final int MOVING_CHARACTER_HIT_BOX_WIDTH_OFFSET = -12;
    public static final int MOVING_CHARACTER_HIT_BOX_HEIGHT_OFFSET = -24;
    public static final int ENEMY_CHANGE_DIRECTION_INTERVALL_START_IN_MILLISECONDS = 1500;
    public static final int ENEMY_CHANGE_DIRECTION_INTERVALL_END_IN_MILLISECONDS = 3000;


    // Game Object constants
    public static final int HUMVEE_HIT_BOX_X_OFFSET = 0;
    public static final int HUMVEE_HIT_BOX_Y_OFFSET = 3;
    public static final int HUMVEE_HIT_BOX_WIDTH_OFFSET = -12;
    public static final int HUMVEE_HIT_BOX_HEIGHT_OFFSET = -18;
    public static final int MOPED_HIT_BOX_X_OFFSET = 3;
    public static final int MOPED_HIT_BOX_Y_OFFSET = 27;
    public static final int MOPED_HIT_BOX_WIDTH_OFFSET = -6;
    public static final int MOPED_HIT_BOX_HEIGHT_OFFSET = -45;

    // Default constants
    public static final int DEFAULT_SHOOT_COOLDOWN_IN_MILLISECONDS = 300;
    public static final int DEFAULT_BULLET_SPEED_IN_PIXEL = 10;
    public static final int DEFAULT_GRENADE_SPEED_IN_PIXEL = 8;
    public static final int DEFAULT_MAIN_CHARACTER_SPEED_IN_PIXEL = 8;
    public static final int DEFAULT_ENEMY_SPEED_IN_PIXEL = 2;
    public static final int DEFAULT_VEHICLE_SPEED_IN_PIXEL = 5;
    public static final int DEFAULT_VEHICLE_LAUNCH_DISTANCE = GameView.HEIGHT / 2;
    public static final int DEFAULT_HUMVEE_HIT_TOLERANCE = 5;
    public static final int DEFAULT_MOPED_HIT_TOLERANCE = 3;
}
