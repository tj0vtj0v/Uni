package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.EnemyDoorGunner;
import thd.gameobjects.movable.EnemyGunner;
import thd.gameobjects.movable.MainCharacterImpl;

import java.util.List;

/**
 * Represents a Spawner for EnemyGunner.
 */
public class Spawner extends GameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private final Direction location;
    private final boolean basic;
    private final List<CollidingGameObject> collidingGameObjectsForPathDecision;

    /**
     * Crates a new GameObject.
     *
     * @param gameView                            GameView to show the game object on.
     * @param gamePlayManager                     GamePlayManager to manage the game actions.
     * @param location                            location on which side of the screen the enemy's should spawn.
     * @param position                            position where the spawner is placed.
     * @param collidingGameObjectsForPathDecision list for Enemy Instances.
     * @param basic                               determines if the spawner is basic or at the door.
     */
    public Spawner(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision, boolean basic) {
        super(gameView, gamePlayManager);

        blockImage = "";

        this.position.updateCoordinates(position);
        this.location = location;
        this.basic = basic;
        this.collidingGameObjectsForPathDecision = collidingGameObjectsForPathDecision;

        updateStatus();
    }

    @Override
    public void addToCanvas() {
    }

    @Override
    public void updateStatus() {
        super.updateStatus();

        if (gamePlayManager.endReached && basic) {
            gamePlayManager.destroyGameObject(this);
        }

        if (gameView.timer(gamePlayManager.currentLevel().enemySpawnInterval, this) && basic) {
            double x = Math.abs(random.nextGaussian(-50, 20) + position.getX());
            double y = position.getY() + random.nextInt(200);

            gamePlayManager.spawnGameObject(new EnemyGunner(gameView, gamePlayManager, location, new Position(x, y), collidingGameObjectsForPathDecision));

        } else if (gameView.timer(gamePlayManager.currentLevel().enemySpawnInterval / 2, this) && !basic && !gamePlayManager.overlay.isMessageShown()) {

            gamePlayManager.spawnGameObject(new EnemyDoorGunner(gameView, gamePlayManager, location, new Position(position), collidingGameObjectsForPathDecision));

            if (random.nextInt(gamePlayManager.currentLevel().enemyDoorSpawn) == 0) {
                gamePlayManager.destroyGameObject(this);
            }
        }
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= DEFAULT_SPAWN_DISTANCE;
    }

    @Override
    public String toString() {
        return "Spawner: on the %s at %s is %b basic".formatted(location.name(), position, basic);
    }
}
