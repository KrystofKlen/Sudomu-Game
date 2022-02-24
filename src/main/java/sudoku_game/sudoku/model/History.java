package sudoku_game.sudoku.model;

import javafx.scene.control.Button;
import java.util.Stack;

import static sudoku_game.sudoku.view.CONSTANSTS.MAXIMUX_HISTORY_LENGTH;

public class History {

    private static Stack<HistoryValue> stackHistory;
    private static int numOfMoves = 0;
    public History() {
        stackHistory = new Stack<>();
    }

    public static Stack<HistoryValue> getStackHistory() {
        return stackHistory;
    }
    public static HistoryValue getPeek() {
        return stackHistory.peek();
    }

    public static int getNumOfMoves() {
        return numOfMoves;
    }

    public static void resetHistory(){
        for(int i = stackHistory.size(); i>0; i--){
            stackHistory.pop();
        }
        numOfMoves = 0;
    }

    public static void addToHistory(Button selectedButton){
        int rowIndex = Character.getNumericValue(selectedButton.getId().charAt(0));
        int columnIndex = Character.getNumericValue(selectedButton.getId().charAt(1));
        int value = Integer.parseInt(selectedButton.getText());

        int previousValue = getPreviousValueAtGivenPosition(rowIndex, columnIndex);
        HistoryValue newHistVal = new HistoryValue(rowIndex, columnIndex, value, previousValue);
        stackHistory.push(newHistVal);
        numOfMoves++;
    }

    public static void deleteFromHistoryIfLimitExceeded(){
        if(stackHistory.size() > MAXIMUX_HISTORY_LENGTH){
            deleteFromHistory();
        }
    }

    private static void deleteFromHistory(){
        stackHistory.remove(0);
    }

    private static int getPreviousValueAtGivenPosition(int rowIndex, int columnIndex){
        int lastValue = 0;
        for(HistoryValue currentHistVal : stackHistory){
            if(currentHistVal.getRowIndex() == rowIndex && currentHistVal.getColumnIndex() == columnIndex){
                lastValue = currentHistVal.getCurrentValue();
            }
        }
        return lastValue;
    }
}
