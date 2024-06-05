package thd.screens;

import thd.game.utilities.GameView;

/**
 * Representing the EndScreen in case of success.
 */
public class WonScreen extends EndScreen {
    /**
     * Represents the Screen showing at gameStart.
     *
     * @param gameView window to display on.
     * @param score    the player scored.
     */
    public WonScreen(GameView gameView, int score) {
        super(gameView, score);
    }

    @Override
    public void showEndScreen() {
        String message = """
                MISSION ACCOMPLISHED!
                                
                Congratulations, Commando!
                                
                You've successfully infiltrated enemy lines,
                rescued the hostages, and neutralized all threats.
                                
                Your Score: --> %d <--         is a new high score candidate!
                
                You've proven that you have what it takes to be the best.
                                
                WHO DARES WINNS, AND YOU HAVE TRIUMPHED!
                                
                Thank you for playing, soldier.
                Until the next mission, rest well – you've earned it.
                """;

        gameView.showEndScreen(message.formatted(score));
    }
}
