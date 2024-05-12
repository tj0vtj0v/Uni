package thd.gameobjects.base;

import thd.game.utilities.GameView;

/**
 * Storage class for all constants in the game.
 */
public interface GameObjectConstants {
    // Display
    public static final char BACKGROUND = 0;
    public static final char LAYER_1 = 50;
    public static final char LAYER_2 = 100;
    public static final char LAYER_3 = 150;
    public static final char LAYER_4 = 200;
    public static final char LAYER_5 = 250;
    public static final char FOREGROUND = 255;
    public static final int BLOCK_IMAGE_SIZE = 3;

    // Animation
    public static final int ANIMATION_SPEED = 50;

    // MovingCharacter
    public static final int MOVING_CHARACTER_BULLET_X_OFFSET = BLOCK_IMAGE_SIZE * 2;
    public static final int MOVING_CHARACTER_BULLET_Y_OFFSET = BLOCK_IMAGE_SIZE * 12;
    public static final int MOVING_CHARACTER_HIT_BOX_X_OFFSET = BLOCK_IMAGE_SIZE * 2;
    public static final int MOVING_CHARACTER_HIT_BOX_Y_OFFSET = BLOCK_IMAGE_SIZE * 2;
    public static final int MOVING_CHARACTER_HIT_BOX_WIDTH_OFFSET = BLOCK_IMAGE_SIZE * -4;
    public static final int MOVING_CHARACTER_HIT_BOX_HEIGHT_OFFSET = BLOCK_IMAGE_SIZE * -8;
    public static final int ENEMY_CHANGE_DIRECTION_INTERVAL_START_IN_MILLISECONDS = 1500;
    public static final int ENEMY_CHANGE_DIRECTION_INTERVAL_END_IN_MILLISECONDS = 3000;

    // UnmovingEnemies
    public static final int MORTAR_X_OFFSET_IF_RIGHT = BLOCK_IMAGE_SIZE * -11;
    public static final int MORTAR_X_OFFSET_IF_LEFT = BLOCK_IMAGE_SIZE * 9;
    public static final int MORTAR_Y_OFFSET = BLOCK_IMAGE_SIZE * 7;
    public static final int MORTAR_START_WAITING_TIME = 500;
    public static final int MORTAR_END_WAITING_TIME = 2000;
    public static final int MORTAR_LOADING_TIME = 800;
    public static final int MORTAR_SHOOTING_TIME = 400;
    public static final int SHOOTING_BOX_START_SHOOTING_TIME = 500;
    public static final int SHOOTING_BOX_END_SHOOTING_TIME = 2000;

    // Vehicles
    public static final int VEHICLE_EXPLOSION_Y_OFFSET = BLOCK_IMAGE_SIZE * 10;
    public static final int VEHICLE_EXPLOSION_2_X_OFFSET = BLOCK_IMAGE_SIZE * 25;
    public static final int HUMVEE_HIT_BOX_X_OFFSET = 0;
    public static final int HUMVEE_HIT_BOX_Y_OFFSET = BLOCK_IMAGE_SIZE * 1;
    public static final int HUMVEE_HIT_BOX_WIDTH_OFFSET = BLOCK_IMAGE_SIZE * -4;
    public static final int HUMVEE_HIT_BOX_HEIGHT_OFFSET = BLOCK_IMAGE_SIZE * -6;
    public static final int MOPED_HIT_BOX_X_OFFSET = BLOCK_IMAGE_SIZE * 1;
    public static final int MOPED_HIT_BOX_Y_OFFSET = BLOCK_IMAGE_SIZE * 9;
    public static final int MOPED_HIT_BOX_WIDTH_OFFSET = BLOCK_IMAGE_SIZE * -2;
    public static final int MOPED_HIT_BOX_HEIGHT_OFFSET = BLOCK_IMAGE_SIZE * -15;

    // MovementPattern
    public static final int PARABOLIC_RANDOM_TRAJECTORY_START = 10;
    public static final int PARABOLIC_RANDOM_TRAJECTORY_END = 30;
    public static final int PARABOLIC_RANDOM_STEPS_START = 20;
    public static final int PARABOLIC_RANDOM_STEPS_END = 30;
    public static final int PARABOLIC_SPAWN_LEFT_X_OFFSET = BLOCK_IMAGE_SIZE * -9;
    public static final int PARABOLIC_SPAWN_RIGHT_X_OFFSET = BLOCK_IMAGE_SIZE * 18;

    // Default
    public static final int DEFAULT_SHOOT_COOLDOWN_IN_MILLISECONDS = 300;
    public static final int DEFAULT_BULLET_SPEED_IN_PIXEL = 10;
    public static final int DEFAULT_GRENADE_SPEED_IN_PIXEL = 8;
    public static final int DEFAULT_MAIN_CHARACTER_SPEED_IN_PIXEL = 2;
    public static final int DEFAULT_ENEMY_SPEED_IN_PIXEL = 2;
    public static final int DEFAULT_VEHICLE_SPEED_IN_PIXEL = 6;
    public static final int DEFAULT_VEHICLE_SPAWN_DISTANCE = GameView.HEIGHT / 2;
    public static final int DEFAULT_SPAWN_DISTANCE = GameView.HEIGHT;
    public static final int DEFAULT_HUMVEE_HIT_TOLERANCE = 5;
    public static final int DEFAULT_MOPED_HIT_TOLERANCE = 3;
    public static final int DEFAULT_SHOOTING_BOX_HIT_TOLERANCE = 2;
}
