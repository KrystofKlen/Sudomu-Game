package sudoku_game.sudoku.view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sudoku_game.sudoku.game.GameControl;
import sudoku_game.sudoku.view_model.ButtonsManipulation;
import sudoku_game.sudoku.view_model.Grids;

public class ButtonsMultiPlayer extends ButtonsManipulation {
    Grids sudokuGrid;

    public ButtonsMultiPlayer(Grids sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }

    public void prepareSendSolutionButton(Button btn){
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("SEND SOLUTION");
            }
        });

    }

    public void prepareGiveUpButton(){

    }

}
