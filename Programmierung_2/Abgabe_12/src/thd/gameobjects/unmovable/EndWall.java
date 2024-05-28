package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.resources.ObjectBlockImages;


public class EndWall extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    static final double HIT_BOX_WIDTH_OFFSET = -36 * BLOCK_IMAGE_SIZE;
    private State currentState;

    public EndWall(GameView gameView, GamePlayManager gamePlayManager, Direction located, Position position) {
        super(gameView, gamePlayManager, located, position);

        currentState = State.CLOSED;
        blockImage = currentState.display;

        size = BLOCK_IMAGE_SIZE;
        distanceToBackground = LAYER_2;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(0, 0, 0, HIT_BOX_WIDTH_OFFSET);
    }

    @Override
    public void updateStatus() {
        if (position.getY() >= 0 && currentState == State.CLOSED) {
            gamePlayManager.endReached = true;

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
