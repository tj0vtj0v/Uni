package indianajones.game;

import indianajones.gamepieces.*;

public class Game extends GameElements {
    private boolean gameOver;
    private boolean jonesWonTheGame;

    public Game(int lines, int columns, int numberOfSnakes) {
        super(lines, columns, numberOfSnakes);

        gameOver = false;
        jonesWonTheGame = false;
    }

    public void nextIteration() {
        if (!gameOver) {
            moveAllGamePieces();
            updateGameLogic();
        }
    }

    private void moveAllGamePieces() {
        for (GamePiece gamePiece : allGamePieces) {
            gamePiece.move();
        }
    }

    private void updateGameLogic() {
        if (jones.hasSamePositionAs(grail)) {
            grail.beInvisible();
        }
        if (jones.hasSamePositionAs(exit) && grail.getLetter() == ' ') {
            jonesWonTheGame = true;
            gameOver = true;
        }
        for (Snake snake : this.snakes) {
            if (jones.hasSamePositionAs(snake)) {
                gameOver = true;
            }
        }
    }

    @Override
    char charAtPosition(int line, int column) {
        if (!gameOver) {
            return super.charAtPosition(line, column);
        } else if (jonesWonTheGame) {
            return 'J';
        } else {
            return 'S';
        }
    }
}
