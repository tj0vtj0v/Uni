package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;


/**
 * Representation of the in-game-object 'Humvee'.
 * <p>
 * passive linear moving
 * destructible by 1 {@link Grenade} or 5 {@link Bullet}
 * png textured
 */
public class Humvee {
    private final GameView gameView;
    private final Position position;
    private final double speedInPixel;
    private double rotation;
    private final double size;
    private final double width;
    private final double height;


    /**
     * Creates Humvee with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public Humvee(GameView gameView) {
        this.gameView = gameView;

        size = 3;
        position = new Position(0, GameView.HEIGHT / 2f);
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 5;
    }


    /**
     * Adds the Object to the gameView window.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("humvee.png", position.getX(), position.getY(), size, rotation);
    }


    /**
     * Updates the Position of the Object.
     */
    public void updatePosition() {
        position.right(speedInPixel);
    }


    /**
     * Creates a String with Name and Position.
     *
     * @return created String.
     */
    @Override
    public String toString() {
        return "Humvee: %s".formatted(position);
    }
}
