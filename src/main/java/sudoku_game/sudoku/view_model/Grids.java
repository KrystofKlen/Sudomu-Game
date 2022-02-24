package sudoku_game.sudoku.view_model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sudoku_game.sudoku.model.History;
import sudoku_game.sudoku.model.SudokuSolver;
import sudoku_game.sudoku.view.CONSTANSTS;

import static sudoku_game.sudoku.view.CONSTANSTS.*;

public class Grids extends ButtonsManipulation{

    private static int arrValuesInGrid[][] = new int[GRID_SIZE][GRID_SIZE];
    private Button arrButtonsInGrid[][] = new Button[GRID_SIZE][GRID_SIZE];
    boolean arrFixedValues[][] = new boolean[GRID_SIZE][GRID_SIZE];
    private GridPane gridSudoku;
    private GridPane numbersGrid;
    private Button arrNumberButtons[] = new Button[GRID_SIZE];
    private Button selectedButton;

    public Grids(GridPane gridSudoku, GridPane numbersGrid){
        this.gridSudoku = gridSudoku;
        this.numbersGrid = numbersGrid;
        fillNumberButtonsGrid();
        fillSudokuGridWithButtons();
    }

    public int[][] getArrValuesInGrid() {
        return arrValuesInGrid;
    }
    public Button[][] getArrButtonsInGrid() {
        return arrButtonsInGrid;
    }

    public Button getSelectedButton() {
        return selectedButton;
    }

    public void generateNewGrid(){
        selectedButton = null;
        unsetFixedValues(arrFixedValues);
        highlightAllButtons(arrButtonsInGrid,arrNumberButtons,BTN_COLOR_NORMAL);
        clearTextButtons(arrButtonsInGrid);
        setAllNumbersInGridTo0(arrValuesInGrid);
        setNonCollidingNumbers(arrValuesInGrid);
        SudokuSolver.solveSudoku(arrValuesInGrid);
        clearNumbersAtRandomPositions(arrValuesInGrid);
        setButtonsTextFromArrOfValues(arrValuesInGrid,arrButtonsInGrid);
        fixValuesGeneratedByNewGrid(arrValuesInGrid, arrFixedValues);
        setStyleToButtonsWithFixedValues(arrFixedValues, arrButtonsInGrid);
    }

    private void fillSudokuGridWithButtons(){
        for(int rowIndex = 0; rowIndex<GRID_SIZE; rowIndex++){
            for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
                Button btnGrid = new Button();
                btnGrid.setPrefHeight(CONSTANSTS.SUDOKU_BTN_SIZE);
                btnGrid.setPrefWidth(CONSTANSTS.SUDOKU_BTN_SIZE);
                btnGrid.setId(String.valueOf(rowIndex) + String.valueOf(columnIndex));
                btnGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        selectedButton = btnGrid;
                        highlightAllButtons(arrButtonsInGrid,arrNumberButtons,BTN_COLOR_NORMAL);
                        higlightImportantButtons(btnGrid,arrButtonsInGrid,IMPORTANT_BTN_HIGHLIGHT_COLOR,SELECTED_BTN_HIGHLIGHT_COLOR);
                        highlightButtonWithCorresxpondingValue(btnGrid);
                        setStyleToButtonsWithFixedValues(arrFixedValues, arrButtonsInGrid);
                    }
                });
                gridSudoku.add(btnGrid,columnIndex,rowIndex);
                arrButtonsInGrid[rowIndex][columnIndex] = btnGrid;
            }
        }
        highlightAllButtons(arrButtonsInGrid,BTN_COLOR_NORMAL);
        setStyleToButtonsWithFixedValues(arrFixedValues, arrButtonsInGrid);
    }

    private void fillNumberButtonsGrid(){
        for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
            Button btnGrid = new Button();
            btnGrid.setPrefHeight(CONSTANSTS.NUMBER_BTN_HEIGHT);
            btnGrid.setPrefWidth(CONSTANSTS.NUMBER_BTN_WIDTH);
            btnGrid.setText(String.valueOf(columnIndex + 1));
            btnGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(selectedButton == null) return;

                    boolean valueIsFixed = valueISFixed(selectedButton.getId(), arrFixedValues);
                    if(valueIsFixed) return;

                    changeButtonValueInArrValues(btnGrid);
                    changeButtonText(btnGrid.getText(),selectedButton);
                    highlightParticularButtons(arrNumberButtons,BTN_COLOR_NORMAL);
                    highlightParticularButtons(btnGrid,SELECTED_BTN_HIGHLIGHT_COLOR);
                    History.addToHistory(selectedButton);
                    History.deleteFromHistoryIfLimitExceeded();
                }
            });
            arrNumberButtons[columnIndex] = btnGrid;
            numbersGrid.add(btnGrid,columnIndex,0);
            highlightParticularButtons(btnGrid,BTN_COLOR_NORMAL);
        }
    }

    private void highlightButtonWithCorresxpondingValue(Button selectedButton){
        int value;
        try{
            value = Integer.parseInt(selectedButton.getText());
        }catch (NumberFormatException ex){return;};
        highlightParticularButtons(getButtonWithValue(value),SELECTED_BTN_HIGHLIGHT_COLOR);
    }

    private Button getButtonWithValue(int value){
        value --;
        return arrNumberButtons[value];
    }
    public void changeButtonValueInArrValues(Button numberButton){
        int btnRowIndex = Character.getNumericValue(selectedButton.getId().charAt(0));
        int btnColumnIndex = Character.getNumericValue(selectedButton.getId().charAt(1));
        int value = Integer.parseInt(numberButton.getText());
        arrValuesInGrid[btnRowIndex][btnColumnIndex] = value;
    }

    public boolean checkIfAllValuesFilled(){
        for(int rowIndex = 0; rowIndex<GRID_SIZE; rowIndex++){
            for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
                if(arrValuesInGrid[rowIndex][columnIndex] == 0) return false;
            }
        }
        return true;
    }

    public boolean valueISFixed(String buttonID, boolean arrPositionsWithFixedValues[][]){
        int rowIndex = Character.getNumericValue(buttonID.charAt(0));
        int columnIndex = Character.getNumericValue(buttonID.charAt(1));
        return arrPositionsWithFixedValues[rowIndex][columnIndex];
    }

    // sets all values in boolean arrPositionsWithFixedValues[][] to false, because new grid will be generated and old
    // fixed values might be free in a new grid.
    public void unsetFixedValues(boolean arrPositionsWithFixedValues[][]){
        for(int i = 0; i<GRID_SIZE; i++){
            for(int j = 0; j<GRID_SIZE; j++){
                arrPositionsWithFixedValues[i][j] = false;
            }
        }
    }

    //Fixes values at positions, on which a value was given, so that they can not be changed.
    public void fixValuesGeneratedByNewGrid(int newGrid[][],boolean arrPositionsWithFixedValues[][]){
        for(int i = 0; i<GRID_SIZE; i++){
            for(int j = 0; j<GRID_SIZE; j++){

                //the value at the [i][j] position was pre-given.
                if(newGrid[i][j] != 0){
                    arrPositionsWithFixedValues[i][j] = true;
                }else{
                    arrPositionsWithFixedValues[i][j] = false;
                }
            }
        }
    }

}
