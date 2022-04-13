package sudoku_game.sudoku.game;

import sudoku_game.sudoku.client.Client;
import sudoku_game.sudoku.view.CONSTANSTS;
import sudoku_game.sudoku.view_model.Grids;

public class GameControlMultiPlayer implements GameControl{

    public static Client client;
    public static boolean gameCanStart = false;
    public static String gridServer;

    public static boolean waitingForEvaluation = false;
    private static int attemptsRemaining = CONSTANSTS.ATTEMPTS_FOR_MULTIPLAYER;
    private Grids grids;

    public GameControlMultiPlayer(Grids grids) {
        this.grids = grids;
    }
    public static boolean decrementAttemptsRemaining(){
        attemptsRemaining--;
        if(attemptsRemaining == 0){
            return false;
        }
        return true;
    }

    @Override
    public void startGame() {
        System.out.println("Starting game");
    }

    @Override
    public void endGame() {

    }

    @Override
    public void discardGame() {

    }
}
