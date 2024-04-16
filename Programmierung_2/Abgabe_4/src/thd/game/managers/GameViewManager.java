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

        addBlockimageColors();
    }


    public void addBlockimageColors() {
        setColorForBlockImage('1', new Color(250, 225, 110)); // Skin color of People
        setColorForBlockImage('2', new Color(119, 89, 35)); // Color of Enemy Uniforms
        setColorForBlockImage('3', new Color(255, 243, 26)); // Color of Enemy Equipment
        setColorForBlockImage('4', new Color(158, 102, 2)); // Fill color of Vehicles
        setColorForBlockImage('5', new Color(11, 105, 220)); // Color of Allies

        setColorForBlockImage('6', new Color(0, 0, 0)); // Black
        setColorForBlockImage('7', new Color(64, 64, 64)); // Grey
        setColorForBlockImage('8', new Color(255, 255, 255)); // White
    }


    /**
     * Triggers the UpdateLoop from {@link GameManager}.
     */
    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
    }
}
