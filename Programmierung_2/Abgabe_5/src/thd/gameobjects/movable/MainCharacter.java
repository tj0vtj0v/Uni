package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CharacterBlockImages;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;


/**
 * Representation of the in-game-object 'MainCharacter'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class MainCharacter extends GameObject {
    private Direction direction;

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public MainCharacter(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        direction = null;

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 2;

        position.updateCoordinates(new Position(GameView.WIDTH / 2d - width / 2d, GameView.HEIGHT / 3d * 2));
    }

    /**
     * Moves the character its speed in pixels to the left.
     */
    public void left() {
        position.left(speedInPixel);

        if (direction == null) {
            direction = Direction.left;
        } else if (direction.equals(Direction.up) || direction.equals(Direction.upRight)) {
            direction = Direction.upLeft;
        } else if (direction.equals(Direction.down) || direction.equals(Direction.downRight)) {
            direction = Direction.downLeft;
        }
    }

    /**
     * Moves the character its speed in pixels to the right.
     */
    public void right() {
        position.right(speedInPixel);

        if (direction == null) {
            direction = Direction.right;
        } else if (direction.equals(Direction.up) || direction.equals(Direction.upLeft)) {
            direction = Direction.upRight;
        } else if (direction.equals(Direction.down) || direction.equals(Direction.downLeft)) {
            direction = Direction.downRight;
        }
    }

    /**
     * Moves the character its speed in pixels up.
     */
    public void up() {
        position.up(speedInPixel);

        if (direction == null) {
            direction = Direction.up;
        } else if (direction.equals(Direction.left) || direction.equals(Direction.downLeft)) {
            direction = Direction.upLeft;
        } else if (direction.equals(Direction.right) || direction.equals(Direction.downRight)) {
            direction = Direction.upRight;
        }
    }

    /**
     * Moves the character its speed in pixels down.
     */
    public void down() {
        position.down(speedInPixel);

        if (direction == null) {
            direction = Direction.down;
        } else if (direction.equals(Direction.left) || direction.equals(Direction.upLeft)) {
            direction = Direction.downLeft;
        } else if (direction.equals(Direction.right) || direction.equals(Direction.upRight)) {
            direction = Direction.downRight;
        }
    }

    /**
     * Detects a 'Shoot' action of the character.
     */
    public void shoot() {
        direction = direction == null ? Direction.down : direction;

        gamePlayManager.spawnGameObject(new Bullet(gameView, gamePlayManager, getPosition(), direction));
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(CharacterBlockImages.Main.DOWN_1, position.getX(), position.getY(), size, rotation);
        direction = null;
    }

    @Override
    public String toString() {
        return "MainCharacter: %s".formatted(position);
    }
}
