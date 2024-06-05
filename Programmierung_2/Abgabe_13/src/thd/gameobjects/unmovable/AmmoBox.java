package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.resources.ObjectBlockImages;


/**
 * Representation of the in-game-object 'AmmoBox'.
 * <p>
 * unmoving
 * collectable
 * BlockImage
 */
public class AmmoBox extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    /**
     * Creates Ammo box with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position where to be located.
     */
    public AmmoBox(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        blockImage = ObjectBlockImages.AMMO_BOX;
        distanceToBackground = LAYER_2;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= DEFAULT_SPAWN_DISTANCE;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof MainCharacterImpl) {
            gamePlayManager.addScorePoints(10);
            gamePlayManager.destroyGameObject(this);
        } else if (other instanceof Explosion) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public String toString() {
        return "AmmoBox: %s".formatted(position);
    }
}
