package ledpanel.controller;

import ledpanel.hardware.*;

class StringController extends LedController {
    private final String[] ledLines;
    private final int[] displayLeds;


    StringController(LEDPanel ledPanel, String string) {
        super(ledPanel);
        this.ledLines = string.split("\n");

        trimStringImage();
        displayLeds = new int[calculateActivatedLeds()];
        collectWantedLeds();
    }

    private void trimStringImage() {
        for (int line = 0; line < ledLines.length; line++) {
            if (ledLines[line].length() > 40) {
                ledLines[line] = ledLines[line].substring(0, 40);
            }
        }
    }

    private int calculateActivatedLeds() {
        int activatedLeds = 0;

        for (String ledLine : ledLines) {
            activatedLeds += ledLine.replace(" ", "").replace("\n", "").length();
        }

        return activatedLeds;
    }

    private void collectWantedLeds() {
        for (int lineIndex = 0; lineIndex < ledLines.length; lineIndex++) {
            for (int columnIndex = 0; columnIndex < ledLines[lineIndex].length(); columnIndex++) {
                insertActiveLeds(lineIndex, columnIndex);
            }
        }
    }

    private void insertActiveLeds(int lineIndex, int columnIndex) {
        if (ledLines[lineIndex].charAt(columnIndex) == '#') {
            displayLeds[firstFreeIndex()] = lineIndex * 40 + columnIndex;
        }
    }

    private int firstFreeIndex() {
        for (int displayLedIndex = 0; displayLedIndex < displayLeds.length; displayLedIndex++) {
            if (displayLeds[displayLedIndex] == 0) {
                return displayLedIndex;
            }
        }

        return -1;
    }

    void displayString(int displayDuration) {
        addressLedCollection(displayLeds, true);
        waitFor(displayDuration);
        addressAllLeds(false);
    }
}
