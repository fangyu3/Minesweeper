package minesweeper;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class MineField {

    public final int fieldSize;
    private Cell[][] mineField;
    private boolean mineGenerated;
    private int numMines;

    public MineField() {
        fieldSize = 9;
        mineField = new Cell[fieldSize][fieldSize];
        mineGenerated = false;
        numMines = 0;
    }

    public Cell[][] getMineField() {
        return this.mineField;
    }

    public boolean isMineGenerated() {
        return mineGenerated;
    }

    public void setMineGenerated(boolean mineGenerated) {
        this.mineGenerated = mineGenerated;
    }

    public int getNumMines() {
        return numMines;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many mines do you want on the field?");
        this.numMines = scanner.nextInt();
        scanner.nextLine();

        // Initialize field
        for (int i=0; i<=fieldSize*fieldSize-1; i++) {
            int row = i/fieldSize;
            int col = i%fieldSize;
            mineField[row][col] = new Cell();
        }
    }

    public void generateMines(int x,int y) {
        System.out.println(x + " " + y);
        Random random = new Random();
        // Add mines
        // Mines can be added anywhere except for the location user entered
        for (int i=1; i<=numMines; i++) {
            int minePos = random.nextInt(fieldSize*fieldSize);
            int row = minePos/fieldSize;
            int col = minePos%fieldSize;

            if (!mineField[row][col].isMine() && mineField[row][col] != mineField[y-1][x-1]) {
                mineField[row][col].setMine(true);
            }
            else
                i--;
        }

        // Get number of mines around empty cell
        for (int row=0; row<fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                Cell cell = mineField[row][col];
                if (!cell.isMine()) {
                    cell.setNumMinesAround(getNumberOfMinesAroundCell(row,col));
                }
            }
        }
        mineGenerated = true;
    }

    public int getNumberOfMinesAroundCell(int row, int col) {
        Cell cell = mineField[row][col];
        int topBound = row-1>0? row-1:0;
        int botBound = row+1<fieldSize? row+1:fieldSize-1;
        int leftBound = col-1>0? col-1:0;
        int rightBound = col+1<fieldSize? col+1:fieldSize-1;

        int numMines = 0;

        // Check for mines around empty cell as well as set neighbour cells
        for (int i=topBound; i<=botBound; i++) {
            for (int j=leftBound; j<=rightBound; j++) {
                if (mineField[i][j].isMine())
                    numMines ++;

            if (mineField[i][j] != cell)
                cell.getNeighbourCells().add(mineField[i][j]);
            }
        }
        return numMines;
    }

    public void freeCells(Cell cell) {
        Queue<Cell> cells = new LinkedList<>();
        cells.offer(cell);

        while (!cells.isEmpty()) {
            Cell exploredCell = cells.poll();
            exploredCell.setExplored(true);
            exploredCell.setMarked(false);
            String symbol = exploredCell.getNumMinesAround()==0?
                                "/":exploredCell.getNumMinesAround() + "";

            exploredCell.setSymbol(symbol);

            if (exploredCell.getNumMinesAround() == 0) {
                for (Cell neighbourCell : exploredCell.getNeighbourCells()) {
                    if (!neighbourCell.isExplored()) {
                        cells.offer(neighbourCell);
                    }
                }
            }
        }

    }

    public void revealMines() {
        for (int row=0; row<fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                Cell cell = mineField[row][col];
                if (cell.isMine()) {
                    cell.setSymbol("X");
                }
            }
        }
        display();
    }

    public void display() {

        // Display top boarder + X axis
        for (int i=0; i<fieldSize; i++) {
            if (i==0)
                System.out.print(" |");

            System.out.print(i+1);

            if (i==fieldSize-1)
                System.out.print("|");
        }

        System.out.println();

        for (int i=0; i<fieldSize; i++) {
            if (i==0)
                System.out.print("-|");

            System.out.print("-");

            if (i==fieldSize-1)
                System.out.print("|");
        }

        System.out.println();

        // Display actual mine field + Y axis
        for (int row=0; row<fieldSize; row++) {
            for (int col=0; col<fieldSize; col++) {
                if (col == 0)
                    System.out.print((row+1) + "|");

                System.out.print(mineField[row][col].getSymbol());

                if (col == fieldSize-1)
                    System.out.print("|");
            }
            System.out.println();
        }

        // Display bottom border
        for (int i=0; i<fieldSize; i++) {
            if (i==0)
                System.out.print("-|");

            System.out.print("-");

            if (i==fieldSize-1)
                System.out.print("|");
        }
        System.out.println();
    }
}
