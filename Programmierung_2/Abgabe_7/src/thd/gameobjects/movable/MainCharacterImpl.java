package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.managers.NoRemainingMenException;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

import java.util.List;


/**
 * Representation of the in-game-object 'MainCharacter'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class MainCharacterImpl extends MovingCharacter implements MainCharacter {

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView                            window in which it has to be displayed.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param collidingGameObjectsForPathDecision List of Objects that block the movement.
     */
    public MainCharacterImpl(GameView gameView, GamePlayManager gamePlayManager, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, collidingGameObjectsForPathDecision);

        blockImage = CharacterBlockImages.Main.DOWN_1;
        distanceToBackground = 200;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(6, 6, -12, -24);

        speedInPixel = 4;

        position.updateCoordinates(new Position(GameView.WIDTH / 2d - width / 2d, GameView.HEIGHT / 3d * 2));
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet && ((Bullet) other).creator != this) {
            try {
                gamePlayManager.reduceLive();
            } catch (NoRemainingMenException e) {
                gamePlayManager.gameOver(false);
            }
        }
    }

    /**
     * Moves the character its speed in pixels to the left.
     */
    public void left() {
        position.left(speedInPixel);
        if (pathIsBlocked()) {
            position.right(speedInPixel);
        }
    }

    /**
     * Moves the character its speed in pixels to the right.
     */
    public void right() {
        position.right(speedInPixel);
        if (pathIsBlocked()) {
            position.left(speedInPixel);
        }
    }

    /**
     * Moves the character its speed in pixels up.
     */
    public void up() {
        position.up(speedInPixel);
        if (pathIsBlocked()) {
            position.down(speedInPixel);
        }
    }

    /**
     * Moves the character its speed in pixels down.
     */
    public void down() {
        position.down(speedInPixel);
        if (pathIsBlocked()) {
            position.up(speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
    }

    @Override
    public String toString() {
        return "MainCharacter: %s".formatted(position);
    }
}
