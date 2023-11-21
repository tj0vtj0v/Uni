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
            api.waitFor(8);
        }
        for (int i = 0; i < 320; i++) {
            api.switchSingleLed(i);
            api.waitFor(5);
        }

        api.showRunningDot(new Path(0, 39, 319, 280, 0), 20, 1);

        Path[] paths = new Path[2];
        paths[0] = new Path(0, 39, 319, 280, 0);
        paths[1] = new Path(278, 78, 41, 241, 278);
        api.showRunningDots(paths, 20, 1);
    }
}
