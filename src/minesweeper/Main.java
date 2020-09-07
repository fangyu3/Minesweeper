package minesweeper;

import java.util.Scanner;
import java.util.Random;

public class Main {

    private static final int fieldSize = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(81);
        String[][] mineField = new String[fieldSize][fieldSize];
        System.out.print("How many mines doe you want on the field?");
        int numMines = scanner.nextInt();
        scanner.nextLine();

        // Initialize field
        for (int i=0; i<=fieldSize*fieldSize-1; i++) {
            int row = i/fieldSize;
            int col = i%fieldSize;
            mineField[row][col] = ".";
        }

        // Add mines
        for (int i=1; i<=numMines; i++) {
            int minePos = random.nextInt();
            int row = i/fieldSize;
            int col = i%fieldSize;

            if (mineField[row][col].equals("."))
                mineField[row][col] = "X";
            else
                i--;
        }

        // Get number of mines around empty cell
        for (int row=0; row<fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                if (mineField[row][col].equals(".")) {
                    mineField[row][col] =
                            checkMines(row,col,mineField)==0?
                                    ".":checkMines(row,col,mineField) + "";
                }
            }
        }


        // Print mineField
        for (int row=0; row<fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                System.out.print(mineField[row][col]);
            }
            System.out.println();
        }
    }

    public static int checkMines(int row, int col, String[][]mineField) {
        int topBound = row-1>0? row-1:0;
        int botBound = row+1<fieldSize? row+1:fieldSize-1;
        int leftBound = col-1>0? col-1:0;
        int rightBound = col+1<fieldSize? col+1:fieldSize-1;

        int numMines = 0;

        for (int i=topBound; i<=botBound; i++) {
            for (int j=leftBound; j<=rightBound; j++) {
                if (mineField[i][j].equals("X"))
                    numMines ++;
            }
        }
        return numMines;
    }
}
