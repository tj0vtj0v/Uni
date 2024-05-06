package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.Bullet;

import java.util.List;


/**
 * Creates an active moving character.
 */
public class MovingCharacter extends CollidingGameObject {
    private final List<CollidingGameObject> collidingGameObjectsForPathDecision;
    private final int shotDurationInMilliseconds;

    /**
     * Creates an instance of a moving Character as the main character or an enemy.
     *
     * @param gameView                            gameView where the Object is displayed in.
     * @param gamePlayManager                     manager for the in-game logic.
     * @param facing                              Stores information about movement direction.
     * @param position                            Position from which to start movement.
     * @param collidingGameObjectsForPathDecision list of object which are path blockers.
     */
    public MovingCharacter(GameView gameView, GamePlayManager gamePlayManager, Direction facing, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, facing, position);
        this.collidingGameObjectsForPathDecision = collidingGameObjectsForPathDecision;
        shotDurationInMilliseconds = 300;

        width = 0;
        height = 0;
        hitBoxOffsets(0, 0, 0, 0);

        shoot();
    }

    /**
     * Creates a Bullet-Object flying down.
     */
    public void shoot() {
        direction = direction == null ? Direction.DOWN : direction;

        if (gameView.timer(shotDurationInMilliseconds, this)) {
            gamePlayManager.spawnGameObject(new Bullet(gameView, gamePlayManager, direction, new Position(position.getX() + 7, position.getY() + 36), this));
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
}
