package sudoku_game.sudoku.user;

import sudoku_game.sudoku.game.Score.Score;

public class User {
    private String userNickname;
    private Score userBestScore;

    public User(String userNickname) {
        this.userNickname = userNickname;
        this.userBestScore = null;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public Score getUserBestScore() {
        return userBestScore;
    }
}
