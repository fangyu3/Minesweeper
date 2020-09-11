package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private boolean isMine;
    private boolean isMarked;
    private boolean isExplored;
    private int numMinesAround;
    private List<Cell> neighbourCells;
    private String symbol;

    public Cell () {
        this.isMine = false;
        this.isMarked = false;
        this.isExplored = false;
        this.symbol = ".";
        this.numMinesAround = 0;
        this.neighbourCells = new ArrayList<>();
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public void setExplored(boolean explored) {
        isExplored = explored;
    }

    public int getNumMinesAround() {
        return numMinesAround;
    }

    public void setNumMinesAround(int numMinesAround) {
        this.numMinesAround = numMinesAround;
    }

    public List<Cell> getNeighbourCells() {
        return neighbourCells;
    }

    public void setNeighbourCells(List<Cell> neighbourCells) {
        this.neighbourCells = neighbourCells;
    }
}
