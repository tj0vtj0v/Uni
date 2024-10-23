package indianajones.game;

public class GameField {
    final int lines;
    final int columns;
    final int numberOfSnakes;

    GameField(int lines, int columns, int numberOfSnakes) {
        this.lines = lines;
        this.columns = columns;
        this.numberOfSnakes = numberOfSnakes;
    }

    @Override
    public String toString() {
        String output = "";

        for (int line = 0; line < lines; line++) {
            for (int column = 0; column < columns; column++) {
                output += charAtPosition(line, column);
            }
            output += "\n";
        }

        return output;
    }

    char charAtPosition(int line, int column) {
        return ' ';
    }
}
