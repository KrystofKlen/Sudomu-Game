package sudoku_game.sudoku.view_model;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import sudoku_game.sudoku.model.history.History;
import sudoku_game.sudoku.model.StopWatch;
import sudoku_game.sudoku.view.ButtonsSinglePlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSinglePlayer implements Initializable {

    @FXML
    GridPane gridSudoku, gridNumbers;
    @FXML
    Button btnNewGrid, btnSolve, btnCheck, btnBack, btnLeave;
    @FXML
    Text txtTime;

    Grids grids;
    ButtonsSinglePlayer buttons;
    History history;
    StopWatch stopWatch;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grids = new Grids(gridSudoku, gridNumbers);
        buttons = new ButtonsSinglePlayer(grids);
        history = new History();
        stopWatch = new StopWatch(txtTime);
        buttons.prepareNewGridButton(btnNewGrid,stopWatch);
        buttons.prepareSolveButton(btnSolve, stopWatch);
        buttons.prepareCheckButton(btnCheck,grids,stopWatch);
        buttons.prepareBackButton(btnBack,grids);
        buttons.prepareLeaveButton(btnLeave);
    }

}