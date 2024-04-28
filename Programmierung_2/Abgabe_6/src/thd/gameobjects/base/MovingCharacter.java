package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.Bullet;

import java.util.List;


public class MovingCharacter extends CollidingGameObject {
    protected final List<CollidingGameObject> collidingGameObjectsForPathDecision;
    private final int shotDurationInMilliseconds = 300;

    public MovingCharacter(GameView gameView, GamePlayManager gamePlayManager, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager);

        this.collidingGameObjectsForPathDecision = collidingGameObjectsForPathDecision;
    }

    public void shoot() {
        if (gameView.timer(shotDurationInMilliseconds, this)) {
            gamePlayManager.spawnGameObject(new Bullet(gameView, gamePlayManager, new Position(position.getX() + 7, position.getY() + 36), Direction.DOWN, this));
        }
    }

    @Override
    protected boolean pathIsBlocked() {
        for (CollidingGameObject collidingGameObject : collidingGameObjectsForPathDecision) {
            if (collidesWith(collidingGameObject)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public void addToCanvas() {
    }
}
