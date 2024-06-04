package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.EnemyGunner;
import thd.gameobjects.movable.MainCharacterImpl;

import java.util.List;

/**
 * Represents a Spawner for EnemyGunner.
 */
public class Spawner extends GameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private final Direction location;
    private final List<CollidingGameObject> collidingGameObjectsForPathDecision;

    /**
     * Crates a new GameObject.
     *
     * @param gameView                            GameView to show the game object on.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param location                            location on which side of the screen the enemy's should spawn.
     * @param position                            position where the spawner is placed.
     * @param collidingGameObjectsForPathDecision list for Enemy Instances.
     */
    public Spawner(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager);

        blockImage = "";

        this.position.updateCoordinates(position);
        this.location = location;
        this.collidingGameObjectsForPathDecision = collidingGameObjectsForPathDecision;

        updateStatus();
    }

    @Override
    public void addToCanvas() {
    }

    @Override
    public void updateStatus() {
        super.updateStatus();

        if (gamePlayManager.endReached) {
            gamePlayManager.destroyGameObject(this);
        }

        if (gameView.timer(gamePlayManager.currentLevel().enemySpawnInterval, this)) {
            double x = Math.abs(random.nextGaussian(0, 50) + position.getX());
            double y = position.getY() + random.nextInt(200);
            gamePlayManager.spawnGameObject(new EnemyGunner(gameView, gamePlayManager, location, new Position(x, y), collidingGameObjectsForPathDecision));
        }
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= DEFAULT_SPAWN_DISTANCE;
    }

    @Override
    public String toString() {
        return "Spawner: on the %s at %s".formatted(location.name(), position);
    }
}
