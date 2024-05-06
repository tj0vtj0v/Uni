package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the whole plot of the game.
 */
public class GamePlayManager extends WorldShiftManager {
    static final int LIVES = 3;
    private final GameObjectManager gameObjectManager;
    final List<CollidingGameObject> collidingGameObjectsForPathDecision;

    int lives;
    int points;
    private int highScore;

    /**
     * Creates an instance of the GamePlayManager.
     *
     * @param gameView window to manage.
     */
    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();
        collidingGameObjectsForPathDecision = new ArrayList<>();
        lives = LIVES;
        points = 0;
        highScore = 0;
    }

    /**
     * Reduces remaining lives of the main character and raises an error if there are no lives left.
     *
     * @throws NoRemainingMenException no men left results in game over.
     */
    public void reduceLive() throws NoRemainingMenException {
        lives--;

        if (lives <= 0) {
            throw new NoRemainingMenException("No men left");
        }
    }

    /**
     * Adds points to the current Score and updates highScore if necessary.
     *
     * @param points amount of points to be added to the score.
     */
    public void addScorePoints(int points) {
        this.points += points;
        highScore = Math.max(points, highScore);
    }

    /**
     * Getter for the points collected in this game.
     *
     * @return amount of scored points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter of the Highscore in this run of the game.
     *
     * @return highScore.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Getter of the remaining grenades to the main character.
     *
     * @return remaining grenades.
     */
    public int availableGrenades() {
        return mainCharacter.getAvailableGrenades();
    }

    /**
     * Getter of the remaining lives of the main character.
     *
     * @return remaining lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Current level for character influence.
     *
     * @return current Level.
     */
    public Level currentLevel() {
        return level;
    }


    /**
     * Method that initiates the gameOver sequence TODO.
     *
     * @param success determines if the game war cleared or failed.
     */
    public void gameOver(boolean success) {
        System.exit(0);
    }

    private void gamePlayManagement() {
    }

    @Override
    public void spawnGameObject(GameObject gameObject) {
        super.spawnGameObject(gameObject);
        gameObjectManager.add(gameObject);
    }

    /**
     * Spawns Object onto {@link GameView} canvas.
     *
     * @param collidingGameObject CollidingGameObject to create.
     */
    public void spawnPathBlockingGameObject(CollidingGameObject collidingGameObject) {
        spawnGameObject(collidingGameObject);
        collidingGameObjectsForPathDecision.add(collidingGameObject);
    }

    @Override
    public void destroyGameObject(GameObject gameObject) {
        super.destroyGameObject(gameObject);
        gameObjectManager.remove(gameObject);
        collidingGameObjectsForPathDecision.remove(gameObject);
    }

    @Override
    protected void destroyAllGameObjects() {
        super.destroyAllGameObjects();
        gameObjectManager.removeAll();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
        gamePlayManagement();
    }
}
