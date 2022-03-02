package sudoku_game.sudoku.model;

import javafx.scene.control.Button;

import static sudoku_game.sudoku.view.CONSTANSTS.GRID_SIZE;

public class SudokuSolver {

    //This function is ment to call when there is a wrong solutiuon. It finds 2 buttopns in grid, which values
    //don't fit to a solution.
    public static void find2ButtonsThatAreNotOKinSolutions(Button arr2Buttons[], Button gridButtons[][], int arrGridValues[][]){
        int num;
        for(int rowIndex = 0; rowIndex<GRID_SIZE; rowIndex++){
            for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
                num = arrGridValues[rowIndex][columnIndex];
                if(!numIsValid(num,arrGridValues,rowIndex,columnIndex)){
                    arr2Buttons[0] = gridButtons[rowIndex][columnIndex];
                    //Search in column
                    for(int i = 0; i < GRID_SIZE; i++){
                        if(arrGridValues[i][columnIndex] == num && i != rowIndex){
                            arr2Buttons[1] = gridButtons[i][columnIndex];
                            return;
                        }
                    }
                    //Search in row
                    for(int i = 0; i < GRID_SIZE; i++){
                        if(arrGridValues[rowIndex][i] == num && i != columnIndex){
                            arr2Buttons[1] = gridButtons[rowIndex][i];
                            return;
                        }
                    }
                    //Search in square
                    int sqRow = rowIndex - rowIndex % 3;
                    int sqCol = columnIndex - columnIndex % 3;

                    for(int i = 0; i < 3; i++){
                        for(int j = 0; j < 3; j++){
                            if(arrGridValues[sqRow + i][sqCol + j] == num && sqRow + i != rowIndex && sqCol + j != columnIndex){
                                arr2Buttons[1] = gridButtons[sqRow + i][sqCol + j];
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean checkIfSolutionisOK(int grid[][]) {
        for (int rowIndex = 0; rowIndex < GRID_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < GRID_SIZE; columnIndex++) {
                if (!numIsValid(grid[rowIndex][columnIndex], grid, rowIndex, columnIndex)) {
                    return false;
                }
            }
        }
        return true;
    }
    //Key function used to solve sudoku grid
    public static boolean solveSudoku(int grid[][]) {
        for (int rowIndex = 0; rowIndex < GRID_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < GRID_SIZE; columnIndex++) {
                if (grid[rowIndex][columnIndex] == 0) {
                    for (int num = 1; num <= 9; num++) {

                        if (numIsValid(num, grid, rowIndex, columnIndex)) {
                            grid[rowIndex][columnIndex] = num;
                            if (solveSudoku(grid)) return true;
                            else grid[rowIndex][columnIndex] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /* Helper functions for Sudoku Solver
     *********************************************************************************************************/
    private static boolean numIsValid(int num, int grid[][], int rowIndex, int columnIndex) {
        return !containsInRow(num, grid, rowIndex, columnIndex)
                && !containsInColumn(num, grid, rowIndex, columnIndex)
                && !containsInSquare(num, grid, rowIndex, columnIndex);
    }

    private static boolean containsInRow(int num, int grid[][], int rowIndex, int columnIndex) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if (grid[rowIndex][i] == num && i != columnIndex) return true;
        }
        return false;
    }

    private static boolean containsInColumn(int num, int grid[][], int rowIndex, int columnIndex) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if (grid[i][columnIndex] == num && i != rowIndex) return true;
        }
        return false;
    }

    private static boolean containsInSquare(int num, int grid[][], int rowIndex, int columnIndex) {
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

