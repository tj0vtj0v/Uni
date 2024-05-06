package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.blockImages.DeadEnemyBlockImages;

public class DeadEnemy extends GameObject implements ShiftableGameObject {
    private static final int TARGET_ANIMATION_REPETITIONS = 4;
    private int animationRepetitions;
    private DeadState deadState;

    public DeadEnemy(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        deadState = DeadState.DEAD_1;
        blockImage = deadState.display;
        animationRepetitions = 0;

        distanceToBackground = 100;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;

        speedInPixel = 0;

        this.position.updateCoordinates(position);
    }

    private void switchToNextState() {
        int nextState = (deadState.ordinal() + 1) % DeadState.values().length;
        deadState = DeadState.values()[nextState];
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

            if (deadState == DeadState.DEAD_14) {
                animationRepetitions += 1;

                if (animationRepetitions >= TARGET_ANIMATION_REPETITIONS) {
                    gamePlayManager.destroyGameObject(this);
                }
            }

            blockImage = deadState.display;

            if (deadState.ordinal() % 2 == 1) {
                blockImage = mirrorBlockImage(blockImage);
            }
        }
    }

    private enum DeadState {
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

        DeadState(String display) {
            this.display = display;
        }
    }
}
