package ledpanel.controller;

import ledpanel.hardware.LEDPanel;

class LedController extends PanelController{
    private static final int LEDS_PER_ROW = 40;
    private static final int LED_ROWS = 8;
    private static final int LED_COUNT = LEDS_PER_ROW * LED_ROWS;
    private final int[] allLeds;

    LedController(LEDPanel ledPanel) {
        super(ledPanel);

        allLeds = new int[320];
        for (int led = 0; led < LED_COUNT; led++) {
            allLeds[led] = led;
        }
    }

    void apiTestLEDPanel() {
        addressAllLeds(true);
        waitFor(500);
        addressAllLeds(false);
    }

    void apiShowLEDs(int[] ledCollection, int milliseconds) {
        addressLedCollection(ledCollection, true);
        waitFor(milliseconds);
        addressAllLeds(false);
    }

    void apiShowBlinkingLEDs(int[] leds, int millisecondsOn, int millisecondsOff, int repetitions) {
        for (int repetition = 1; repetition <= repetitions; repetition++) {
            addressLedCollection(leds, true);
            waitFor(millisecondsOn);
            addressAllLeds(false);
            waitFor(millisecondsOff);
        }
    }

    void addressLed(int ledIndex, boolean on) {
        int row = rowByIndex(ledIndex);
        int matrix = matrixByIndex(ledIndex);
        int column = columnOfMatrixByIndex(ledIndex);

        if (on) {
            matrices[matrix][row] = (byte) (matrices[matrix][row] | (byte) Math.pow(2, column));
        } else {
            matrices[matrix][row] = (byte) (matrices[matrix][row] & (byte) (255 - Math.pow(2, column)));
        }
    }

    void addressLedCollection(int[] ledIndices, boolean on) {
        for (int ledIndex : ledIndices) {
            addressLed(ledIndex, on);
        }
    }

    void addressAllLeds(boolean on) {
        addressLedCollection(allLeds, on);
    }

    private int matrixByIndex(int ledIndex) {
        return (ledIndex % LEDS_PER_ROW) / LED_ROWS;
    }

    private int rowByIndex(int ledIndex) {
        return ledIndex / LEDS_PER_ROW;
    }

    private int columnOfMatrixByIndex(int ledIndex) {
        return ledIndex % LEDS_PER_ROW % LED_ROWS;
    }

    void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {
        }
    }
}
