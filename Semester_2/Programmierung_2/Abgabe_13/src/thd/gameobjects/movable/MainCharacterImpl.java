package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.MovingCharacterBlockImages;
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
        dead = false;

        changeBlockImageColors();
        distanceToBackground = LAYER_2;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;

        speedInPixel = gamePlayManager.currentLevel().mainCharacterSpeedInPixel;

        shoot();
    }

    private void changeBlockImageColors() {
        for (int directions = 0; directions < Direction.values().length; directions++) {
            for (int frames = 0; frames < Animation.values().length; frames++) {
                animationFrames[directions][frames] = animationFrames[directions][frames].replace("x", "5");
            }
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (fatallyHit(other)) {
            if (!dead && !gamePlayManager.overlay.isMessageShown()) {
                gamePlayManager.reduceLive();
                gameView.playSound("maindeath.wav", false);
                dead = true;
            }

        }

        if (other instanceof AmmoBox) {
            gamePlayManager.addGrenades(new Random(hashCode()).nextInt(3, 6));
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

        if (direction == null) {
            direction = Direction.LEFT;
        } else {
            direction = direction.addDirection(Direction.LEFT);
        }

        moved = true;
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

        moved = true;
    }

    /**
     * Moves the character its speed in pixels up.
     */
    public void up() {
        if (position.getY() > (GameView.HEIGHT - SCOREBOARD_HEIGHT) / 2f || gamePlayManager.endReached) {
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

        moved = true;
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

        moved = true;
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
        direction = null;
    }

    @Override
    public void throwGrenade() {
        if (!dead && gamePlayManager.getAvailableGrenades() > 0) {
            gamePlayManager.reduceGrenades();
            super.throwGrenade();
        }
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
            blockImage = MovingCharacterBlockImages.DEAD_MAIN_CHARACTER;

            hitBoxOffsets(0, 0, -width, -height);
        } else {
            updateAnimation();
        }

        moved = false;
    }

    @Override
    protected boolean pathIsBlocked() {
        boolean outOfGameView = position.getX() + 1 < 0 || position.getX() - 1 > GameView.WIDTH - width || position.getY() - 1 > GameView.HEIGHT - height - SCOREBOARD_HEIGHT;
        return outOfGameView || super.pathIsBlocked() || dead;
    }

    @Override
    public String toString() {
        return "MainCharacterBlockImages: %s is %b dead".formatted(position, dead);
    }
}