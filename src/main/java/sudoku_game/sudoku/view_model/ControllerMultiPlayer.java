package sudoku_game.sudoku.view_model;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import sudoku_game.sudoku.game.GameControlMultiPlayer;
import sudoku_game.sudoku.model.history.History;
import sudoku_game.sudoku.view.ButtonsMultiPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMultiPlayer implements Initializable {

    Grids grids;
    ButtonsMultiPlayer buttons;
    History history;

    @FXML
    GridPane gridSudoku, gridNumbers;
    @FXML
    Button btnSendSolution,btnLeave;
    @FXML
    Text txtAttemptsRemaining;

    @FXML
    Text txtMessagesFromServer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameControlMultiPlayer.btnSendSolution = btnSendSolution;
        GameControlMultiPlayer.txtMessagesFromServer = txtMessagesFromServer;
        grids = new Grids(gridSudoku,gridNumbers);
        buttons = new ButtonsMultiPlayer(grids,btnLeave);
        history = new History();
        buttons.prepareLeaveButton();
        buttons.prepareSendSolutionButton(btnSendSolution,grids.getArrValuesInGrid(),txtAttemptsRemaining);
        grids.generateNewGrid(GameControlMultiPlayer.gridServer);
        History.resetHistory();
    }

}
