package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.resources.ObjectBlockImages;

import java.util.List;


/**
 * Represents the Wall at the end of the game.
 */
public class EndWall extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    static final double HIT_BOX_HEIGHT_OFFSET = -36 * BLOCK_IMAGE_SIZE;
    private State currentState;
    private final List<CollidingGameObject> collidingGameObjectsForPathDecision;

    /**
     * Creates the Wall at the end of the game.
     *
     * @param gameView                            window to be displayed on.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param located                             parameter for the parent.
     * @param position                            where the wall is placed.
     * @param collidingGameObjectsForPathDecision collision list for the spawner.
     */
    public EndWall(GameView gameView, GamePlayManager gamePlayManager, Direction located, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, located, position);
        this.collidingGameObjectsForPathDecision = collidingGameObjectsForPathDecision;

        currentState = State.CLOSED;
        blockImage = currentState.display;

        size = BLOCK_IMAGE_SIZE;
        distanceToBackground = LAYER_2;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(0, 0, 0, HIT_BOX_HEIGHT_OFFSET - 30);
    }

    @Override
    public void updateStatus() {
        if (position.getY() >= 0 && currentState == State.CLOSED) {
            gamePlayManager.endReached = true;
            gamePlayManager.overlay.showMessage("Kill all enemies to continue!", 2);

            gameView.playSound("door.wav", false);
            gamePlayManager.spawnGameObject(new Spawner(gameView, gamePlayManager, Direction.UP, new Position((double) GameView.WIDTH / 2, 40), collidingGameObjectsForPathDecision, false));

            currentState = State.OPENED;
            blockImage = currentState.display;
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= DEFAULT_SPAWN_DISTANCE;
    }

    private enum State {
        CLOSED(new ObjectBlockImages().endWall(false)),
        OPENED(new ObjectBlockImages().endWall(true));

        private final String display;

        State(String display) {
            this.display = display;
        }
    }

}
