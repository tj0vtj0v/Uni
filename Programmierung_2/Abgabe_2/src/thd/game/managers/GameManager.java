package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.Humvee;
import thd.gameobjects.movable.Moped;
import thd.gameobjects.unmovable.Stone;

public class GameManager {
    final GameView gameView;
    private final Humvee humvee;
    private final Moped moped;
    private final Stone scoreBoard;

    public GameManager(GameView gameView) {
        this.gameView = gameView;

        humvee = new Humvee(gameView);
        moped = new Moped(gameView);
        scoreBoard = new Stone(gameView);
    }

    public void gameLoopUpdate() {
        humvee.updatePosition();
        humvee.addToCanvas();

        moped.updatePosition();
        moped.addToCanvas();

        scoreBoard.addToCanvas();
    }
}
