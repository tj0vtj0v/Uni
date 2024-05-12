package thd.gameobjects.base;

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

    /**
     * turns the Direction all around.
     *
     * @return the opposite Direction of the Direction before.
     */
    public Direction opposite() {
        return Direction.values()[Direction.values().length - this.ordinal() - 1];
    }

    private boolean horizontal() {
        return this == RIGHT || this == LEFT;
    }

    private boolean vertical() {
        return this == UP || this == DOWN;
    }

    private boolean diagonal() {
        return !horizontal() && !vertical();
    }

    /**
     * Adds another direction to the current.
     *
     * @param other direction to add.
     * @return combined direction.
     */
    public Direction addDirection(Direction other) {
        if (this == other) {
            return this;
        } else if (this.opposite() == other) {
            return null;
        } else if (other.diagonal()) {
            throw new IllegalArgumentException("Direction to add has to be vertical or horizontal");
        }

        if (diagonal()) {
            if (name().contains(other.name())) {
                return this;
            } else {
                return Direction.valueOf(this.name().replace(other.opposite().name(), "").replace("_", ""));
            }
        } else {
            return Direction.valueOf(vertical() ? name() + "_" + other.name() : other.name() + "_" + name());
        }
    }
}
