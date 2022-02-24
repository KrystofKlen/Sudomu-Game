package sudoku_game.sudoku.model;

public class HistoryValue {
    private int rowIndex;
    private int columnIndex;
    private int currentValue;
    private int previousValue;

    public HistoryValue(int rowIndex, int columnIndex, int currentValue, int previousValue) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.currentValue = currentValue;
        this.previousValue = previousValue;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getPreviousValue() {
        return previousValue;
    }
}

