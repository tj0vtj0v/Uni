package halloween;

public class Vampire extends Creature {
    Vampire(String name) {
        super(name, "schwarz");
    }

    @Override
    protected String jumpScare() {
        return super.jumpScare() + "AAAAHHHH!";
    }

    private String bite() {
        return "-> Beißt zu.";
    }
}
