package halloween;

abstract class Creature {
    private final String name;
    private final String color;

    Creature(String name, String color) {
        this.name = name;
        this.color = color;
    }

    protected String jumpScare() {
        return name + ", " + color + ": ";
    }
}
