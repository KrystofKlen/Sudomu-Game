package sudoku_game.sudoku.view_model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sudoku_game.sudoku.game.GameControl;
import sudoku_game.sudoku.model.History;
import sudoku_game.sudoku.model.HistoryValue;
import sudoku_game.sudoku.model.StopWatch;
import sudoku_game.sudoku.model.SudokuSolver;


import static sudoku_game.sudoku.view.CONSTANSTS.BTN_COLOR_NORMAL;
import static sudoku_game.sudoku.view.CONSTANSTS.BTN_COLOR_SOLUTION_CORRECT;

public class Buttons extends ButtonsManipulation{
    Grids gridSudoku;
    Buttons(Grids gridSudoku){
        this.gridSudoku = gridSudoku;
    }
    GameControl gameControl;

    public void prepareNewGridButton(Button button, StopWatch stopWatch){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gameControl = new GameControl(gridSudoku, stopWatch);
                gameControl.startGame();
                gridSudoku.generateNewGrid();
                History.resetHistory();

            }
        });
    }

    public void prepareSolveButton(Button button, StopWatch stopWatch){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SudokuSolver.solveSudoku(gridSudoku.getArrValuesInGrid());
                gridSudoku.setButtonsTextFromArrOfValues(gridSudoku.getArrValuesInGrid(), gridSudoku.getArrButtonsInGrid());
                gameControl.discardGame();
            }
        });
    }

    public void prepareCheckButton(Button button, Grids grids, StopWatch stopWatch){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boolean valuesFilled = grids.checkIfAllValuesFilled();
                if(!valuesFilled) return;
                highlightAllButtons(grids.getArrButtonsInGrid(),BTN_COLOR_NORMAL);
                boolean solved = SudokuSolver.checkIfSolutionisOK(grids.getArrValuesInGrid());
                if(!solved){
                    highlightWrongSolution(grids.getArrButtonsInGrid(),grids.getArrValuesInGrid());
                }else{
                    gameControl.endGame();
                }
            }
        });
    }

    void prepareBackButton(Button button, Grids grids){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(History.getStackHistory().isEmpty()) return;
                HistoryValue peek = History.getPeek();

                int rowIndex = peek.getRowIndex();
                int columnIndex = peek.getColumnIndex();
                int previousValue = peek.getPreviousValue();
                String textForButton;
                if(previousValue == 0){
                    textForButton = "";
                }else {
                    textForButton = String.valueOf(previousValue);
                }
                grids.getArrButtonsInGrid()[rowIndex][columnIndex].setText(textForButton);
                History.getStackHistory().pop();
            }
        });
    }

}
