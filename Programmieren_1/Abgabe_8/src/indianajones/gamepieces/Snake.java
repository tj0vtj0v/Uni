package indianajones.gamepieces;

public class Snake extends GamePiece {
    private final Jones jones;

    public Snake(int lines, int columns, Jones jones) {
        super(lines, columns);

        this.column = columns - 1;
        this.line = (int) (Math.random() * lines);
        this.jones = jones;

        letter = 'S';
    }

    @Override
    public void move() {
        if (Math.random() >= 0.5 && jones.getLine() != line) {
            line = jones.getLine() > line ? line + 1 : line - 1;
        } else if (jones.getColumn() != column) {
            column = jones.getColumn() > column ? column + 1 : column - 1;
        }
    }
}
