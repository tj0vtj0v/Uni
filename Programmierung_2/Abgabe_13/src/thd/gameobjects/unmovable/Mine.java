package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.movable.Vehicle;
import thd.gameobjects.resources.ObjectBlockImages;

/**
 * Represents a Mine.
 */
public class Mine extends CollidingGameObject implements ShiftableGameObject {
    Mine(GameView gameView, GamePlayManager gamePlayManager, Direction facing, Position position) {
        super(gameView, gamePlayManager, facing, position);

        blockImage = ObjectBlockImages.MINE;
        distanceToBackground = LAYER_1;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;

        hitBoxOffsets(size * -1, size * -5, size * 2, size * -2);
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (position.getX() > GameView.WIDTH || position.getX() < 0) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof MovingCharacter || other instanceof Vehicle || other instanceof Explosion) {
            if (other instanceof MainCharacterImpl) {
                gamePlayManager.addPoints(-500);
            }
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new Explosion(gameView, gamePlayManager, direction, position));
        } else {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
