package thd.screens;

import thd.game.utilities.GameView;

/**
 * Represents the Screen showing at the end.
 */
public class EndScreen {
    private final GameView gameView;

    /**
     * Represents the Screen showing at gameStart.
     *
     * @param gameView window to display on.
     */
    public EndScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Prints the Screen onto the gameView.
     *
     * @param score the player achieved.
     */
    public void showEndScreen(int score) {
        gameView.showEndScreen(String.valueOf(score));
    }
}
