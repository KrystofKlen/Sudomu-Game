package sudoku_game.sudoku.game.Score;

import java.time.LocalDate;
import java.time.LocalTime;

public class Score {
    private LocalDate dateWhenScoreReached;
    private LocalTime timeWhenScoreReached;
    private String timeItTookToSolve;
    private int numOfMoves;

    public Score(LocalDate dateWhenScoreReached, LocalTime timeWhenScoreReached, String timeItTookToSolve, int numOfMoves) {
        this.dateWhenScoreReached = dateWhenScoreReached;
        this.timeWhenScoreReached = timeWhenScoreReached;
        this.timeItTookToSolve = timeItTookToSolve;
        this.numOfMoves = numOfMoves;
    }

    public LocalDate getDateWhenScoreReached() {
        return dateWhenScoreReached;
    }

    public LocalTime getTimeWhenScoreReached() {
        return timeWhenScoreReached;
    }

    public String getTimeItTookToSolve() {
        return timeItTookToSolve;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }


}
