package ledpanel.application;

import ledpanel.controller.API;
import ledpanel.controller.Path;
import ledpanel.controller.StringImage;

public class MyUserApplication {

    public static void main(String[] args) {
        MyUserApplication userApplication = new MyUserApplication();
        userApplication.lightShow();
    }

    private final API api;

    private MyUserApplication() {
        api = new API();
    }

    private void lightShow() {
        api.showMovingString(new StringImage("Herzlich Willkommen!").movingStrings(), 20, 1);
        api.waitFor(250);

    }
}
