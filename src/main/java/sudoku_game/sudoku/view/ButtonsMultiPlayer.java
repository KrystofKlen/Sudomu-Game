package sudoku_game.sudoku.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku_game.sudoku.RunApplication;
import sudoku_game.sudoku.game.GameControlMultiPlayer;
import sudoku_game.sudoku.model.SudokuSolver;
import sudoku_game.sudoku.view_model.ButtonsManipulation;
import sudoku_game.sudoku.view_model.Grids;

import java.io.IOException;;

import static sudoku_game.sudoku.view.CONSTANSTS.GRID_SIZE;

public class ButtonsMultiPlayer extends ButtonsManipulation {
    Grids sudokuGrid;
    Button btnLeave;

    public ButtonsMultiPlayer(Grids sudokuGrid,Button btnLeave) {
        this.sudokuGrid = sudokuGrid;
        this.btnLeave = btnLeave;
    }

    public void prepareSendSolutionButton(Button btn, int [][] valuesInGrid, Text txtAttemptsRemaining){
        btn.setOnMouseClicked(mouseEvent ->  {
            if ( SudokuSolver.checkIfSolutionisOK(valuesInGrid) ){
                GameControlMultiPlayer.client.sendMessage("WON");
                return;
            }
            if( !GameControlMultiPlayer.decrementAttemptsRemaining() ){
                GameControlMultiPlayer.client.sendMessage("OUT_OF_ATTEMPTS");
                txtAttemptsRemaining.setText("NO ATTEMPTS, YOU ARE OUT OF THE GAME!");
                highlightWrongSolution(sudokuGrid.getArrButtonsInGrid(),sudokuGrid.getArrValuesInGrid());
                btn.setVisible(false);
                return;
            }
            txtAttemptsRemaining.setText(String.valueOf(GameControlMultiPlayer.getAttemptsRemaining()));
            highlightWrongSolution(sudokuGrid.getArrButtonsInGrid(),sudokuGrid.getArrValuesInGrid());
        });

    }

    public void prepareLeaveButton(){
       btnLeave.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               GameControlMultiPlayer.endGame();
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
