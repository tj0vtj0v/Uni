package ledpanel.application;

import ledpanel.controller.API;
import ledpanel.controller.Path;

public class UserApplication {

    public static void main(String[] args) {
        UserApplication userApplication = new UserApplication();
        userApplication.lightShow();
    }

    private final API api;

    private UserApplication() {
        api = new API();
    }

    private void lightShow() {
        for (int i = 0; i < 320; i++) {
            api.switchSingleLed(i);
            api.waitFor(5);
        }

        Path[] paths = new Path[8];
        for (int j = 0; j < 8; j++) {
            paths[j] = new Path(j * 40, ((1 + j) * 40) - 1);
        }
        api.showRunningDots(paths, 80, 0);

        api.showRunningDot(new Path(0, 39, 319, 280, 0), 20, 0);

        paths = new Path[3];
        paths[0] = new Path(0, 39, 319, 280, 0);
        paths[1] = new Path(40, 79);
        paths[2] = new Path(278, 78, 41, 241, 278);
        api.showRunningDots(paths, 30, 0);
    }
}
