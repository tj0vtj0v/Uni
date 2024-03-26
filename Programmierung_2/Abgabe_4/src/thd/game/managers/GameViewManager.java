package thd.game.managers;

import thd.game.utilities.GameView;

import java.awt.*;


/**
 * Manager for the GameView customization.
 */
public class GameViewManager extends GameView {
    private GameManager gameManager;


    /**
     * Adjustment of the GameView Window according to my Data.
     */
    @Override
    public void initialize() {
        gameManager = new GameManager(this);
        setWindowTitle("Who Dares Wins");
        setStatusText("Tjorven Burdorf - Java Programmierung SS 2024");
        setWindowIcon("main_character/main_down_1.png");
        changeBackgroundColor(Color.GRAY);
    }


    /**
     * Triggers the UpdateLoop from {@link GameManager}.
     */
    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
    }
}
