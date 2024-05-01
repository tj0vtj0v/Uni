package thd.game.bin;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

import java.util.*;

public class SortedPositions {
    public static class CompareByXCoordinate implements Comparator<Position> {

        @Override
        public int compare(Position o1, Position o2) {
            return Double.compare(o1.horizontalDistance(new Position()), o2.horizontalDistance(new Position()));
        }
    }

    private static final int NUMBER_OF_POSITIONS = 5;
    private final Position[] positions;

    public static void main(String[] args) {
        SortedPositions sortedPositions = new SortedPositions();
        sortedPositions.sortAndPrintPositions();
    }

    /**
     * Das Array mit Positionen wird in drei verschiedenen Sortierungen ausgegeben.
     */
    private void sortAndPrintPositions() {
        // Sortieren Sie die Positionen nach Ihrer natürlichen Ordnung (eine Zeile Code).
        Arrays.sort(positions);
        System.out.println(Arrays.deepToString(positions));

        // Erstellen Sie eine geschachtelte öffentliche statische Klasse "CompareByXCoordinate" innerhalb dieser Klasse.
        // Diese Klasse implementiert eine Ordnung für Position, geordnet nach der Größe der x-Koordinate.

        // Sortieren Sie mit Hilfe von "CompareByXCoordinate" die Positionen nach Ihrer x-Koordinate (eine Zeile Code).
        Arrays.sort(positions, new CompareByXCoordinate());
        System.out.println(Arrays.deepToString(positions));

        // Sortieren Sie die Positionen hier nach Ihrer y-Koordinate mit Hilfe eines Lambda-Ausdrucks (eine Zeile Code).
        // Arrays.sort(positions, Comparator.comparingDouble(Position::getY));
        Arrays.sort(positions, (p1, p2) -> Double.compare(p1.getY(), p2.getY()));
        System.out.println(Arrays.deepToString(positions));
    }

    public SortedPositions() {
        this.positions = new Position[NUMBER_OF_POSITIONS];
        createPositions();
    }

    private void createPositions() {
        Random random = new Random(0);
        for (int i = 0; i < NUMBER_OF_POSITIONS; i++) {
            positions[i] = new Position(random.nextInt(GameView.WIDTH), random.nextInt(GameView.WIDTH));
        }
    }
}
