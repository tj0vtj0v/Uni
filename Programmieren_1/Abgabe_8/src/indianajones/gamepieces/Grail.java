package indianajones.gamepieces;

public class Grail extends GamePiece {
    public Grail(int lines, int columns) {
        super(lines, columns);

        this.line = (int) (Math.random() * (lines));
        this.column = (int) (Math.random() * (columns));

        letter = 'G';
    }

    public void beInvisible() {
        letter = ' ';
    }
}
