package sudoku_game.sudoku.view_model;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import sudoku_game.sudoku.model.History;
import sudoku_game.sudoku.model.StopWatch;
import sudoku_game.sudoku.view.Buttons;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    GridPane gridSudoku, gridNumbers;
    @FXML
    Button btnNewGrid, btnSolve, btnCheck, btnBack;
    @FXML
    Text txtTime;

    Grids grids;
    Buttons buttons;
    History history;
    StopWatch stopWatch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grids = new Grids(gridSudoku, gridNumbers);
        buttons = new Buttons(grids);
        history = new History();
        stopWatch = new StopWatch(txtTime);
        buttons.prepareNewGridButton(btnNewGrid,stopWatch);
        buttons.prepareSolveButton(btnSolve, stopWatch);
        buttons.prepareCheckButton(btnCheck,grids,stopWatch);
        buttons.prepareBackButton(btnBack,grids);
    }

}