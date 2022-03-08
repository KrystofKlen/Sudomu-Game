package sudoku_game.sudoku.game;

import sudoku_game.sudoku.model.StopWatch;
import sudoku_game.sudoku.view_model.Grids;

import static sudoku_game.sudoku.view.CONSTANSTS.BTN_COLOR_SOLUTION_CORRECT;
import static sudoku_game.sudoku.view.CONSTANSTS.TEXT_TIME_ELIMINATE;

public class GameControl {
    private Grids grids;
    private StopWatch stopWatch;

    public GameControl(Grids grids, StopWatch stopWatch) {
        this.grids = grids;
        this.stopWatch = stopWatch;
    }

    public void endGame(){
        grids.highlightAllButtons(grids.getArrButtonsInGrid(),BTN_COLOR_SOLUTION_CORRECT);
        stopWatch.cancelTime();
    }
    public void startGame(){
        if(stopWatch.IS_RUNNING){
            stopWatch.resetTime();
        }else{
            stopWatch.start();
        }
    }
    public void discardGame(){
        grids.highlightAllButtons(grids.getArrButtonsInGrid(),BTN_COLOR_SOLUTION_CORRECT);
        stopWatch.cancelTime();
        stopWatch.setTextTxtTime(TEXT_TIME_ELIMINATE);
    }
}
