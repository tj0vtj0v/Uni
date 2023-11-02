package gameoflife;

class PetriDish {
    private final Cell[][] cells;
    private int lines;
    private int columns;
    private int generationNumber;

    PetriDish(int lines, int columns, double probabilityToLive) {
        // this.lines = lines;
        // this.columns = columns;
        cells = new Cell[lines][columns];
        generationNumber = 1;

        createAllCells(probabilityToLive);
        addNeighborsToAllCells();
    }

    private void createAllCells(double probabilityToLive) {
        // for (int line = 0; line < lines; line++) {
        // for (int column = 0; column < columns; column++) {
        for (int line = 0; line < cells.length; line++) {
            for (int column = 0; column < cells[line].length; column++) {
                cells[line][column] = new Cell(probabilityToLive);
            }
        }
    }

    private void addNeighborsToAllCells() {
        // for (int line = 0; line < lines; line++) {
        // for (int column = 0; column < columns; column++) {
        for (int line = 0; line < cells.length; line++) {
            for (int column = 0; column < cells[line].length; column++) {
                addNeighborsToCellIn(line, column);
            }
        }
    }

    private void addNeighborsToCellIn(int line, int column) {
        for (int deltaLine = -1; deltaLine <= 1; deltaLine++) {
            for (int deltaColumn = -1; deltaColumn <= 1; deltaColumn++) {
                if (!(deltaLine == 0 && deltaColumn == 0) && withinBoundaries(line + deltaLine, column + deltaColumn)) {
                    cells[line][column].addNeighbor(cells[line + deltaLine][column + deltaColumn]);
                }
            }
        }
    }

    private boolean withinBoundaries(int line, int column) {
        // return (line < lines && line >= 0 && column < columns && column >= 0);
        return (line < cells.length && line >= 0 && column < cells[line].length && column >= 0);
    }

    void nextGeneration() {
        countLivingNeighborsOfAllCells();
        applyRulesToAllCells();
        generationNumber++;
    }

    private void countLivingNeighborsOfAllCells() {
        for (Cell[] lineOfCells : cells) {
            for (Cell cell : lineOfCells) {
                cell.countLivingNeighbors();
            }
        }
    }

    private void applyRulesToAllCells() {
        for (Cell[] lineOfCells : cells) {
            for (Cell cell : lineOfCells) {
                cell.applyRules();
            }
        }
    }

    String asString(boolean showHeadline) {
        String output = "";

        if (showHeadline) {
            output = "Generation: " + generationNumber + "\n";
        }

        for (Cell[] lineOfCells : cells) {
            for (Cell cell : lineOfCells) {
                output += cell.asString();
            }
            output += "\n";
        }

        return output;
    }
}
