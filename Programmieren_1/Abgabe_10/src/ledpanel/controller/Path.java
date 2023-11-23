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

    /**
     * Der Pfad wird als LED-Array zurückgegeben.
     *
     * @return Der fertiggestellte Pfad.
     */
    int[] getLeds() {
        return leds;
    }

    private void calculatePath() {
        for (int keyIndex = 1; keyIndex < keyPositions.length; keyIndex++) {
            deleteLastLed();
            if (Math.abs(keyPositions[keyIndex - 1] - keyPositions[keyIndex]) < 40) {
                if (keyPositions[keyIndex - 1] < keyPositions[keyIndex]) {
                    for (int pathStep = keyPositions[keyIndex - 1]; pathStep <= keyPositions[keyIndex]; pathStep++) {
                        appendLed(pathStep);
                    }
                } else {
                    for (int pathStep = keyPositions[keyIndex - 1]; pathStep >= keyPositions[keyIndex]; pathStep--) {
                        appendLed(pathStep);
                    }
                }
            } else {
                if (keyPositions[keyIndex - 1] < keyPositions[keyIndex]) {
                    for (int pathStep = keyPositions[keyIndex - 1]; pathStep <= keyPositions[keyIndex]; pathStep += 40) {
                        appendLed(pathStep);
                    }
                } else {
                    for (int pathStep = keyPositions[keyIndex - 1]; pathStep >= keyPositions[keyIndex]; pathStep -= 40) {
                        appendLed(pathStep);
                    }
                }
            }
        }
    }

    private void deleteLastLed() {
        if (size() > 0) {
            int[] newLeds = new int[size() - 1];
            System.arraycopy(leds, 0, newLeds, 0, size() - 1);

            leds = newLeds;
        }
    }
    private void appendLed(int led) {
        int[] newLeds = new int[size() + 1];
        System.arraycopy(leds, 0, newLeds, 0, size());
        newLeds[size()] = led;

        leds = newLeds;
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