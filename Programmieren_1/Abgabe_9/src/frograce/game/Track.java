package frograce.game;

class Track {
    private final String name;
    private final int distanceInCm;
    private final Frog[] frogs;

    Track(String name, int distanceInCm, Frog[] frogs) {
        this.name = name;
        this.distanceInCm = distanceInCm;
        this.frogs = frogs;
    }

    int getDistanceInCm() {
        return distanceInCm;
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder("\n");
        display.append(name).append(": ").append(distanceInCm).append(" Zentimeter\n\n");

        createEdgeLine(display);

        for (Frog frog : frogs) {
            display.append("        ").append(frog.toString()).append("\n\n");
        }

        createEdgeLine(display);

        return display.toString();
    }

    private void createEdgeLine(StringBuilder display) {
        display.append("\n Start |");

        for (int coveredTrack = 0; coveredTrack < distanceInCm; coveredTrack += 10) {
            display.append("-");
        }

        display.append("| Ziel\n\n\n");
    }

}
