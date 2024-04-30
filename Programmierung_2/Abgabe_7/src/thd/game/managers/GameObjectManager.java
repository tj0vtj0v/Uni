package thd.game.managers;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;
import java.util.List;


class GameObjectManager extends CollisionManager {
    private final List<GameObject> gameObjects;
    private final List<GameObject> gameObjectsToBeAdded;
    private final List<GameObject> gameObjectsToBeRemoved;
    private static final int MAXIMUM_NUMBER_OF_GAME_OBJECTS = 500;

    GameObjectManager() {
        super();

        gameObjects = new LinkedList<>();
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

        manageCollisions(false); //TODO
    }

    private void updateLists() {
        removeFromGameObjects();
        addToGameObjects();

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
            sortIntoGameObjects(toAdd);
            addToCollisionManagement(toAdd);
        }
        gameObjectsToBeAdded.clear();
    }

    private void sortIntoGameObjects(GameObject toAdd) {
        int indexToSortIn = 0;
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getDistanceToBackground() >= toAdd.getDistanceToBackground()) {
                break;
            }
            indexToSortIn++;
        }
        gameObjects.add(indexToSortIn, toAdd);
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
