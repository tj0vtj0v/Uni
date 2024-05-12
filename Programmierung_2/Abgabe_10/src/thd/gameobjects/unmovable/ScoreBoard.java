package thd.gameobjects.unmovable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.GameObjectConstants;

import java.awt.*;

/**
 * Representation of the Scoreboard.
 * <p>
 * overlay fixed on screen
 * not part of the game.
 */
public class ScoreBoard extends GameObject {
    private final double localYZero;

    /**
     * Creates Scoreboard with gameView window of presence.
     *
     * @param gameView        window to insert to.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public ScoreBoard(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        distanceToBackground = FOREGROUND;

        height = 80;
        size = 40;

        position.updateCoordinates(0, GameView.HEIGHT);
        localYZero = GameView.HEIGHT - height;
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(0, GameView.HEIGHT - height, GameView.WIDTH, height, 0, true, Color.BLACK);
        gameView.addTextToCanvas(
                "SCORE %06d".formatted(gamePlayManager.getPoints()),
                0,
                localYZero + (height - size) / 2,
                size,
                true,
                new Color(81, 66, 245),
                rotation);
        gameView.addTextToCanvas(
                "MEN %d".formatted(gamePlayManager.getLives()),
                GameView.WIDTH / 3f,
                localYZero + (height - size) / 2,
                size,
                true,
                new Color(148, 209, 90),
                rotation);
        gameView.addTextToCanvas(
                "GRENADES %d".formatted(gamePlayManager.availableGrenades()),
                GameView.WIDTH / 1.8,
                localYZero + (height - size) / 2,
                size,
                true,
                new Color(200, 200, 200),
                rotation);
        gameView.addTextToCanvas("HI %06d".formatted(gamePlayManager.getHighScore()),
                GameView.WIDTH / 1.3,
                localYZero + (height - size) / 2,
                size,
                true,
                new Color(255, 238, 96),
                rotation);
    }
}
