package thd.gameobjects.unmovable;


import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ScoreBoardBlockImages;

import java.awt.*;

/**
 * Representation of the Scoreboard.
 * <p>
 * overlay fixed on screen
 * not part of the game.
 */
public class ScoreBoard extends GameObject {
    private double localYZero;

    /**
     * Creates Scoreboard with gameView window of presence.
     *
     * @param gameView window to insert to.
     */
    public ScoreBoard(GameView gameView) {
        super(gameView);

        height = 80;
        size = 40;

        localYZero = GameView.HEIGHT - height;
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(0, GameView.HEIGHT - height, GameView.WIDTH, height, 0, true, Color.BLACK);
        gameView.addTextToCanvas("SCORE %06d".formatted(0), 0, localYZero + (height - size) / 2, size, true, new Color(81, 66, 245), rotation);
        gameView.addTextToCanvas("MEN %d".formatted(3), GameView.WIDTH / 2.5, localYZero + (height - size) / 2, size, true, new Color(148, 209, 90), rotation);
        gameView.addBlockImageToCanvas(ScoreBoardBlockImages.Grenade, GameView.WIDTH/2, localYZero + (height - size) / 2, 4, rotation);
        // TODO all
    }

}
