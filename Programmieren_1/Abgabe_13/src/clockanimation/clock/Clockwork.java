package clockanimation.clock;

public class Clockwork {
    private Time startTime;
    private long startTimestamp;
    private int speedFactor;

    Clockwork(Time startTime, int speedFactor) {
        this.startTime = startTime;
        this.speedFactor = speedFactor;

        startTimestamp = System.currentTimeMillis();
    }

    Time retrieveCurrentTime() {
        long timeDelta = System.currentTimeMillis() - startTimestamp;
        return new Time(startTime.asMilliseconds() + (timeDelta * speedFactor));
    }
}
