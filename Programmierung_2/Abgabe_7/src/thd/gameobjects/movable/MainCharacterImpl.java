package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.managers.NoRemainingMenException;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.unmovable.AmmoBox;
import thd.gameobjects.unmovable.Explosion;

import java.util.List;
import java.util.Random;


/**
 * Representation of the in-game-object 'MainCharacter'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class MainCharacterImpl extends MovingCharacter implements MainCharacter {
    private int availableGrenades;
    private boolean invincible;

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView                            window in which it has to be displayed.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param direction                           Stores positional information.
     * @param position                            Position from which to start movement.
     * @param collidingGameObjectsForPathDecision List of Objects that block the movement.
     */
    public MainCharacterImpl(GameView gameView, GamePlayManager gamePlayManager, Direction direction, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, direction, position, collidingGameObjectsForPathDecision);
        availableGrenades = 5;
        invincible = true;

        blockImage = CharacterBlockImages.Main.DOWN_1;
        distanceToBackground = 200;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(6, 6, -12, -24);

        speedInPixel = 5;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet && ((Bullet) other).creator != this || other instanceof Explosion) {
            try {
                if (!invincible) {
                    gamePlayManager.reduceLive();
                    invincible = true;
                }
            } catch (NoRemainingMenException e) {
                gamePlayManager.gameOver(false);
            }

        }

        if (other instanceof AmmoBox) {
            availableGrenades += new Random().nextInt(3, 6);
        }
    }

    /**
     * Communicates the number of remaining Grenades.
     *
     * @return amount of Grenades.
     */
    public int getAvailableGrenades() {
        return availableGrenades;
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
        if (position.getY() > GameView.HEIGHT / 2f) {
            position.up(speedInPixel);
            if (pathIsBlocked()) {
                position.down(speedInPixel);
            }
        } else if (position.getY() > GameView.HEIGHT / 3f) {
            position.up(speedInPixel / 2);
            gamePlayManager.moveWorldDown(speedInPixel / 2);

            if (pathIsBlocked()) {
                position.down(speedInPixel / 2);
                gamePlayManager.moveWorldUp(speedInPixel / 2);
            }
        } else {
            gamePlayManager.moveWorldDown(speedInPixel);
            if (pathIsBlocked()) {
                gamePlayManager.moveWorldUp(speedInPixel);
            }
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
    public void updateStatus() {
        if (invincible && gameView.timer(2000, this)) {
            invincible = false;
        }
    }

    @Override
    public String toString() {
        return "MainCharacter: %s is %b invincible with %d available Grenades".formatted(position, invincible, availableGrenades);
    }
}
