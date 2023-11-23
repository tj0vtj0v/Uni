package ledpanel.application;

import ledpanel.controller.API;
import ledpanel.controller.Path;

public class MyUserApplication {

    public static void main(String[] args) {
        MyUserApplication userApplication = new MyUserApplication();
        userApplication.lightShow();
    }

    private final API api;

    private MyUserApplication() {
        api = new API();
    }

    private void lightShow() {/*
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
        api.showRunningDots(paths, 3, 0);
        */
        Path path1 = new Path(40, 0, 8);
        Path path2 = new Path(279, 319, 311);
        api.showRunningDots(new Path[]{path1, path2}, 500, 1);

        Path[] paths = new Path[8];
        paths[0] = new Path(0, 39, 0);
        paths[1] = new Path(40, 79, 40);
        paths[2] = new Path(0, 39);
        paths[3] = new Path(40, 79);
        paths[4] = new Path(0, 39);
        paths[5] = new Path(40, 79);
        paths[6] = new Path(0, 39);
        paths[7] = new Path(40, 79);
        long time = System.currentTimeMillis();
        api.showRunningDots(paths, 10, 1);
        System.out.println(System.currentTimeMillis() - time);
    }
}
