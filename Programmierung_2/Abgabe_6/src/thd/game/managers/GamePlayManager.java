package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.EnemyGunner;
import thd.gameobjects.movable.Humvee;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.movable.Moped;
import thd.gameobjects.unmovable.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the whole plot of the game.
 */
public class GamePlayManager extends UserControlledGameObjectPool{
    private final GameObjectManager gameObjectManager;
    private final List<CollidingGameObject> collidingGameObjectsForPathDecision;

    /**
     * Creates an instance of the GamePlayManager.
     *
     * @param gameView window to manage.
     */
    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();

        collidingGameObjectsForPathDecision = new ArrayList<>();

        scoreBoard = new ScoreBoard(gameView, this);

        humvee = new Humvee(gameView, this);
        moped = new Moped(gameView, this);
        shootingBox = new ShootingBox(gameView, this);

        stone = new Stone(gameView, this);
        tree = new Tree(gameView, this);
        wall = new Wall(gameView, this);

        enemyMortar = new EnemyMortar(gameView, this);

        collidingGameObjectsForPathDecision.add(shootingBox);
        collidingGameObjectsForPathDecision.add(stone);
        collidingGameObjectsForPathDecision.add(tree);
        collidingGameObjectsForPathDecision.add(wall);

        mainCharacter = new MainCharacterImpl(gameView, this, collidingGameObjectsForPathDecision);

        enemyGunner = new EnemyGunner(gameView, this, collidingGameObjectsForPathDecision);

        spawnGameObject(humvee);
        spawnGameObject(moped);
        spawnGameObject(shootingBox);
        spawnGameObject(enemyGunner);
        spawnGameObject(enemyMortar);
        spawnGameObject(mainCharacter);
        spawnGameObject(stone);
        spawnGameObject(tree);
        spawnGameObject(wall);
        spawnGameObject(scoreBoard);
    }

    /**
     * Spawns Object onto {@link GameView} canvas.
     *
     * @param gameObject GameObject to create.
     */
    public void spawnGameObject(GameObject gameObject) {
        gameObjectManager.add(gameObject);
    }

    /**
     * Removes Object from {@link GameView} canvas.
     *
     * @param gameObject GameObject to destroy.
     */
    public void destroyGameObject(GameObject gameObject) {
        gameObjectManager.remove(gameObject);
        collidingGameObjectsForPathDecision.remove(gameObject);
    }

    protected void destroyAllGameObjects() {
        gameObjectManager.removeAll();
    }

    private void gamePlayManagement() {
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
        gamePlayManagement();
    }
}
