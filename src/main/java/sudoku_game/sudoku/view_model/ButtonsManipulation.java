package sudoku_game.sudoku.view_model;

import javafx.scene.control.Button;
import sudoku_game.sudoku.model.SudokuSolver;

import java.util.Random;

import static sudoku_game.sudoku.view.CONSTANSTS.*;

public class ButtonsManipulation {

    /**
     * highlights buttons in square, row and column
     */
    public void higlightImportantButtons(Button buttonClicked, Button arrGridButtons[][],final String color, String colorSelected){
        int btnRowIndex = Character.getNumericValue(buttonClicked.getId().charAt(0));
        int btnColumnIndex = Character.getNumericValue(buttonClicked.getId().charAt(1));
        //highlight row
        for(int columnIndex = 0; columnIndex<GRID_SIZE;columnIndex++){
            (arrGridButtons[btnRowIndex][columnIndex]).setStyle("-fx-background-color:" + color + ";");
        }
        //highlightColumn
        for(int rowIndex = 0; rowIndex<GRID_SIZE;rowIndex++){
            (arrGridButtons[rowIndex][btnColumnIndex]).setStyle("-fx-background-color:" + color + ";");
        }
        //highlight square
        int squareRowIndex = btnRowIndex - btnRowIndex % 3;
        int squareColumnIndex = btnColumnIndex - btnColumnIndex% 3;

        for(int rowIndex = 0; rowIndex < 3; rowIndex++){
            for(int columnIndex = 0; columnIndex < 3; columnIndex++){
                (arrGridButtons[squareRowIndex + rowIndex][squareColumnIndex + columnIndex]).setStyle("-fx-background-color:" + color + ";");
            }
        }
        //highlight selected button
        buttonClicked.setStyle("-fx-background-color:" + colorSelected + ";");
    }

    /**
     * when 2 buttons are Not OK with in the solution, than this function is used to
     * highlight these 2 buttons
     * @param arrGridButtons
     * @param arrGridValues
     */
    public void highlightWrongSolution(Button arrGridButtons[][], int arrGridValues[][]){
        Button wrongSolution[] = new Button[2];
        SudokuSolver.find2ButtonsThatAreNotOKinSolutions(wrongSolution,arrGridButtons,arrGridValues);
        highlightParticularButtons(wrongSolution,BTN_COLOR_SOLUTION_WRONG_SOLUTION);
    }

    public void highlightAllButtons(Button arrGridButtons[][], final String color){
        for(int rowIndex = 0; rowIndex<GRID_SIZE; rowIndex++){
            for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
                (arrGridButtons[rowIndex][columnIndex]).setStyle("-fx-background-color:" + color + ";");
            }
        }
    }

    public void highlightAllButtons(Button arrSudokuButtons[][], Button arrNumberButtons[], final String color){
        highlightAllButtons(arrSudokuButtons,color);
        highlightParticularButtons(arrNumberButtons,color);
    }

    public void highlightParticularButtons(Button arrButtonsToHighlight[], final String color){
        for(int i = 0; i< arrButtonsToHighlight.length; i++){
            (arrButtonsToHighlight[i]).setStyle("-fx-background-color:" + color + ";");
        }
    }
    public void highlightParticularButtons(Button buttonToHighlight, final String color){
            buttonToHighlight.setStyle("-fx-background-color:" + color + ";");
    }

    public void setFixedValueButtonsStyle(boolean arrFixedValues[][], Button button, String styleFixedValue){
        for(int rowIndex = 0; rowIndex<GRID_SIZE; rowIndex++){
            for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
                if(arrFixedValues[rowIndex][columnIndex]) button.setStyle(styleFixedValue);
            }
        }
    }

    void clearTextButtons(Button arrGridButtons[][]){
        for(int i = 0; i<GRID_SIZE; i++){
            for(int j = 0; j<GRID_SIZE; j++){
                arrGridButtons[i][j].setText("");
            }
        }
    }

    void setAllNumbersInGridTo0(int grid[][]){
        for(int i = 0; i<GRID_SIZE; i++){
            for(int j = 0; j<GRID_SIZE; j++){
                grid[i][j] = 0;
            }
        }
    }

    void setNonCollidingNumbers(int grid[][]){
        Random random = new Random(System.currentTimeMillis());
        int randNum = random.nextInt(9) + 1;
        for(int i = 0; i<GRID_SIZE/3; i++){
            grid[i*3][i*3] = randNum;
        }
    }

    /**
     * when a new grid is generated... there is a solved grid and we want to remove approximately 4 of 5 elements at
     * random positions, so that there are empty squares in grid and Sudoku can be solved.
     * @param grid
     */
    void clearNumbersAtRandomPositions(int grid[][]){
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i<GRID_SIZE; i++){
            for(int j = 0; j<GRID_SIZE; j++){
                int randNum = random.nextInt(9) + 1;
                // Clear approximately 4 out of 5 squares
                if(randNum % 5 != 1) grid[i][j] = 0;
            }
        }
    }

    public void setButtonsTextFromArrOfValues(int arrGridValues[][], Button arrGridButtons[][]){
        int currentValue;
        for(int rowIndex = 0; rowIndex<GRID_SIZE; rowIndex++){
            for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
                currentValue = arrGridValues[rowIndex][columnIndex];
                if(currentValue > 0 && currentValue <10){
                    arrGridButtons[rowIndex][columnIndex].setText(String.valueOf(currentValue));
                }
            }
        }
    }

    public void changeButtonText(String strValue, Button button){
        if(strValue.equals("0")){
            button.setText("");
            return;
        }
        button.setText(strValue);
    }

    public void setStyleToButtonsWithFixedValues(boolean arrButtonsWithFixedValues[][], Button arrButtonsInGrid[][]){
        for(int rowIndex = 0; rowIndex<GRID_SIZE; rowIndex++){
            for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
                if(arrButtonsWithFixedValues[rowIndex][columnIndex]){
                    arrButtonsInGrid[rowIndex][columnIndex].setStyle(BTN_FIXED_VALUE_STYLE);
                }
            }
        }
    }

}
