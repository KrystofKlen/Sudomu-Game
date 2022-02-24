module sudoku_game.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens sudoku_game.sudoku to javafx.fxml;
    exports sudoku_game.sudoku;
    exports sudoku_game.sudoku.view_model;
    opens sudoku_game.sudoku.view_model to javafx.fxml;
    exports sudoku_game.sudoku.view;
    opens sudoku_game.sudoku.view to javafx.fxml;
}