package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.managers.NoRemainingMenException;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.MainCharacterBlockImages;
import thd.gameobjects.unmovable.AmmoBox;
import thd.gameobjects.unmovable.Explosion;

import java.util.List;
import java.util.Random;


/**
 * Representation of the in-game-object 'MainCharacterBlockImages'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class MainCharacterImpl extends MovingCharacter implements thd.gameobjects.base.MainCharacter {
    private int availableGrenades;
    private boolean dead;

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView                            window in which it has to be displayed.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param direction                           Stores positional information.
     * @param position                            Position from which to start movement.
     * @param collidingGameObjectsForPathDecision List of ObjectBlockImages that block the movement.
     */
    public MainCharacterImpl(GameView gameView, GamePlayManager gamePlayManager, Direction direction, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, direction, position, collidingGameObjectsForPathDecision);
        availableGrenades = 5;
        dead = false;

        instanceBlockImage = MainCharacterBlockImages.DOWN_1;
        distanceToBackground = 100;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(6, 6, -12, -24);

        speedInPixel = gamePlayManager.currentLevel().mainCharacterMovementSpeed;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet && ((Bullet) other).creator != this || other instanceof Explosion) {
            try {
                if (!dead) {
                    gamePlayManager.reduceLive();
                    dead = true;
                }
            } catch (NoRemainingMenException e) {
                gamePlayManager.gameOver(false);
            }

        }

        if (other instanceof AmmoBox) {
            availableGrenades += new Random(System.currentTimeMillis()).nextInt(3, 6);
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

    /**
     * Getter to communicate Status of the MainCharacterBlockImages.
     *
     * @return true if dead.
     */
    public boolean isDead() {
        return dead;
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (dead) {
            instanceBlockImage = MainCharacterBlockImages.DEAD;

            width = 0;
            height = 0;
            hitBoxOffsets(0, 0, 0, 0);
        }

        direction = null;
    }

    @Override
    protected boolean pathIsBlocked() {
        boolean outOfGameView = position.getX() + 1 < 0 || position.getX() - 1 > GameView.WIDTH - width || position.getY() - 1 > GameView.HEIGHT - height - 80;
        return outOfGameView || super.pathIsBlocked() || dead;
    }

    @Override
    public String toString() {
        return "MainCharacterBlockImages: %s is %b dead with %d available Grenades".formatted(position, dead, availableGrenades);
    }
}