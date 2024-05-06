package thd.game.utilities;

import thd.gameobjects.base.GameObject;

import java.util.Comparator;
import java.util.LinkedList;

public class SortedGameObjectsList extends LinkedList<GameObject> {

    private static class CompareByLowerEdge implements Comparator<GameObject> {
        @Override
        public int compare(GameObject o1, GameObject o2) {
            return Double.compare(applySortingRule(o1), applySortingRule(o2));
        }

        private double applySortingRule(GameObject gameObject) {
            return (gameObject.getPosition().getY() + gameObject.getHeight()) * gameObject.getDistanceToBackground();
        }
    }

    public void sort() {
        super.sort(new CompareByLowerEdge());
    }

    @Override
    public boolean add(GameObject gameObject) {
        super.add(gameObject);
        this.sort();
        return true;
    }
}
