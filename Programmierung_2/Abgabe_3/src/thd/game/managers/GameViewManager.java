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
        changeBackgroundColor(Color.BLACK);

        addBlockimageColors();
    }


    public void addBlockimageColors() {

        setColorForBlockImage();
        colorMap.put('s', new Color(250, 225, 110)); // Skin color of People
        colorMap.put('u', new Color(119, 89, 35)); // Color of Enemy Uniforms
        colorMap.put('e', new Color(255, 243, 26)); // Color of Enemy Equipment
        colorMap.put('v', new Color(158, 102, 2)); // Fill color of Vehicles
        colorMap.put('a', new Color(13, 140, 233)); // Color of Allies

        colorMap.put('b', new Color(0, 0, 0)); // Black
        colorMap.put('g', new Color(64, 64, 64)); // Grey
        colorMap.put('w', new Color(255, 255, 255)); // White
    }


    /**
     * Triggers the UpdateLoop from {@link GameManager}.
     */
    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
    }
}
