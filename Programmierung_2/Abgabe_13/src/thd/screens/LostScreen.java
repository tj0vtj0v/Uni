package thd.screens;

import thd.game.utilities.GameView;

/**
 * Representing the EndScreen in case of failure.
 */
public class LostScreen extends EndScreen {
    /**
     * Represents the Screen showing at the end if you lost.
     *
     * @param gameView window to display on.
     * @param score    the player scored.
     */
    public LostScreen(GameView gameView, int score) {
        super(gameView, score);
    }

    @Override
    public void showEndScreen() {
        String message = """
                MISSION FAILED!

                Brave Commando,

                The mission has ended in catastrophic failure.
                The enemy forces have crushed your defenses,
                and the land you swore to protect is now under their control.

                Your Score: --> %d <--                     is discarded...

                WHO DARES WINNS – BUT TODAY, DEFEAT HAS STRUCK!

                Thank you for playing, soldier. Reflect on this defeat,
                harness your anger, and prepare for the next fight.
                """;

        gameView.showEndScreen(message.formatted(score));
    }
}
