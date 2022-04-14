package sudoku_game.sudoku.game;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import sudoku_game.sudoku.client.Client;
import sudoku_game.sudoku.view.CONSTANSTS;
import sudoku_game.sudoku.view_model.Grids;

public class GameControlMultiPlayer{

    public static Client client = null;
    public static boolean isInGame = false;
    public static volatile boolean gameCanStart = false;
    public static String gridServer;
    private static int attemptsRemaining = CONSTANSTS.ATTEMPTS_FOR_MULTIPLAYER;
    private Grids grids;
    public static Button btnSendSolution = null;
    public static Text txtMessagesFromServer = null;

    public GameControlMultiPlayer(Grids grids) {
        this.grids = grids;
    }

    public static int getAttemptsRemaining() {
        return attemptsRemaining;
    }

    public static boolean decrementAttemptsRemaining(){
        attemptsRemaining--;
        if(attemptsRemaining == 0){
            return false;
        }
        return true;
    }

    public static void displayMessageFromServer(String message){
        if(txtMessagesFromServer != null)
            txtMessagesFromServer.setText(message);
    }
    public static void endGame() {
        isInGame = false;
        if(btnSendSolution != null) btnSendSolution.setVisible(false);
        if(client == null) return;
        client.sendMessage("END");
        attemptsRemaining = CONSTANSTS.ATTEMPTS_FOR_MULTIPLAYER;
        client.closeEverything();
        client = null;
    }

}
