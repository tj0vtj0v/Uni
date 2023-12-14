package clockanimation.clock;

import clockanimation.gameview.Animations;

import java.time.LocalTime;

public class Clock {
    Animations animations;
    Clockwork clockwork;

    Clock() {
        animations = new Animations();
        clockwork = new Clockwork(new Time(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()), 1);
        //clockwork = new Clockwork(new Time(1, 28, -55), 1);
    }

    public static void main(String[] args) {
        new Clock().startClock();
    }

    private void startClock() {
        Time timeAtMoment;
        while (true) {
            timeAtMoment = clockwork.retrieveCurrentTime();
            animations.showClock(timeAtMoment.asFormattedString(), timeAtMoment.hoursAsDegrees(), timeAtMoment.minutesAsDegrees(), timeAtMoment.secondsAsDegrees());
        }
    }
}
