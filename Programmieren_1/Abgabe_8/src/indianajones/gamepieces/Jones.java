package indianajones.gamepieces;

import indianajones.game.GameView;

import static java.awt.event.KeyEvent.*;

public class Jones extends GamePiece {
    private boolean grail;

    public Jones(int lines, int columns) {
        super(lines, columns);

        this.column = 0;
        this.line = lines / 2;

        letter = 'J';
        grail = false;
    }

    public boolean hasSamePositionAs(GamePiece gamePiece) {
        return column == gamePiece.column && line == gamePiece.line;
    }

    @Override
    public void move() {
        if (GameView.keyPressed(VK_UP)) {
            line = line > 0 ? line - 1 : line;
        }
        if (GameView.keyPressed(VK_DOWN)) {
            line = line < (lines - 1) ? line + 1 : line;
        }
        if (GameView.keyPressed(VK_LEFT)) {
            column = column > 0 ? column - 1 : column;
        }
        if (GameView.keyPressed(VK_RIGHT)) {
            column = column < (columns - 1) ? column + 1 : column;
        }
    }
}
