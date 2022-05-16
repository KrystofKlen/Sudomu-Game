package sudoku_game.sudoku.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sudoku_game.sudoku.RunApplication;
import sudoku_game.sudoku.game.GameControl;
import sudoku_game.sudoku.game.GameControlSinglePlayer;
import sudoku_game.sudoku.model.history.History;
import sudoku_game.sudoku.model.history.HistoryValue;
import sudoku_game.sudoku.model.StopWatch;
import sudoku_game.sudoku.model.SudokuSolver;
import sudoku_game.sudoku.view_model.ButtonsManipulation;
import sudoku_game.sudoku.view_model.Grids;


import java.io.IOException;

import static sudoku_game.sudoku.view.CONSTANSTS.BTN_COLOR_NORMAL;

public class ButtonsSinglePlayer extends ButtonsManipulation {
    Grids gridSudoku;
    public ButtonsSinglePlayer(Grids gridSudoku){
        this.gridSudoku = gridSudoku;
    }
    GameControl gameControl;

    public void prepareNewGridButton(Button button, StopWatch stopWatch){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gameControl = new GameControlSinglePlayer(gridSudoku, stopWatch);
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
                if(!stopWatch.IS_RUNNING){
                    //game has not started
                    return;
                }
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
                    //SOLUTION CORRECT
                    gameControl.endGame();
                }
            }
        });
    }

    public void prepareBackButton(Button button, Grids grids){
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

    public void prepareLeaveButton(Button button){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Parent root = FXMLLoader.load(RunApplication.class.getResource("UI_menu.fxml"));
                    Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
