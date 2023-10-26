package halloween;

public abstract class Creature {
    protected String name;
    protected String color;

    Creature(String name, String color) {
        this.name = name;
        this.color = color;
    }

    protected String jumpScare() {
        return name + ", " + color + ": ";
    }
}
