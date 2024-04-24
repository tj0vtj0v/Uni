package thd.game.managers;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;
import java.util.List;


class GameObjectManager {
    private final List<GameObject> gameObjects;
    private final List<GameObject> gameObjectsToBeAdded;
    private final List<GameObject> gameObjectsToBeRemoved;
    private static final int MAXIMUM_NUMBER_OF_GAME_OBJECTS = 500;

    GameObjectManager() {
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
    }

    private void updateLists() {
        removeFromGameObjects();
        addToGameObjects();

        if (gameObjects.size() > MAXIMUM_NUMBER_OF_GAME_OBJECTS) {
            throw new TooManyGameObjectsException("You have %d GameObjects, the limit is %d!".formatted(gameObjects.size(), MAXIMUM_NUMBER_OF_GAME_OBJECTS));
        }
    }

    private void removeFromGameObjects() {
        gameObjects.removeAll(gameObjectsToBeRemoved);
        gameObjectsToBeRemoved.clear();
    }

    private void addToGameObjects() {
        gameObjects.addAll(gameObjectsToBeAdded);
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
