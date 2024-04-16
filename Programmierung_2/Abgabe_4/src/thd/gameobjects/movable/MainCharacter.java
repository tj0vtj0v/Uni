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
 * png textured
 */
public class MainCharacter extends GameObject {
    private boolean shootInProgress;

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

        shootInProgress = false;
    }

    public void left() {
        position.left(speedInPixel);
    }

    public void right() {
        position.right(speedInPixel);
    }

    public void up() {
        position.up(speedInPixel);
    }

    public void down() {
        position.down(speedInPixel);
    }

    public void shoot() {
        shootInProgress = true;
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(5000, this)) {
            size++;
        }
    }

    @Override
    public void addToCanvas() {
        if (shootInProgress) {
            gameView.addTextToCanvas("X", position.getX(), position.getY(), size * 25, true, Color.BLACK, rotation);
        } else {
            gameView.addBlockImageToCanvas(CharacterBlockImages.Main.DOWN_1, position.getX(), position.getY(), size, rotation);
        }
        shootInProgress = false;
    }

    @Override
    public String toString() {
        return "EnemyGunner: %s".formatted(position);
    }
}
