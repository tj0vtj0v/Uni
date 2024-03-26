package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Bullet;

import java.awt.*;

/**
 * Representation of the in-game-object 'Stone'.
 * <p>
 * unmoving
 * indestructible
 * blocks {@link Bullet}
 * png textured
 */
public class Stone {
    private final GameView gameView;
    private final Position position;
    private double rotation;
    private final double size;
    private final double width;
    private final double height;


    /**
     * Creates Stone with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */

    public Stone(GameView gameView) {
        this.gameView = gameView;

        size = 30;
        rotation = 0;
        width = 150;
        height = 33;
        position = new Position(GameView.WIDTH - width, 0);
    }


    /**
     * Adds the Object to the gameView window.
     */
    public void addToCanvas() {
        gameView.addTextToCanvas("Objekt 3", position.getX() + 3, position.getY() - 5, size, true, Color.YELLOW, rotation);
    }


    /**
     * Creates a String with Name and Position.
     *
     * @return created String.
     */
    @Override
    public String toString() {
        return "Tree: %s".formatted(position);
    }
}
