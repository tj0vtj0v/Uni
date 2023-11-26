package ledpanel.controller;

import ledpanel.hardware.LEDPanel;

class PanelController {
    private final LEDPanel ledPanel;
    byte[][] matrices;

    PanelController(LEDPanel ledPanel) {
        this.ledPanel = ledPanel;
        matrices = new byte[][]{
                this.ledPanel.matrix0,
                this.ledPanel.matrix1,
                this.ledPanel.matrix2,
                this.ledPanel.matrix3,
                this.ledPanel.matrix4
        };
    }

    LEDPanel getLedPanel() {
        return ledPanel;
    }
}
