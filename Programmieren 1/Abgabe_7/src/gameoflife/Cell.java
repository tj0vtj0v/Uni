package gameoflife;

class Cell {
    private boolean alive;
    private int livingNeighbors;
    private final Cell[] neighbors;

    Cell(double probabilityToLive) {
        this.neighbors = new Cell[8];
        this.alive = Math.random() < probabilityToLive;
    }

    void addNeighbor(Cell neighbor) {
        for (int i = 0; i < this.neighbors.length; i++) {
            if (neighbors[i] == null) {
                neighbors[i] = neighbor;
                break;
            }
        }
    }

    void countLivingNeighbors() {
        this.livingNeighbors = 0;

        for (Cell cell : this.neighbors) {
            if (cell != null && cell.alive) {
                this.livingNeighbors++;
            }
        }
    }

    void applyRules() {
        switch (this.livingNeighbors) {
            case 3:
                this.alive = true;
                break;
            case 2:
                break;
            default:
                this.alive = false;
        }
    }

    String asString() {
        return this.alive ? "X " : "  ";
    }
}
