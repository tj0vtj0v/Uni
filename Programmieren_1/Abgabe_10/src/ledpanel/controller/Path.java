package ledpanel.controller;

/**
 * Diese Klasse erlaubt das Erstellen von LED-Pfaden. Sie füllt fehlende LEDs auf, sodass man jeweils nur Start- und
 * Ende-LED angeben muss, um einen vollständigen Pfad zu erzeugen. Darüber hinaus können Pfade hintereinander geschaltet
 * werden.
 */
public class Path {
    private final int[] keyPositions;
    private int[] leds;

    /**
     * Ein Pfad wird erzeugt. Nur einzelne Wegpunkte auf dem Pfad müssen in der richtigen Reihenfolge übergeben werden.
     * Es sind nur gerade Wege erlaubt (links, rechts, runter, rauf), Diagonalen sind verboten.
     * <br><br>
     * Beispiele für Pfade:<br>
     * <pre>
     * {@code new Path(0, 10)} --> (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
     * {@code new Path(10, 0)} --> (10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
     * {@code new Path(0, 280)} --> (0, 40, 80, 120, 160, 200, 240, 280)
     * {@code new Path(0, 39, 319, 280, 0)} --> Eine Runde im Viereck um das Panel </pre><br>
     * <br>
     *
     * @param keyPositions Die Wegpunkte auf dem Pfad.
     */
    public Path(int... keyPositions) {
        this.keyPositions = keyPositions;
        leds = new int[0];
        calculatePath();
    }

    private void calculatePath() {
        int lastPosition;
        int nextPosition = 0;

        for (int keyIndex = 1; keyIndex < keyPositions.length; keyIndex++) {
            lastPosition = keyPositions[keyIndex - 1];
            nextPosition = keyPositions[keyIndex];

            if (Math.abs(lastPosition - nextPosition) < 40) {
                if (lastPosition < nextPosition) {
                    addPathSection(lastPosition, nextPosition, 1);
                } else {
                    addPathSection(lastPosition, nextPosition, -1);
                }
            } else {
                if (lastPosition < nextPosition) {
                    addPathSection(lastPosition, nextPosition, 40);
                } else {
                    addPathSection(lastPosition, nextPosition, -40);
                }
            }
        }
        appendLed(nextPosition);
    }

    private void addPathSection(int lastPosition, int nextPosition, int additionalValuePerStep) {
        for (int pathStep = lastPosition; pathStep != nextPosition; pathStep += additionalValuePerStep) {
            appendLed(pathStep);
        }
    }

    private void appendLed(int ledIndex) {
        int[] newLeds = new int[size() + 1];
        System.arraycopy(leds, 0, newLeds, 0, size());
        newLeds[size()] = ledIndex;

        leds = newLeds;
    }

    /**
     * Der Pfad wird als LED-Array zurückgegeben.
     *
     * @return Der fertiggestellte Pfad.
     */
    int[] getLeds() {
        return leds;
    }

    /**
     * Die Größe des Pfads.
     *
     * @return Größe des Pfads.
     */
    int size() {
        return leds.length;
    }
}