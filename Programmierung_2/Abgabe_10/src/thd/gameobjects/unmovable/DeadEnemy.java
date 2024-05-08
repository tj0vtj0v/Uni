package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.DeadEnemyBlockImages;

/**
 * Represents a dead Enemy.
 */
public class DeadEnemy extends GameObject implements ShiftableGameObject {
    private static final int TARGET_ANIMATION_REPETITIONS = 3;
    private int animationRepetitions;
    private State currentState;


    /**
     * Creates a dead Enemy, mainly for animation and collision purposes.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param position        Position where to be located.
     */
    public DeadEnemy(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        currentState = State.DEAD_1;
        blockImage = currentState.display;
        animationRepetitions = 0;

        distanceToBackground = 100;

        size = GameObjectConstants.BLOCKIMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;

        speedInPixel = 0;

        this.position.updateCoordinates(position);
    }

    private void switchToNextState() {
        int nextState = (currentState.ordinal() + 1) % State.values().length;
        currentState = State.values()[nextState];
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (gameView.timer(50, this)) {
            switchToNextState();

            if (currentState == State.DEAD_14) {
                animationRepetitions += 1;

                if (animationRepetitions >= TARGET_ANIMATION_REPETITIONS) {
                    gamePlayManager.destroyGameObject(this);
                }
            }

            blockImage = currentState.display;

            if (currentState.ordinal() % 2 == 1) {
                blockImage = mirrorBlockImage(blockImage);
            }
        }
    }

    private enum State {
        DEAD_1(DeadEnemyBlockImages.DEAD_ENEMY),
        DEAD_2(" "),
        DEAD_3(DeadEnemyBlockImages.DEAD_ENEMY),
        DEAD_4(" "),
        DEAD_5(DeadEnemyBlockImages.DEAD_ENEMY),
        DEAD_6(" "),
        DEAD_7(DeadEnemyBlockImages.DEAD_ENEMY),
        DEAD_8(DeadEnemyBlockImages.DEAD_ENEMY),
        DEAD_9(" "),
        DEAD_010(DeadEnemyBlockImages.DEAD_ENEMY),
        DEAD_11(" "),
        DEAD_12(DeadEnemyBlockImages.DEAD_ENEMY),
        DEAD_13(" "),
        DEAD_14(DeadEnemyBlockImages.DEAD_ENEMY);

        private final String display;

        State(String display) {
            this.display = display;
        }
    }
}
