package minesweeper;

import java.util.Scanner;
import java.util.Random;

public class Main {

    private static final int fieldSize = 9;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Cell[][] mineField = new Cell[fieldSize][fieldSize];

        initializeMineField(mineField);

        while (!gameEnd(mineField)) {
            // Print mineField
            printMineField(mineField);
            System.out.println("Set/delete mines marks (x and y coordinates): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            scanner.nextLine();
            addMark(y-1,x-1,mineField);
        }

        System.out.println("Congratulations! You found all mines!");
    }

    public static void initializeMineField(Cell[][]mineField) {

        System.out.print("How many mines do you want on the field?");
        int numMines = scanner.nextInt();
        scanner.nextLine();

        // Initialize field
        for (int i=0; i<=fieldSize*fieldSize-1; i++) {
            int row = i/fieldSize;
            int col = i%fieldSize;
            mineField[row][col] = new Cell();
        }

        Random random = new Random(fieldSize*fieldSize);
        // Add mines
        for (int i=1; i<=numMines; i++) {
            int minePos = random.nextInt();
            int row = i/fieldSize;
            int col = i%fieldSize;

            if (!mineField[row][col].isMine()) {
                mineField[row][col].setMine(true);
            }
            else
                i--;
        }

        // Get number of mines around empty cell
        for (int row=0; row<fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                if (!mineField[row][col].isMine()) {
                    mineField[row][col].setSymbol(checkMines(row,col,mineField)==0?
                            ".":checkMines(row,col,mineField) + "");
                }
            }
        }
    }

    public static void addMark(int row, int col, Cell[][]mineField) {
        Cell cell = mineField[row][col];

        if (cell.isMarked()) {
            cell.setMarked(false);
            cell.setSymbol(checkMines(row,col,mineField)==0?
                    ".":checkMines(row,col,mineField) + "");
        }
        else {
            if (!cell.getSymbol().equals(".")) {
                System.out.println("There is a number here!");
            }
            else {
                cell.setMarked(true);
                cell.setSymbol("*");
            }
        }
    }

    public static int checkMines(int row, int col, Cell[][]mineField) {
        int topBound = row-1>0? row-1:0;
        int botBound = row+1<fieldSize? row+1:fieldSize-1;
        int leftBound = col-1>0? col-1:0;
        int rightBound = col+1<fieldSize? col+1:fieldSize-1;

        int numMines = 0;

        for (int i=topBound; i<=botBound; i++) {
            for (int j=leftBound; j<=rightBound; j++) {
                if (mineField[i][j].isMine())
                    numMines ++;
            }
        }
        return numMines;
    }

    public static boolean gameEnd(Cell[][] mineField) {
        for (int row=0; row<fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                Cell cell = mineField[row][col];
                if (cell.isMarked()) {
                    if (!cell.isMine())
                        return false;
                }

                if (cell.isMine()) {
                    if (!cell.isMarked()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void printMineField(Cell[][] mineField) {

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
