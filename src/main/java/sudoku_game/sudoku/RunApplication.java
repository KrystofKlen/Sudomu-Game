package sudoku_game.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sudoku_game.sudoku.game.GameControlMultiPlayer;

import java.io.IOException;

public class RunApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("UI_menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            System.out.printf("ENDING game");
            if(GameControlMultiPlayer.client != null){
                GameControlMultiPlayer.endGame();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}