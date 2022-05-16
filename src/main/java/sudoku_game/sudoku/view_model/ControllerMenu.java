package sudoku_game.sudoku.view_model;

import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sudoku_game.sudoku.view.ButtonsMenu;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    @FXML
    Button singlePlayerButton, connectButton;
    @FXML
    Text txtState;
    @FXML
    TextField txtFieldServerName, txtFieldUsername, txtFieldPort;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ButtonsMenu buttonsMenu = new ButtonsMenu();
        buttonsMenu.prepareSinglePlayerButton(singlePlayerButton);
        buttonsMenu.prpConntct(connectButton,txtFieldServerName,txtFieldUsername,txtFieldPort,txtState);
    }
}
