package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;


/**
 * Representation of the in-game-object 'ShootingBox'.
 * <p>
 * unmoving
 * destructible by 1 {@link Grenade} or 2 {@link Bullet}
 * BlockImage
 */
public class ShootingBox extends CollidingGameObject {
    private int hitTolerance;

    /**
     * Creates ShootingBox with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public ShootingBox(GameView gameView, GamePlayManager gamePlayManager, Direction direction, Position position) {
        super(gameView, gamePlayManager, direction, position);

        blockImage = ObjectBlockImages.SHOOTING_BOX_LEFT;
        distanceToBackground = 200;
        hitTolerance = 2;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(3, 3, -6, -18);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet) {
            hitTolerance--;

            if (hitTolerance <= 0) {
                gamePlayManager.destroyGameObject(this);
                gamePlayManager.addScorePoints(-1);
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "ShootingBox: %s with %d hits left till destruction".formatted(position, hitTolerance);
    }
}
