package halloween;

public class Halloween {
    public static void main(String[] args) {
        Creature[] creatures = new Creature[3];
        creatures[0] = new Vampire("Dracula");
        creatures[1] = new Vampire("Nosferatu");
        creatures[2] = new Ghost("Caspar");

        for (Creature creature : creatures) {
            System.out.println(creature.jumpScare());
        }
    }
}
