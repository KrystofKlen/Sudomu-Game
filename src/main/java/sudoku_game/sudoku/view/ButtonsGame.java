package sudoku_game.sudoku.view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sudoku_game.sudoku.model.history.History;
import sudoku_game.sudoku.model.history.HistoryValue;
import sudoku_game.sudoku.view_model.Grids;

public class ButtonsGame {

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
}
