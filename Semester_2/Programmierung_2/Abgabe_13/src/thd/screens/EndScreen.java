package thd.screens;

import thd.game.utilities.GameView;

/**
 * Represents the Screen showing at the end.
 */
public abstract class EndScreen {
    protected final GameView gameView;
    final int score;

    /**
     * Represents the Screen showing at gameStart.
     *
     * @param gameView window to display on.
     * @param score    the player scored.
     */
    EndScreen(GameView gameView, int score) {
        this.gameView = gameView;
        this.score = score;
    }

    /**
     * Prints the Screen onto the gameView.
     */
    public abstract void showEndScreen();
}
