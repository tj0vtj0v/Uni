package ledpanel.controller;

import ledpanel.hardware.LEDPanel;

class MovementController extends LedController {

    MovementController(LEDPanel ledPanel) {
        super(ledPanel);
    }

    void apiShowRunningDots(Path[] path, int milliseconds, int repetitions) {
        int longestPath = calculateLongestPath(path);
        int[][] ledsPerPathStep = ledPathBySteps(path);


        for (int repetition = 1; repetition <= repetitions; repetition++) {
            for (int stepIndex = 0; stepIndex < longestPath; stepIndex++) {
                activateLedsByPathStep(ledsPerPathStep, stepIndex);
                waitFor(milliseconds);
                addressAllLeds(false);
            }
        }
    }

    void apiShowMovingString(String[] strings, int milliseconds, int repetitions) {
        for (int repetition = 1; repetition <= repetitions; repetition++) {
            for (String stringAtTick : strings) {
                new StringController(getLedPanel(), stringAtTick).displayString(milliseconds);
            }
        }
    }

    private int calculateLongestPath(Path[] paths) {
        int longestPath = 0;
        for (Path currentPath : paths) {
            longestPath = Math.max(longestPath, currentPath.size());
        }

        return longestPath;
    }

    private int[][] ledPathBySteps(Path[] paths) {
        int[][] dotsOfPaths = new int[paths.length][];

        for (int singlePath = 0; singlePath < paths.length; singlePath++) {
            dotsOfPaths[singlePath] = paths[singlePath].getLeds();
        }

        return dotsOfPaths;
    }

    private void activateLedsByPathStep(int[][] ledsPerPathStep, int stepIndex) {
        for (int[] ledsPerStep : ledsPerPathStep) {
            if (ledsPerStep.length > stepIndex) {
                addressLed(ledsPerStep[stepIndex], true);
            }
        }
    }
}
