package thd.game.managers;

import thd.game.utilities.SortedGameObjectsList;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.EnemyGunner;
import thd.gameobjects.movable.Humvee;
import thd.gameobjects.movable.Moped;
import thd.gameobjects.movable.Vehicle;
import thd.gameobjects.unmovable.DeadEnemy;
import thd.gameobjects.unmovable.EnemyMortar;
import thd.gameobjects.unmovable.ShootingBox;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


class GameObjectManager extends CollisionManager {
    private static class CompareByLowerEdge implements Comparator<GameObject> {
        @Override
        public int compare(GameObject o1, GameObject o2) {
            return Double.compare(applySortingRule(o1), applySortingRule(o2));
        }

        private double applySortingRule(GameObject gameObject) {
            return (gameObject.getPosition().getY() + gameObject.getHeight()) * gameObject.getDistanceToBackground();
        }
    }

    private final List<GameObject> gameObjects;
    private final List<GameObject> gameObjectsToBeAdded;
    private final List<GameObject> gameObjectsToBeRemoved;
    private boolean enemyInGameObjects;

    GameObjectManager() {
        super();

        gameObjects = new SortedGameObjectsList();
        gameObjectsToBeAdded = new LinkedList<>();
        gameObjectsToBeRemoved = new LinkedList<>();
        enemyInGameObjects = false;
    }

    void gameLoopUpdate() {
        updateLists();
        enemyInGameObjects = false;

        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            gameObject.updatePosition();
            gameObject.addToCanvas();
            if (gameObject instanceof EnemyGunner || gameObject instanceof EnemyMortar || gameObject instanceof DeadEnemy || gameObject instanceof Vehicle) {
                enemyInGameObjects = true;
            }
        }

        manageCollisions(true);
    }

    boolean enemyExisting() {
        return enemyInGameObjects;
    }


    private void updateLists() {
        removeFromGameObjects();
        addToGameObjects();
        gameObjects.sort(new CompareByLowerEdge());

        if (gameObjects.size() > MAXIMUM_NUMBER_OF_GAME_OBJECTS) {
            throw new TooManyGameObjectsException("You have %d GameObjects, the limit is %d!".formatted(gameObjects.size(), MAXIMUM_NUMBER_OF_GAME_OBJECTS));
        }
    }

    private void removeFromGameObjects() {
        for (GameObject gameObject : gameObjectsToBeRemoved) {
            gameObjects.remove(gameObject);
            removeFromCollisionManagement(gameObject);
        }
        gameObjectsToBeRemoved.clear();
    }

    private void addToGameObjects() {
        for (GameObject toAdd : gameObjectsToBeAdded) {
            gameObjects.add(toAdd);
            addToCollisionManagement(toAdd);
        }
        gameObjectsToBeAdded.clear();
    }

    void add(GameObject gameObject) {
        gameObjectsToBeAdded.add(gameObject);
    }

    void remove(GameObject gameObject) {
        gameObjectsToBeRemoved.add(gameObject);
    }

    void removeAll() {
        gameObjectsToBeAdded.clear();
        gameObjectsToBeRemoved.addAll(gameObjects);
    }
}
