package sudoku_game.sudoku.game;

import sudoku_game.sudoku.client.Client;
import sudoku_game.sudoku.view_model.Grids;

public class GameControlMultiPlayer implements GameControl{

    public static Client client;
    public static boolean gameCanStart = false;
    public static boolean gameStarted = false;
    public static String gridServer;
    private Grids grids;

    public GameControlMultiPlayer(Grids grids) {
        this.grids = grids;
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
