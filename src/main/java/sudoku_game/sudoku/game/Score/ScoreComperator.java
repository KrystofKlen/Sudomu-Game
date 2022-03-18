package sudoku_game.sudoku.game.Score;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

import static sudoku_game.sudoku.game.Score.CompareBy.*;

public class ScoreComperator implements Comparator<Score> {

    private final CompareBy compareBy;

    public ScoreComperator(CompareBy compareBy) {
        this.compareBy = compareBy;
    }

    @Override
    public int compare(Score score1, Score score2) {

        if(compareBy.equals(QUICKEST)){
            int a = timeToInt(score1.getTimeItTookToSolve());
            int b = timeToInt(score2.getTimeItTookToSolve());
            int result = a-b;
            return result;

        }else if(compareBy.equals(SLOWEST)){
            int a = timeToInt(score1.getTimeItTookToSolve());
            int b = timeToInt(score2.getTimeItTookToSolve());
            int result = a-b;
            return -result;

        }else if(compareBy.equals(LATEST)){

        }else{

        }
        return 0;
    }

    private int timeToInt(String time){
        String hoursStr = time.substring(0,2);
        String minutesStr = time.substring(3,5);
        String secondsStr = time.substring(6,8);
        try{
            int hoursInt = Integer.parseInt(hoursStr);
            int minutesInt = Integer.parseInt(minutesStr);
            int secondsInt = Integer.parseInt(secondsStr);

            return hoursInt*3600 + minutesInt*60 + secondsInt;
        }catch (NumberFormatException ex){
            throw new NumberFormatException("INVALID TIME");
        }
    }

}

