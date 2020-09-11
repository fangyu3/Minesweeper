package minesweeper;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean playerWin = false;

    public static void main(String[] args) {

        MineField mineField = new MineField();

        mineField.initialize();

        while (!gameEnd(mineField)) {
            // Print mineField
            mineField.display();
            System.out.println("Set/unset mines marks or claim a cell as free: ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String action = scanner.next();
            scanner.nextLine();

            if (action.equals("free")) {
                if (!mineField.isMineGenerated()) {
                    mineField.generateMines(x,y);
                }
                exploreCell(x,y,mineField);
            }

            if (action.equals("mine")) {
                addMark(x,y, mineField);
            }
        }

        if (!playerWin) {
            mineField.revealMines();
            System.out.println("You stepped on a mine and failed!");
            return;
        }

        System.out.println("Congratulations! You found all mines!");
        return;
    }

    public static void addMark(int x, int y, MineField mineField) {
        int row = y-1;
        int col = x-1;
        Cell cell = mineField.getMineField()[row][col];

        if (cell.isMarked()) {
            cell.setMarked(false);
            cell.setSymbol(".");
        }
        else {
            cell.setMarked(true);
            cell.setSymbol("*");
        }
    }

    public static void exploreCell(int x, int y, MineField mineField) {
        int row = y-1;
        int col = x-1;
        Cell cell = mineField.getMineField()[row][col];

        if (cell.isMine()) {
            cell.setExplored(true);
            cell.setMarked(false);
        }
        else if (cell.getNumMinesAround() > 0) {
            cell.setExplored(true);
            cell.setMarked(false);
            cell.setSymbol(cell.getNumMinesAround()+"");
        }
        else {
            mineField.freeCells(cell);
        }
    }

    public static boolean gameEnd(MineField mineField) {
        // Continue game if mines are not generated
        if (!mineField.isMineGenerated())
            return false;

        int numExploredCells = 0;

        // Player win case #1: player checks all cells that are not mines
        for (int row=0; row<mineField.fieldSize; row++) {
            for (int col = 0; col < mineField.fieldSize; col++) {
                Cell cell = mineField.getMineField()[row][col];

                if (cell.isMine() && cell.isExplored()) {
                    playerWin = false;
                    return true;
                }

                if (!cell.isMine() && cell.isExplored())
                    numExploredCells++;
            }
        }

        int totalCells = mineField.fieldSize*mineField.fieldSize;

        if (numExploredCells == totalCells-mineField.getNumMines()) {
            playerWin = true;
            return true;
        }

        // Player win case #2: player flags all cells that are mines
        for (int row=0; row<mineField.fieldSize; row++) {
            for (int col = 0; col < mineField.fieldSize; col++) {
                Cell cell = mineField.getMineField()[row][col];

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
        playerWin = true;
        return true;
    }
}
