package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

import java.awt.*;


/**
 * Representation of the in-game-object 'Moped'.
 * <p>
 * passive linear moving
 * destructible by 1 {@link Grenade} or 3 {@link Bullet}
 * png textured
 */
public class Moped {
    private final GameView gameView;
    private final Position position;
    private final double speedInPixel;
    private double rotation;
    private final double size;
    private final double width;
    private final double height;


    /**
     * Creates Moped with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public Moped(GameView gameView) {
        this.gameView = gameView;

        size = 30;
        position = new Position(1100, 650);
        rotation = 0;
        width = 150;
        height = 33;

        speedInPixel = 2;
    }


    /**
     * Adds the Object to the gameView window.
     */
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX(), position.getY(), 150, 35, 0, true, Color.GREEN);
        gameView.addRectangleToCanvas(position.getX(), position.getY(), 150, 35, 5, false, Color.WHITE);
        gameView.addTextToCanvas("Objekt 2", position.getX() + 3, position.getY() - 3, size, true, Color.BLUE, rotation);
    }


    /**
     * Updates the Position of the Object.
     */
    public void updatePosition() {
        position.left(speedInPixel);
    }


    /**
     * Creates a String with Name and Position.
     *
     * @return created String.
     */
    @Override
    public String toString() {
        return "Moped: %s".formatted(position);
    }
}
