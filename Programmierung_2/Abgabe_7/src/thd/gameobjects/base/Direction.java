package thd.gameobjects.base;

import java.util.Arrays;

/**
 * This Enum-Class contains all movement directions of the Characters.
 */
public enum Direction {
    /**
     * Represents the direction 'left'.
     */
    LEFT,
    /**
     * Represents the direction 'down'.
     */
    DOWN,
    /**
     * Represents the diagonal direction 'right' and 'up'.
     */
    UP_RIGHT,
    /**
     * Represents the diagonal direction 'left' and 'up'.
     */
    UP_LEFT,
    /**
     * Represents the diagonal direction 'right' and 'down'.
     */
    DOWN_RIGHT,
    /**
     * Represents the diagonal direction 'left' and 'down'.
     */
    DOWN_LEFT,
    /**
     * Represents the direction 'up'.
     */
    UP,
    /**
     * Represents the direction 'right'.
     */
    RIGHT;

    public Direction opposite() {
        return Direction.values()[Direction.values().length - this.ordinal() - 1];
    }
}
