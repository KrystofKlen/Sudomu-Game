package sudoku_game.sudoku.game.Score;

import sudoku_game.sudoku.game.Score.CompareBy;

import java.util.Comparator;

import static sudoku_game.sudoku.game.Score.CompareBy.QUICKEST;

public class TimeComperator implements Comparator<String> {

    private CompareBy compareBy;

    public TimeComperator(CompareBy compareBy){
        this.compareBy = compareBy;
    }

    @Override
    public int compare(String time1, String time2) {
        int a = timeToInt(time1);
        int b = timeToInt(time2);
        int result = a-b;
        if(compareBy.equals(QUICKEST)){
            return result;
        }
        return -result;
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
