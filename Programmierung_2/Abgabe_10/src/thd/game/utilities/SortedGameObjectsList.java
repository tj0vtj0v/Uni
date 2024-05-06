package thd.game.utilities;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;

/**
 * Helper for the sorting of the GameObjects.
 */
public class SortedGameObjectsList extends LinkedList<GameObject> {
    @Override
    public boolean add(GameObject toAdd) {
        addLast(toAdd);
        return true;
    }
}
