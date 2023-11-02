package indianajones.game;

import indianajones.gamepieces.*;

class GameElements extends GameField {
    final Jones jones;
    final Grail grail;
    final Exit exit;
    final Snake[] snakes;
    GamePiece[] allGamePieces;

    GameElements(int lines, int columns, int numberOfSnakes) {
        super(lines, columns, numberOfSnakes);

        int numberOfAllPieces = this.numberOfSnakes + 3;
        allGamePieces = new GamePiece[numberOfAllPieces];

        jones = new Jones(this.lines, this.columns);
        grail = new Grail(this.lines, this.columns);
        exit = new Exit(this.lines, this.columns);
        snakes = new Snake[this.numberOfSnakes];

        allGamePieces[0] = jones;
        allGamePieces[1] = grail;
        allGamePieces[2] = exit;

        for (int i = 0; i < this.numberOfSnakes; i++) {
            snakes[i] = new Snake(this.lines, this.columns, jones);
            allGamePieces[i + 3] = snakes[i];
        }
    }

    @Override
    char charAtPosition(int line, int column) {
        for (GamePiece gamePiece : allGamePieces) {
            if (gamePiece.getLine() == line && gamePiece.getColumn() == column) {
                return gamePiece.getLetter();
            }
        }

        return super.charAtPosition(line, column);
    }

}
