package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ExplosionBlockImages;

/**
 * Explosion dealing damage after Grenade has flown long enough.
 */
public class Explosion extends CollidingGameObject implements ShiftableGameObject {
    private State currentState;

    /**
     * Creates Explosion.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position where to be located.
     */
    public Explosion(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);
        currentState = State.EXPLOSION_1;
        blockImage = currentState.display;

        distanceToBackground = LAYER_1;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(size * -1, size * -1, size * 2, size * 2);

        speedInPixel = 0;

        gameView.playSound("bumm.wav", false);
    }

    private void switchToNextState() {
        int nextState = (currentState.ordinal() + 1) % State.values().length;
        currentState = State.values()[nextState];
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (gameView.timer(ANIMATION_SPEED, this)) {
            switchToNextState();

            if (currentState == State.EXPLOSION_1) {
                gamePlayManager.destroyGameObject(this);
                return;
            }

            position.up(currentState.upShift * size);
            position.left(currentState.leftShift * size);

            blockImage = currentState.display;
            width = generateWidthFromBlockImage() * size;
            height = generateHeightFromBlockImage() * size;
            hitBoxOffsets(size * -1, size * -1, size * 2, size * 2);
        }
        blockImage = currentState.display;
    }

    private enum State {
        EXPLOSION_1(ExplosionBlockImages.EXPLOSION_1, 0, 0),
        EXPLOSION_2(ExplosionBlockImages.EXPLOSION_2, 3, 2),
        EXPLOSION_3(ExplosionBlockImages.EXPLOSION_3, 4, 0),
        EXPLOSION_4(ExplosionBlockImages.EXPLOSION_4, 1, 2),
        EXPLOSION_7(ExplosionBlockImages.EXPLOSION_4, 0, 0),
        EXPLOSION_8(ExplosionBlockImages.EXPLOSION_3, -1, -2),
        EXPLOSION_9(ExplosionBlockImages.EXPLOSION_2, -4, 0),
        EXPLOSION_10(ExplosionBlockImages.EXPLOSION_1, -3, -2);

        private final String display;
        private final int upShift;
        private final int leftShift;

        State(String display, int upShift, int leftShift) {
            this.display = display;
            this.upShift = upShift;
            this.leftShift = leftShift;
        }
    }
}
