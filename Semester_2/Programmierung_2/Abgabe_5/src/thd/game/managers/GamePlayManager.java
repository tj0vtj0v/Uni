package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Square;

/**
 * Manages the whole plot of the game.
 */
public class GamePlayManager extends UserControlledGameObjectPool{
    private final GameObjectManager gameObjectManager;
    private int currentNumberOfVisibleSquares;

    /**
     * Creates an instance of the GamePlayManager.
     *
     * @param gameView window to manage.
     */
    protected GamePlayManager(GameView gameView) {
        super(gameView);

        this.gameObjectManager = new GameObjectManager();
        currentNumberOfVisibleSquares = 0;
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
        if (gameView.timer(1000, this) && currentNumberOfVisibleSquares < 5) {
            spawnGameObject(new Square(gameView, this));
            currentNumberOfVisibleSquares++;
        }
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
        gamePlayManagement();
    }
}
