package sudoku_game.sudoku.model;

import static sudoku_game.sudoku.view.CONSTANSTS.GRID_SIZE;

public class SudokuGridNumbers {
    /* Helper functions for Sudoku Solver
     they are used for "moving" in grid when the solving algorythm needs to loop through numbers in
     row, column and square and compare them.
     *********************************************************************************************************/
    protected static boolean numIsValid(int num, int grid[][], int rowIndex, int columnIndex) {
        return !containsInRow(num, grid, rowIndex, columnIndex)
                && !containsInColumn(num, grid, rowIndex, columnIndex)
                && !containsInSquare(num, grid, rowIndex, columnIndex);
    }

    protected static boolean containsInRow(int num, int grid[][], int rowIndex, int columnIndex) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if (grid[rowIndex][i] == num && i != columnIndex) return true;
        }
        return false;
    }

    protected static boolean containsInColumn(int num, int grid[][], int rowIndex, int columnIndex) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if (grid[i][columnIndex] == num && i != rowIndex) return true;
        }
        return false;
    }

    protected static boolean containsInSquare(int num, int grid[][], int rowIndex, int columnIndex) {
        int sqRow = rowIndex - rowIndex % 3;
        int sqCol = columnIndex - columnIndex % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[sqRow + i][sqCol + j] == num && sqRow + i != rowIndex && sqCol + j != columnIndex) return true;
            }
        }
        return false;
    }
}
