package sudoku_game.sudoku.view_model;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
    Button btnSendSolution;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grids = new Grids(gridSudoku,gridNumbers);
        buttons = new ButtonsMultiPlayer(grids);
        history = new History();
        buttons.prepareGiveUpButton();
        buttons.prepareSendSolutionButton(btnSendSolution);
        grids.generateNewGrid(GameControlMultiPlayer.gridServer);
        History.resetHistory();
    }
}
