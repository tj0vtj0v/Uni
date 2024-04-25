package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Manages the whole plot of the game.
 */
public class GamePlayManager extends UserControlledGameObjectPool{
    private final GameObjectManager gameObjectManager;

    /**
     * Creates an instance of the GamePlayManager.
     *
     * @param gameView window to manage.
     */
    protected GamePlayManager(GameView gameView) {
        super(gameView);

        this.gameObjectManager = new GameObjectManager();
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
