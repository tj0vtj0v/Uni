package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;

import java.util.Objects;

/**
 * Represents an object in the game.
 */
public abstract class GameObject {
    protected final GameView gameView;
    protected final GamePlayManager gamePlayManager;
    protected final Position position;
    protected final Position targetPosition;
    protected String blockImage;
    protected char distanceToBackground;
    protected double speedInPixel;
    protected double rotation;
    protected double size;
    protected double width;
    protected double height;

    /**
     * Crates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    protected GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;

        position = new Position();
        targetPosition = new Position();
    }

    protected int generateWidthFromBlockImage() {
        int maximumLineLength = 0;
        for (String line : blockImage.split("\n")) {
            maximumLineLength = Math.max(maximumLineLength, line.length());
        }

        return maximumLineLength;
    }

    protected int generateHeightFromBlockImage() {
        return blockImage.split("\n").length;
    }

    /**
     * Updates the position of the game object.
     */
    public void updatePosition() {
    }

    /**
     * Draws the game object to the canvas.
     */
    public abstract void addToCanvas();

    /**
     * Changes the status of an Object.
     */
    public void updateStatus() {
    }

    /**
     * Returns the current position of the game object.
     *
     * @return position of the game object.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the current targetPosition of the object.
     *
     * @return targetPosition of the game object.
     */
    public Position getTargetPosition() {
        return targetPosition;
    }

    /**
     * Returns width of game object.
     *
     * @return Width of game object
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns height of game object.
     *
     * @return Height of game object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the distance to the background of the game object.
     *
     * @return distance to the background of game object
     */
    public char getDistanceToBackground() {
        return distanceToBackground;
    }

    protected String mirrorBlockImage(String originalBlockImage) {
        StringBuilder mirroredBlockimage = new StringBuilder();

        for (String originalLine : originalBlockImage.split("\n")) {
            mirroredBlockimage.append(new StringBuilder(originalLine).reverse().append("\n"));
        }

        return mirroredBlockimage.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        GameObject other = (GameObject) o;
        return position.equals(other.position)
                && targetPosition.equals(other.targetPosition)
                && blockImage.equals(other.blockImage)
                && Character.compare(distanceToBackground, other.distanceToBackground) == 0
                && Double.compare(speedInPixel, other.speedInPixel) == 0
                && Double.compare(rotation, other.rotation) == 0
                && Double.compare(size, other.size) == 0
                && Double.compare(width, other.width) == 0
                && Double.compare(height, other.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, targetPosition, blockImage, distanceToBackground, speedInPixel, rotation, size, width, height);
    }
}