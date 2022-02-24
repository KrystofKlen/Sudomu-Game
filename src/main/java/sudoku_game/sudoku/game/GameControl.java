package sudoku_game.sudoku.game;

import sudoku_game.sudoku.model.StopWatch;
import sudoku_game.sudoku.view_model.Grids;

import static sudoku_game.sudoku.view.CONSTANSTS.BTN_COLOR_SOLUTION_CORRECT;

public class GameControl {
    Grids grids;
    StopWatch stopWatch;

    public GameControl(Grids grids, StopWatch stopWatch) {
        this.grids = grids;
        this.stopWatch = stopWatch;
    }

    public void endGame(){
        grids.highlightAllButtons(grids.getArrButtonsInGrid(),BTN_COLOR_SOLUTION_CORRECT);
        stopWatch.cancelTime();
    }
    public void startGame(){
        stopWatch.start();
    }
    public void discardGame(){
        grids.highlightAllButtons(grids.getArrButtonsInGrid(),BTN_COLOR_SOLUTION_CORRECT);
        stopWatch.cancelTime();
    }
}
