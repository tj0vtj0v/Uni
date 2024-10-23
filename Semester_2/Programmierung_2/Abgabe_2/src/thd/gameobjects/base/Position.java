package thd.gameobjects.base;

import thd.game.utilities.GameView;

/**
 *  Datatype for Position-handling in the {@link GameView}.
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

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
    }
}
