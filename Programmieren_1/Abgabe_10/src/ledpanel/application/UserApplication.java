package ledpanel.application;

import ledpanel.controller.API;

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
            api.addressSingleLed(i);
            api.waitFor(10);
        }
        for (int i = 0; i < 320; i++) {
            api.addressSingleLed(i);
            api.waitFor(10);
        }
    }
}
