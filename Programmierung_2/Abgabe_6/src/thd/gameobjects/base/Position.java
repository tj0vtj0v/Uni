package thd.gameobjects.base;

import thd.game.utilities.GameView;

import java.util.Objects;

/**
 * Datatype for Position-handling in the {@link GameView}.
 *
 * <pre>
 * The GameView Window shows pixel with Positions in this allocation.
 * (0,0) -------------- (1280,0)
 *  |                         |
 *  |                         |
 * (0,720) ---------- (1280,720)
 * </pre>
 * negative Positions are allowed but not visible in the GameView.
 *
 * @see GameView
 */
public class Position {

    private double x;
    private double y;

    /**
     * Creates a position on (0, 0).
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Creates a position with the coordinates of the given position.
     *
     * @param otherPosition Another position.
     */
    public Position(Position otherPosition) {
        this(otherPosition.x, otherPosition.y);
    }

    /**
     * Creates a position on (x, y).
     *
     * @param x X-coordinate on the window.
     * @param y Y-coordinate on the window.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x coordinate.
     *
     * @return x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y coordinate.
     *
     * @return y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Updates this position to the coordinates of the given position.
     *
     * @param otherPosition Another position.
     */
    public void updateCoordinates(Position otherPosition) {
        x = otherPosition.x;
        y = otherPosition.y;
    }

    /**
     * Updates this position to the coordinates of the new position.
     *
     * @param x X-coordinate on the window.
     * @param y Y-coordinate on the window.
     */
    public void updateCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * One pixel to the right.
     */
    public void right() {
        x++;
    }

    /**
     * To the right by the given number of pixels.
     *
     * @param pixel Number of pixels.
     */
    public void right(double pixel) {
        x += pixel;
    }

    /**
     * One pixel to the left.
     */
    public void left() {
        x--;
    }

    /**
     * To the left by the given number of pixels.
     *
     * @param pixel Number of pixels.
     */
    public void left(double pixel) {
        x -= pixel;
    }

    /**
     * One pixel upwards.
     */
    public void up() {
        y--;
    }

    /**
     * Upwards by the given number of pixels.
     *
     * @param pixel Number of pixels.
     */
    public void up(double pixel) {
        y -= pixel;
    }

    /**
     * One pixel downwards.
     */
    public void down() {
        y++;
    }

    /**
     * Downwards by the given number of pixels.
     *
     * @param pixel Number of pixels.
     */
    public void down(double pixel) {
        y += pixel;
    }

    /**
     * Moves towards the given position with the given speed.
     *
     * @param otherPosition Another position.
     * @param speedInPixel  Speed of movement in a single frame.
     */
    public void moveToPosition(Position otherPosition, double speedInPixel) {
        double distance = distance(otherPosition);
        if (distance <= speedInPixel) {
            updateCoordinates(otherPosition);
        } else {
            right((otherPosition.x - x) / distance * speedInPixel);
            down((otherPosition.y - y) / distance * speedInPixel);
        }
    }


    /**
     * Calculation of distance to any other given Position.
     *
     * @param otherPosition Position to calculate distance from.
     * @return distance to the otherPosition.
     */
    public double distance(Position otherPosition) {
        return Math.sqrt(Math.pow(x - otherPosition.getX(), 2) + Math.pow(y - otherPosition.getY(), 2));
    }

    /**
     * Checks if this position is similar to the other position.
     *
     * @param otherPosition Another position.
     * @return True if this position has the same x- and y-coordinates as the other position,
     * when both are rounded to {@code int}.
     */
    public boolean similarTo(Position otherPosition) {
        return Math.round(x) == Math.round(otherPosition.x)
                && Math.round(y) == Math.round(otherPosition.y);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Double.compare(x, position.x) == 0
                && Double.compare(y, position.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
    }
}
