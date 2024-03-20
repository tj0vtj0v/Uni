package game;

import java.awt.*;

public class GameViewManager extends GameView {
    Humvee humvee;
    Moped moped;
    ScoreBoard scoreBoard;

    @Override
    public void initialize() {
        humvee = new Humvee(this);
        moped = new Moped(this);
        scoreBoard = new ScoreBoard(this);
    }

    @Override
    public void gameLoop() {
        humvee.updatePosition();
        humvee.addToCanvas();
        moped.updatePosition();
        moped.addToCanvas();

        scoreBoard.addToCanvas();
    }
}
