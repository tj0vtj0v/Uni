package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

public class Square extends GameObject {
    public Square(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        size = 1;
        rotation = 0;
        width = 30;
        height = 30;

        speedInPixel = 5;

        position.updateCoordinates(100, 100);
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX(), position.getY(), 30, 30, 3, false, Color.red);
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
    }
}
