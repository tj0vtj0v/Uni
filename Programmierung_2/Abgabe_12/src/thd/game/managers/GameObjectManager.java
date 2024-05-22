package thd.game.managers;

import thd.game.utilities.SortedGameObjectsList;
import thd.gameobjects.base.GameObject;

import java.util.*;


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

    GameObjectManager() {
        super();

        gameObjects = new SortedGameObjectsList();
        gameObjectsToBeAdded = new LinkedList<>();
        gameObjectsToBeRemoved = new LinkedList<>();
    }

    void gameLoopUpdate() {
        updateLists();

        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            gameObject.updatePosition();
            gameObject.addToCanvas();
        }

        manageCollisions(true);
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
