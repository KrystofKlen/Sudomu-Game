package sudoku_game.sudoku.client;

import sudoku_game.sudoku.game.GameControlMultiPlayer;

import java.util.Optional;

public class MESSAGES {

    public static String createMessage(String content){
        String message = String.format("#%s:%s#", GameControlMultiPlayer.client.userName, content);
        return message;
    }
}
