package thd.gameobjects.unmovable;


import thd.game.utilities.GameView;
import thd.gameobjects.base.CharacterBlockImages;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;

/**
 * Representation of the in-game-object 'EnemyMortar'.
 * <p>
 * unmoving
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class EnemyMortar extends GameObject {

    /**
     * Creates EnemyMortar with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public EnemyMortar(GameView gameView) {
        super(gameView);

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        position.updateCoordinates(GameView.WIDTH - 200, 200);
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(CharacterBlockImages.Enemy.Mortar.LOADING, position.getX(), position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(ObjectBlockImages.MORTAR, position.getX() - 18, position.getY() + 21, size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Mortar: %s".formatted(position);
    }
}
