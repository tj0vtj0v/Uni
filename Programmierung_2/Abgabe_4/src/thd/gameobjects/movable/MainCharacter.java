package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.CharacterBlockImages;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;


/**
 * Representation of the in-game-object 'MainCharacter'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class MainCharacter extends GameObject {
    private boolean shotInProgress;

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public MainCharacter(GameView gameView) {
        super(gameView);

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 2;

        position.updateCoordinates(new Position(GameView.WIDTH / 2d - width / 2d, GameView.HEIGHT / 3d * 2));

        shotInProgress = false;
    }

    /**
     * Moves the character its speed in pixels to the left.
     */
    public void left() {
        position.left(speedInPixel);
    }

    /**
     * Moves the character its speed in pixels to the right.
     */
    public void right() {
        position.right(speedInPixel);
    }

    /**
     * Moves the character its speed in pixels up.
     */
    public void up() {
        position.up(speedInPixel);
    }

    /**
     * Moves the character its speed in pixels down.
     */
    public void down() {
        position.down(speedInPixel);
    }

    /**
     * Detects a 'Shoot' action of the character.
     */
    public void shoot() {
        shotInProgress = true;
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(5000, this)) {
            size++;
        }
    }

    @Override
    public void addToCanvas() {
        if (shotInProgress) {
            gameView.addTextToCanvas("X", position.getX(), position.getY(), size * 25, true, Color.BLACK, rotation);
        } else {
            gameView.addBlockImageToCanvas(CharacterBlockImages.Main.DOWN_1, position.getX(), position.getY(), size, rotation);
        }
        shotInProgress = false;
    }

    @Override
    public String toString() {
        return "EnemyGunner: %s".formatted(position);
    }
}
