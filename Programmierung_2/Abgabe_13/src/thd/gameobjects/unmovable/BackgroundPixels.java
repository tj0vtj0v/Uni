package thd.gameobjects.unmovable;

import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

import java.util.Random;

/**
 * Representation of the random Pixels in the background.
 */
public class BackgroundPixels extends GameObject implements ShiftableGameObject {
    private final Level level;

    /**
     * Creates and generates the random Pixel.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public BackgroundPixels(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        level = gamePlayManager.currentLevel();

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;

        gamePlayManager.currentLevel();
        blockImage = createBackgroundPattern(((level.worldOffsetLines * 2) + Level.VISIBLE_COLUMNS));
        distanceToBackground = BACKGROUND;

        width = generateWidthFromBlockImage();
        height = generateHeightFromBlockImage();

        position.up((level.worldOffsetLines * 2) * level.worldScale);
    }

    private String createBackgroundPattern(int length) {
        int blockImageWidth = level.world.split("\n")[0].length() - level.worldOffsetColumns;
        Random random = new Random(hashCode());

        StringBuilder output = new StringBuilder();

        for (int row = 0; row < (length * level.worldScale / size); row++) {
            for (int col = 0; col < (blockImageWidth * level.worldScale / size); col++) {
                output.append(random.nextInt((int) (1 / BACKGROUND_DARK_PIXEL_PROBABILITY)) == 0 ? "0" : "-");
            }
            output.append("\n");
        }

        return output.toString();
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
    }
}
