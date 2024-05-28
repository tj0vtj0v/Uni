package thd.gameobjects.base;

import thd.game.utilities.GameView;

/**
 * Storage class for all constants in the game.
 */
public interface GameConstants {
    // Game
    /**
     * Represents a Constant in the Category Game.
     */
    int LIVES = 999;
    /**
     * Represents a Constant in the Category Game.
     */
    int MAXIMUM_NUMBER_OF_GAME_OBJECTS = 500;
    /**
     * Represents a Constant in the Category Game.
     */
    int GAME_OVER_DISPLAY_TIME = 2000;
    /**
     * Represents a Constant in the Category Game.
     */
    int LEVEL_OVER_DISPLAY_TIME = 2000;
    /**
     * Represents a Constant in the Category Game.
     */
    int DEATH_DISPLAY_TOGGLE_TIME = 200;
    /**
     * Represents a Constant in the Category Game.
     */
    int DEATH_RESET_DELAY = 3000;

    // Display
    /**
     * Represents a Constant in the Category Display.
     */
    char BACKGROUND = 0;
    /**
     * Represents a Constant in the Category Display.
     */
    char LAYER_1 = 50;
    /**
     * Represents a Constant in the Category Display.
     */
    char LAYER_2 = 100;
    /**
     * Represents a Constant in the Category Display.
     */
    char LAYER_3 = 150;
    /**
     * Represents a Constant in the Category Display.
     */
    char LAYER_4 = 200;
    /**
     * Represents a Constant in the Category Display.
     */
    char LAYER_5 = 250;
    /**
     * Represents a Constant in the Category Display.
     */
    char FOREGROUND = 255;
    /**
     * Represents a Constant in the Category Display.
     */
    int BLOCK_IMAGE_SIZE = 3;
    /**
     * Represents a Constant in the Category Display.
     */
    double BACKGROUND_DARK_PIXEL_PROBABILITY = 0.1;
    /**
     * Represents a Constant in the Category Display.
     */
    int SCOREBOARD_HEIGHT = 80;
    /**
     * Represents a Constant in the Category Display.
     */
    int SCOREBOARD_FONT_SIZE = 40;

    // Animation
    /**
     * Represents a Constant in the Category Animation.
     */
    int ANIMATION_SPEED = 50;

    // MovingCharacter
    /**
     * Represents a Constant in the Category MovingCharacter.
     */
    int ENEMY_CHANGE_DIRECTION_INTERVAL_START_IN_MILLISECONDS = 1500;
    /**
     * Represents a Constant in the Category MovingCharacter.
     */
    int ENEMY_CHANGE_DIRECTION_INTERVAL_END_IN_MILLISECONDS = 3000;

    // UnmovingEnemies
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int MORTAR_X_OFFSET_IF_RIGHT = BLOCK_IMAGE_SIZE * -11;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int MORTAR_X_OFFSET_IF_LEFT = BLOCK_IMAGE_SIZE * 9;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int MORTAR_Y_OFFSET = BLOCK_IMAGE_SIZE * 7;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int MORTAR_START_WAITING_TIME = 500;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int MORTAR_END_WAITING_TIME = 2000;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int MORTAR_SHOOTING_TIME = 400;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int SHOOTING_BOX_START_SHOOTING_TIME = 500;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int SHOOTING_BOX_END_SHOOTING_TIME = 2000;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int SHOOTING_BOX_EXPLOSION_X_OFFSET = BLOCK_IMAGE_SIZE * 4;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int SHOOTING_BOX_EXPLOSION_Y_OFFSET = BLOCK_IMAGE_SIZE * 15;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int SHOOTING_BOX_RUIN_Y_OFFSET = BLOCK_IMAGE_SIZE * 11;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int SHOOTING_BOX_BULLET_X_OFFSET = BLOCK_IMAGE_SIZE * 10;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int SHOOTING_BOX_BULLET_Y_OFFSET = BLOCK_IMAGE_SIZE * 10;
    /**
     * Represents a Constant in the Category UnmovingEnemy.
     */
    int SHOOTING_BOX_BULLET_EXPLOSION_X_OFFSET = BLOCK_IMAGE_SIZE * -5;

    // Vehicles
    /**
     * Represents a Constant in the Category Vehicles.
     */
    int VEHICLE_EXPLOSION_Y_OFFSET = BLOCK_IMAGE_SIZE * 10;
    /**
     * Represents a Constant in the Category Vehicles.
     */
    int VEHICLE_EXPLOSION_2_X_OFFSET = BLOCK_IMAGE_SIZE * 25;

    // MovementPattern
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int PARABOLIC_RANDOM_TRAJECTORY_START = 10;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int PARABOLIC_RANDOM_TRAJECTORY_END = 30;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int PARABOLIC_RANDOM_STEPS_START = 20;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int PARABOLIC_RANDOM_STEPS_END = 30;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int PARABOLIC_SPAWN_LEFT_X_OFFSET = BLOCK_IMAGE_SIZE * -9;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int PARABOLIC_SPAWN_RIGHT_X_OFFSET = BLOCK_IMAGE_SIZE * 18;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int RANDOM_GLOBAL_SPAWN_LEFT_OFFSET = -30;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int RANDOM_GLOBAL_SPAWN_RIGHT_OFFSET = 20;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int RANDOM_START_TARGET_DISTANCE = 150;
    /**
     * Represents a Constant in the Category MovementPattern.
     */
    int RANDOM_END_TARGET_DISTANCE = 500;

    // Default
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_SHOOT_COOLDOWN_IN_MILLISECONDS = 300;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_BULLET_SPEED_IN_PIXEL = 10;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_GRENADE_SPEED_IN_PIXEL = 8;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_MAIN_CHARACTER_SPEED_IN_PIXEL = 2;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_ENEMY_SPEED_IN_PIXEL = 2;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_VEHICLE_SPEED_IN_PIXEL = 6;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_VEHICLE_SPAWN_DISTANCE = GameView.HEIGHT / 2;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_SPAWN_DISTANCE = GameView.HEIGHT;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_HUMVEE_HIT_TOLERANCE = 5;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_MOPED_HIT_TOLERANCE = 3;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_SHOOTING_BOX_HIT_TOLERANCE = 2;
    /**
     * Represents a DEFAULT-VALUE Constant.
     */
    int DEFAULT_MORTAR_LOADING_TIME = 800;
}
