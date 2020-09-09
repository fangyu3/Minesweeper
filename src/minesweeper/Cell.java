package minesweeper;

public class Cell {
    private boolean isMine;
    private boolean isMarked;
    private String symbol;

    public Cell () {
        this.isMine = false;
        this.isMarked = false;
        this.symbol = ".";
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
}
