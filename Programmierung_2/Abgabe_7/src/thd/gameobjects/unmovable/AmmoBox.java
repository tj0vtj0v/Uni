package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.MainCharacterImpl;


/**
 * Representation of the in-game-object 'AmmoBox'.
 * <p>
 * unmoving
 * collectable
 * BlockImage
 */
public class AmmoBox extends CollidingGameObject {
    /**
     * Creates Ammo box with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public AmmoBox(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        blockImage = ObjectBlockImages.AMMOBOX;
        distanceToBackground = 200;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof MainCharacterImpl) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public String toString() {
        return "AmmoBox: %s".formatted(position);
    }
}
