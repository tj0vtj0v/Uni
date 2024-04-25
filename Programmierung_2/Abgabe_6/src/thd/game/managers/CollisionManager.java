package thd.game.managers;

import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.GameObject;

import java.util.LinkedList;
import java.util.List;

class CollisionManager {

    private final List<CollidingGameObject> collidingGameObjects;

    protected CollisionManager() {
        collidingGameObjects = new LinkedList<>();
    }

    /**
     * Manages collisions between any game objects in the list. If a collision is detected, the method
     * {@link CollidingGameObject#reactToCollisionWith(CollidingGameObject)} is called for both game objects.
     * If <code>showHitBoxes</code> is true, all hitboxes of game objects in the list are shown as red rectangles.
     *
     * @param showHitBoxes If true, hitboxes of colliding game objects in the list are shown.
     */
    protected void manageCollisions(boolean showHitBoxes) {
        int sublistIndex = 0;
        for (CollidingGameObject gameObjectA : collidingGameObjects) {
            sublistIndex++;
            List<CollidingGameObject> restOfList = collidingGameObjects.subList(sublistIndex, collidingGameObjects.size());
            for (CollidingGameObject gameObjectB : restOfList) {
                if (gameObjectA.collidesWith(gameObjectB)) {
                    gameObjectA.reactToCollisionWith(gameObjectB);
                    gameObjectB.reactToCollisionWith(gameObjectA);
                }
            }
            if (showHitBoxes) {
                gameObjectA.showHitBox();
            }
        }
    }

    protected void addToCollisionManagement(GameObject toAdd) {
        if (toAdd instanceof CollidingGameObject collidingGameObject) {
            collidingGameObjects.add(collidingGameObject);
        }
    }

    protected void removeFromCollisionManagement(GameObject toRemove) {
        if (toRemove instanceof CollidingGameObject collidingGameObject) {
            collidingGameObjects.remove(collidingGameObject);
        }
    }
}
