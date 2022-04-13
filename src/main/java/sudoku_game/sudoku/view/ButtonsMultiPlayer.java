package sudoku_game.sudoku.view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sudoku_game.sudoku.game.GameControl;
import sudoku_game.sudoku.game.GameControlMultiPlayer;
import sudoku_game.sudoku.model.SudokuSolver;
import sudoku_game.sudoku.view_model.ButtonsManipulation;
import sudoku_game.sudoku.view_model.Grids;

import static sudoku_game.sudoku.view.CONSTANSTS.GRID_SIZE;

public class ButtonsMultiPlayer extends ButtonsManipulation {
    Grids sudokuGrid;

    public ButtonsMultiPlayer(Grids sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }

    public void prepareSendSolutionButton(Button btn, int [][] valuesInGrid){
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if ( SudokuSolver.checkIfSolutionisOK(valuesInGrid) ){
                    GameControlMultiPlayer.client.sendMessage("WON");
                    return;
                }
                if( !GameControlMultiPlayer.decrementAttemptsRemaining() ){
                    GameControlMultiPlayer.client.sendMessage("OUT_OF_ATTEMPTS");
                }
            }
        });

    }

    public void prepareGiveUpButton(){

    }

    private String convertValuesInGridToMessageFormat(int [][] valuesInGrid){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SOLUTION:");
        for(int rowIndex = 0; rowIndex< GRID_SIZE; rowIndex++){
            for(int columnIndex = 0; columnIndex<GRID_SIZE; columnIndex++){
                stringBuffer.append(String.valueOf(valuesInGrid[rowIndex][columnIndex]));
                stringBuffer.append(',');
            }
        }
        stringBuffer.append('\n');
        return new String(stringBuffer);
    }

}
