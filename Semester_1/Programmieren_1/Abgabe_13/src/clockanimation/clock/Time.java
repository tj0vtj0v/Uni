package clockanimation.clock;

public class Time {
    private final int hours;
    private final int minutes;
    private final int seconds;

    Time(int hours, int minutes, int seconds) {
        if ((hours < 0 || hours > 23) || (minutes < 0 || minutes > 59) || (seconds < 0 || seconds > 59)) {
            throw new IllegalArgumentException(hours + ", " + minutes + ", " + seconds);
        }

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    Time(long milliseconds) {
        this(hoursFromMilliseconds(milliseconds) % 24, minutesFromMilliseconds(milliseconds) % 60, secondsFromMilliseconds(milliseconds) % 60);
    }

    private static int hoursFromMilliseconds(long milliseconds) {
        return (int) (milliseconds / 1000 / 60 / 60);
    }

    private static int minutesFromMilliseconds(long milliseconds) {
        return (int) (milliseconds / 1000 / 60);
    }

    private static int secondsFromMilliseconds(long milliseconds) {
        return (int) (milliseconds / 1000);
    }

    int secondsAsDegrees() {
        return (int) (360 * (seconds / 60f));
    }

    int minutesAsDegrees() {
        return (int) (360 * (minutes / 60f));
    }

    int hoursAsDegrees() {
        return (int) (360 * (hours / 12f) + minutes / 2f);
    }

    String asFormattedString() {
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }

    long asMilliseconds() {
        long seconds = this.seconds + minutes * 60L + hours * 3600L;
        return seconds * 1000;
    }
}
