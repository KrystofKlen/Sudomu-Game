package sudoku_game.sudoku.model;

import javafx.scene.text.Text;
import java.util.Timer;
import java.util.TimerTask;

import static sudoku_game.sudoku.view.CONSTANSTS.TEXT_TIME_ELIMINATE;

public class StopWatch {
    private int elapsedTimeInSeconds;
    private Timer timer;
    private TimerTask timerTask;
    private Text txtTime;
    public static boolean IS_RUNNING = false;


    public StopWatch(Text txtTime){
        this.elapsedTimeInSeconds = 0;
        IS_RUNNING = false;
        this.timer = new Timer();
        this.txtTime = txtTime;
    }

    public void cancelTime(){
        if(!IS_RUNNING) return;

        timerTask.cancel();
        IS_RUNNING = false;
    }

    public void resetTime(){
        elapsedTimeInSeconds = 0;
    }

    public void start(){
        if(IS_RUNNING) return;

        timerTask = new TimerTask() {
            @Override
            public void run() {
                elapsedTimeInSeconds++;
                txtTime.setText(formatTime());
            }
        };
        IS_RUNNING = true;
        linkTimerTaskToTimer();
    }

    public void setTextTxtTime(String text) {
        this.txtTime.setText(text);
    }

    private String formatTime(){
        int hours = elapsedTimeInSeconds / 3600;
        int minutes = (elapsedTimeInSeconds - hours*3600) / 60;
        int seconds = elapsedTimeInSeconds - hours*3600 - minutes*60;
        String strTime = String.format("%02d:%02d:%02d",hours,minutes,seconds);
        return strTime;
    }

    private void linkTimerTaskToTimer(){
        timer.schedule(timerTask, 1000, 1000);
    }

}
