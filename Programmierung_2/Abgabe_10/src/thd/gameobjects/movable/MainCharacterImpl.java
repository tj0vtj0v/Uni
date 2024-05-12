package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.managers.NoRemainingMenException;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.MainCharacterBlockImages;
import thd.gameobjects.unmovable.AmmoBox;

import java.util.List;
import java.util.Random;


/**
 * Representation of the in-game-object 'MainCharacterBlockImages'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class MainCharacterImpl extends MovingCharacter implements MainCharacter {
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
        shotCooldownInMilliseconds = gamePlayManager.currentLevel().mainCharacterShotCooldown;
        availableGrenades = 5;
        dead = false;

        blockImage = MainCharacterBlockImages.DOWN_1;
        distanceToBackground = LAYER_2;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(6, 6, -12, -24);

        speedInPixel = gamePlayManager.currentLevel().mainCharacterSpeedInPixel;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (fatallyHit(other)) {
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
            availableGrenades += new Random(hashCode()).nextInt(3, 6);
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

        if (direction == null) {
            direction = Direction.LEFT;
        } else {
            direction = direction.addDirection(Direction.LEFT);
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

        if (direction == null) {
            direction = Direction.RIGHT;
        } else {
            direction = direction.addDirection(Direction.RIGHT);
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
        } else {
            gamePlayManager.moveWorldDown(speedInPixel);
            if (pathIsBlocked()) {
                gamePlayManager.moveWorldUp(speedInPixel);
            }
        }

        if (direction == null) {
            direction = Direction.UP;
        } else {
            direction = direction.addDirection(Direction.UP);
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

        if (direction == null) {
            direction = Direction.DOWN;
        } else {
            direction = direction.addDirection(Direction.DOWN);
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

    /**
     * Re setter for the direction if a new directional keyInput comes.
     */
    public void resetDirection() {
        this.direction = null;
    }

    @Override
    public void shoot() {
        if (!dead) {
            super.shoot();
        }
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (dead) {
            blockImage = MainCharacterBlockImages.DEAD;

            width = 0;
            height = 0;
            hitBoxOffsets(0, 0, 0, 0);
        }
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