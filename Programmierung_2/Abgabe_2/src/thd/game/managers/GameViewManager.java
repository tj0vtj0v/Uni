package thd.game.managers;

import thd.game.utilities.GameView;

import java.awt.*;

public class GameViewManager extends GameView {
    GameManager gameManager;

    @Override
    public void initialize() {
        gameManager = new GameManager(this);
        setWindowTitle("Who Dares Wins");
        setStatusText("Tjorven Burdorf - Java Programmierung SS 2024");
        setWindowIcon("main_character/main_down_1.png");
        changeBackgroundColor(Color.BLACK);
    }

    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
    }
}
