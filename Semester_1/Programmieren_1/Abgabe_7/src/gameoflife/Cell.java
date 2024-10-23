package gameoflife;

class Cell {
    private boolean alive;
    private int livingNeighbors;
    private final Cell[] neighbors;

    Cell(double probabilityToLive) {
        neighbors = new Cell[8];
        alive = Math.random() < probabilityToLive;
    }

    void addNeighbor(Cell neighbor) {
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] == null) {
                neighbors[i] = neighbor;
                break;
            }
        }
    }

    void countLivingNeighbors() {
        livingNeighbors = 0;

        for (Cell cell : neighbors) {
            if (cell != null && cell.alive) {
                livingNeighbors++;
            }
        }
    }

    void applyRules() {
        switch (livingNeighbors) {
            case 3:
                alive = true;
                break;
            case 2:
                break;
            default:
                alive = false;
        }
    }

    String asString() {
        return alive ? "X " : "  ";
    }
}
