package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.Position;

import java.util.List;

/**
 * Represents an Enemy coming out of the End of game Door.
 */
public class EnemyDoorGunner extends EnemyGunner {
    /**
     * Creates Enemy Door Gunner with gameView window of presence.
     *
     * @param gameView                            window in which it has to be displayed.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param location                            Stores positional information.
     * @param position                            Position from which to start movement.
     * @param collidingGameObjectsForPathDecision List of ObjectBlockImages that block the movement.
     */
    public EnemyDoorGunner(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, location, position, collidingGameObjectsForPathDecision);
        movementPattern = new DoorRandomMovementPattern(location);
        this.position.updateCoordinates(movementPattern.startPosition(position));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position));
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
    }
}
