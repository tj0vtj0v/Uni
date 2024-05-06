package thd.gameobjects.unmovable;

import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

import java.util.Random;

public class BackgroundPixels extends GameObject implements ShiftableGameObject {
    private final Level level;

    public BackgroundPixels(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        level = gamePlayManager.currentLevel();

        size = 3;
        rotation = 0;

        gamePlayManager.currentLevel();
        blockImage = createBackgroundPattern((level.worldOffsetLines + Level.VISIBLE_COLUMNS));
        distanceToBackground = 0;

        width = generateWidthFromBlockImage();
        height = generateHeightFromBlockImage();

        position.up(level.worldOffsetLines * level.worldScale);
    }

    private String createBackgroundPattern(int length) {
        int blockImageWidth = level.world.split("\n")[0].length() - level.worldOffsetColumns;
        Random random = new Random(System.currentTimeMillis());

        StringBuilder output = new StringBuilder();

        for (int row = 0; row < (length * level.worldScale / size); row++) {
            for (int col = 0; col < (blockImageWidth * level.worldScale / size); col++) {
                output.append(random.nextInt(10) == 0 ? "0" : "-");
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
