package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;


/**
 * Representation of the in-game-object 'MainCharacter'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class MainCharacterUnluckyLuke extends GameObject implements MainCharacter{
    private final int shotDurationInMilliseconds;

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public MainCharacterUnluckyLuke(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        shotDurationInMilliseconds = 300;

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 2;

        position.updateCoordinates(new Position(GameView.WIDTH / 2d - width / 2d, GameView.HEIGHT / 3d * 2));
        shoot();
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

    @Override
    public void shoot() {
        if (gameView.timer(shotDurationInMilliseconds, this)) {
            gamePlayManager.spawnGameObject(new Bullet(gameView, gamePlayManager, new Position(position.getX() + 7, position.getY() + 36), Direction.down));
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(CharacterBlockImages.Main.DOWN_1, position.getX(), position.getY(), size, rotation);
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
