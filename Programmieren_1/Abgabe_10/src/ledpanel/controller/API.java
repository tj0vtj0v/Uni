package ledpanel.controller;

import ledpanel.hardware.LEDPanel;

import java.util.Arrays;

/**
 * Diese Klasse enthält das API (Application Programming Interface) für das LED-Panel. Die angebotenen Methoden können
 * genutzt werden, um das Panel komfortabel für unterschiedliche Anzeigen. zu nutzen. Alle 320 LEDs des Panels (40 mal 8
 * LEDs) sind von 0 bis 319 durchnummeriert.
 * <br>
 * <br>
 * Die Anordnung der LEDs ist wie folgt gegeben:
 * <br><pre>
 *    0 . . . . . . . . . . . . . . . . . . . . . 39 <br>
 *   40 . . . . . . . . . . . . . . . . . . . . . 79 <br>
 *   80 . . . . . . . . . . . . . . . . . . . . . 119<br>
 *  120 . . . . . . . . . . . . . . . . . . . . . 159<br>
 *  160 . . . . . . . . . . . . . . . . . . . . . 199<br>
 *  200 . . . . . . . . . . . . . . . . . . . . . 239<br>
 *  240 . . . . . . . . . . . . . . . . . . . . . 279<br>
 *  280 . . . . . . . . . . . . . . . . . . . . . 319</pre>
 * <br>
 */
public class API {
    LEDPanel ledPanel;
    byte[][] matrices;

    public API() {
        ledPanel = new LEDPanel();
        matrices = new byte[][]{ledPanel.matrix0, ledPanel.matrix1, ledPanel.matrix2, ledPanel.matrix3, ledPanel.matrix4};
    }


    /**
     * Diese Methode gibt das von der API genutzte LED-Panel zurück. So bekommt der Benutzer direkten Zugriff auf das
     * laufende Panel.
     *
     * @return Das für die API genutzte LED-Panel.
     */
    public LEDPanel directHardwareAccess() {
        return ledPanel;
    }

    /**
     * Alle LEDs werden gleichzeitig für eine halbe Sekunde eingeschaltet. Nach Ablauf der Zeit wird die Anzeige
     * gelöscht.
     */
    public void testLEDPanel() {
        addressAllLedRows((byte) 255);
        waitFor(500);
        addressAllLedRows((byte) 0);
    }

    private void addressAllLedRows(byte lightStatus) {
        for (byte[] matrix : matrices) {
            for (int row = 0; row < 8; row++) {
                matrix[row] = lightStatus;
            }
        }
    }


    /**
     * Die im Array übergebenen LEDs werden gleichzeitig für den angegebenen Zeitraum eingeschaltet. Danach wird die
     * Anzeige gelöscht.
     * <br>
     * Beispielsweise schaltet der Aufruf <pre>{@code showLEDs(new int[]{0, 39, 280, 319}, 1000)}</pre> die LEDs in den
     * 4 Ecken für eine Sekunde ein.
     *
     * @param ledIndices   Die LEDs, die eingeschaltet werden sollen.
     * @param milliseconds Die LEDs bleiben für diesen Zeitraum eingeschaltet.
     */
    public void showLEDs(int[] ledIndices, int milliseconds) {
        for (int ledIndex : ledIndices) {
            onSingleLed(ledIndex);
        }
        waitFor(milliseconds);
        addressAllLedRows((byte) 0);
    }

    public void onSingleLed(int ledIndex) {
        int addressedLedRow = ledIndex / 40;
        int addressedLedMatrix = (ledIndex % 40) / 8;
        int addressedLedColumn = ledIndex % 40 % 8;

        matrices[addressedLedMatrix][addressedLedRow] = (byte) (matrices[addressedLedMatrix][addressedLedRow] | (byte) Math.pow(2, addressedLedColumn));
    }

    public void offSingleLed(int ledIndex) {
        int addressedLedRow = ledIndex / 40;
        int addressedLedMatrix = (ledIndex % 40) / 8;
        int addressedLedColumn = ledIndex % 40 % 8;

        matrices[addressedLedMatrix][addressedLedRow] = (byte) (matrices[addressedLedMatrix][addressedLedRow] & (byte) (255 - Math.pow(2, addressedLedColumn)));
    }


    /**
     * Die im Array übergebenen LEDs blinken. Nach Ablauf aller Wiederholungen ist die Anzeige wieder leer.
     * <br>
     * Beispielsweise führt der Aufruf von
     * <pre>{@code blinkLEDs(new int[]{139, 140, 179, 180}, 300, 100, 5)}</pre> zu vier blinkenden LEDs in der
     * Mitte des Panels. Die LEDs sind in jeder der 5 Wiederholungen für 300 Millisekunden zu sehen und dann für 100
     * Millisekunden nicht zu sehen.
     *
     * @param leds            Die LEDs, die blinken sollen.
     * @param millisecondsOn  Die LEDs bleiben während des Blinkens für diesen Zeitraum eingeschaltet.
     * @param millisecondsOff Die LEDs bleiben während des Blinkens für diesen Zeitraum ausgeschaltet.
     * @param repetitions     Anzahl der Wiederholungen.
     */
    public void showBlinkingLEDs(int[] leds, int millisecondsOn, int millisecondsOff, int repetitions) {
        for (int repetition = 1; repetition <= repetitions; repetition++) {
            for (int led : leds) {
                onSingleLed(led);
            }
            waitFor(millisecondsOn);
            addressAllLedRows((byte) 0);
            waitFor(millisecondsOff);
        }
    }

    /**
     * Ein Punkt läuft durch einen vorgegebenen Pfad von LEDs. Auf jeder LED verweilt die Anzeige für den angegebenen
     * Zeitraum. Mit der Klasse {@link Path} wird ein Array von LEDs übergeben, das durchlaufen werden soll. Danach ist
     * die Anzeige wieder leer.
     * <p>
     * Die Klasse {@link Path} unterstützt das einfache Erzeugen von Pfaden. Sie füllt fehlende LEDs auf, sodass man
     * jeweils nur Start- und Ende-LED angeben muss, um einen vollständigen Pfad zu erzeugen. Beispielsweise führt der
     * Pfad <pre>{@code new Path(0, 10)}</pre> zu einem Pfad von links nach rechts (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10).
     * <br><br>
     * Der Aufruf von
     * <pre>{@code showRunningDot(new Path(0, 39, 319, 280, 0), 20)}</pre> führt zu einem Punkt, der einmal außen
     * um das LEDPanel herumläuft. Auf jedes der auf dem Pfad liegenden LEDs leuchtet dabei für 20 Millisekunden.
     *
     * @param path         Der Pfad, auf dem sich der laufende Punkt bewegen soll.
     * @param milliseconds Zeitraum, für den der laufende Punkt auf einer LED verweilt.
     * @param repetitions  Anzahl der Wiederholungen.
     */
    public void showRunningDot(Path path, int milliseconds, int repetitions) {
        for (int repetition = 0; repetition <= repetitions; repetition++) {
            for (int pathPosition : path.getLeds()) {
                onSingleLed(pathPosition);
                waitFor(milliseconds);
                offSingleLed(pathPosition);
            }
        }
    }

    /**
     * Diese Methode verhält sich genau wie {@link #showRunningDot(Path, int, int)}, allerdings werden mehrere laufende
     * Punkte gleichzeitig angezeigt. Die Anzeige endet, sobald der längste Pfad abgearbeitet wurde.
     *
     * @param path         Der Pfad, auf dem sich der laufende Punkt bewegen soll.
     * @param milliseconds Zeitraum, für den der laufende Punkt auf einer LED verweilt.
     * @param repetitions  Anzahl der Wiederholungen.
     **/
    /*
    public void showRunningDots(Path[] path, int milliseconds, int repetitions) {
        int longestPath = 0;
        for (Path currentPath : path) {
            longestPath = Math.max(longestPath, currentPath.size());
        }
        System.out.println(Arrays.toString(path[0].getLeds()));
        System.out.println(Arrays.toString(path[1].getLeds()));

        int[][] ledsPerStep = new int[longestPath][0];
        for (Path currentPath : path) {
            for (int step = 0; step < currentPath.size(); step++) {
                ledsPerStep[step] = appendLed(ledsPerStep[step], currentPath.getLeds()[step]);
            }
        }

        for (int repetition = 1; repetition <= repetitions; repetition++) {
            for (int[] step : ledsPerStep) {
                for (int led : step) {
                    onSingleLed(led);
                }
                waitFor(milliseconds);
                for (int led : step) {
                    offSingleLed(led);
                }
            }
        }
    }

    private int[] appendLed(int[] stepArray, int led) {
        int[] newStepArray = new int[stepArray.length + 1];
        System.arraycopy(stepArray, 0, newStepArray, 0, stepArray.length);

        newStepArray[stepArray.length] = led;

        return newStepArray;
    }
    */
    public void showRunningDots(Path[] path, int milliseconds, int repetitions) {
        int longestPath = 0;
        for (Path currentPath : path) {
            longestPath = Math.max(longestPath, currentPath.size());
        }

        int[][] dotsOfPaths = new int[path.length][];
        for (int singlePath = 0; singlePath < path.length; singlePath++) {
            dotsOfPaths[singlePath] = path[singlePath].getLeds();
        }

        for (int repetition = 1; repetition <= repetitions; repetition++) {
            for (int pathPosition = 0; pathPosition < longestPath; pathPosition++) {
                for (int[] ff : dotsOfPaths) {
                    if (ff.length > pathPosition) {
                        onSingleLed(ff[pathPosition]);
                    }
                }
                waitFor(milliseconds);
                for (int[] ff : dotsOfPaths) {
                    if (ff.length > pathPosition) {
                        offSingleLed(ff[pathPosition]);
                    }
                }
            }
        }
    }

    /**
     * Ein String der Größe 40 * 8 wird für eine bestimmte Zeitdauer angezeigt. An den Stellen, an denen der String eine
     * Raute "#" enthält, wird die entsprechende LED eingeschaltet, bei anderen Zeichen bleibt die LED ausgeschaltet.
     * Strings dürfen kleiner oder größer sein als 40 * 8. Größere Strings werden dann nur teilweise angezeigt.
     * <p>
     * Die Klasse {@link StringImage} kann genutzt werden, um Text in passende große Strings zu verwandeln, für jeden
     * Buchstaben wird dabei ein String der Größe 8 * 8 erzeugt.
     * <br>
     * Beispielsweise führt der Aufruf von
     * <pre>{@code showString(new StringImage("Hallo").toString(), 2000)}</pre> zu einer Ausgabe von "Hallo" auf dem
     * LED-Panel, die für 2 Sekunden lang angezeigt wird.
     *
     * @param string       Das anzuzeigende StringImage.
     * @param milliseconds Zeitdauer der Anzeige.
     */
    public void showString(String string, int milliseconds) {
    }

    /**
     * Diese Methode verhält sich ähnlich wie {@link #showString(String, int)}, allerdings wird der String über das
     * Panel bewegt. Der String erscheint am rechten Rand des Panels und scrollt zum linken Rand durch, bis er ganz
     * verschwunden ist.
     *
     * <br>
     * Beispielsweise führt der Aufruf von
     * <pre>{@code showMovingString(new StringImage("Herzlich Willkommen!").movingStrings(), 20, 2)}</pre> zur Ausgabe
     * eines Lauftextes, der 2-mal durchläuft. Jedes einzelne Bild ist dabei für 20 Millisekunden zu sehen.
     *
     * @param strings      Der anzuzeigende Lauftext.
     * @param milliseconds Zeitdauer der Anzeige eines einzelnen Bildes.
     * @param repetitions  Anzahl der Wiederholungen des kompletten Durchlaufs.
     */
    public void showMovingString(String[] strings, int milliseconds, int repetitions) {
    }

    /**
     * Hier kann eine Wartezeit konfiguriert werden. Das Panel ändert seinen Zustand in dieser Zeit nicht.
     *
     * @param milliseconds Wartezeit in Millisekunden.
     */
    public void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {
        }
    }
}
