package thd.game.utilities;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;

/**
 * Helper for the sorting of the GameObjects.
 */
public class SortedGameObjectsList extends LinkedList<GameObject> {
    @Override
    public boolean add(GameObject toAdd) {
        int indexToSortIn = 0;
        for (GameObject gameObject : this) {
            if (gameObject.getDistanceToBackground() >= toAdd.getDistanceToBackground()) {
                break;
            }
            indexToSortIn++;
        }
        add(indexToSortIn, toAdd);
        return true;
    }
}
