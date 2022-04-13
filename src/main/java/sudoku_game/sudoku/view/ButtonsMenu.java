package sudoku_game.sudoku.view;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku_game.sudoku.RunApplication;
import sudoku_game.sudoku.client.Client;
import sudoku_game.sudoku.game.GameControlMultiPlayer;
import sudoku_game.sudoku.view_model.ButtonsManipulation;

import java.io.IOException;
import java.util.TimerTask;

public class ButtonsMenu extends ButtonsManipulation {

    Stage stage;
    Scene scene;

    public void prepareSinglePlayerButton(Button button){
        button.setOnMouseClicked(event -> {
            try {
                Parent root = FXMLLoader.load(RunApplication.class.getResource("UI_single_player.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public void prpConntct(Button button, TextField txtFieldServerIP, TextField txtFieldUsername,
                           TextField txtFieldPort, Text txtState){



        button.setOnMouseClicked(mouseEvent -> {
            int port;
            try{port = Integer.parseInt(txtFieldPort.getText());}
            catch (NumberFormatException numEx){
                txtState.setText("Invalid Port.");
                return;
            }
            Client client = new Client(txtFieldUsername.getText());

            Task<Void> connected = new Task<Void>() {
                @Override
                protected Void call() throws Exception {

                    txtState.setText("Conneting...");

                    if(!client.connectClient(txtFieldServerIP.getText(),port)){
                        txtState.setText("Connetion failed.");
                        throw new IOException();
                    }
                    GameControlMultiPlayer.client = client;
                    if( !client.authenticateSuccess() ){
                        client.closeEverything();
                        System.out.println("NOT AUTHENTICATED");
                        throw new IOException();
                    }

                    txtState.setText("Looking for opponents...");
                    client.listenForMessage();
                    while(!GameControlMultiPlayer.gameCanStart){
                        try {
                            System.out.println("NOT YET" + GameControlMultiPlayer.gameCanStart);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            };

            connected.setOnSucceeded(event->{
                try {
                    Parent root = FXMLLoader.load(RunApplication.class.getResource("UI_multi_player.fxml"));
                    stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    client.closeEverything();
                }
            });

            connected.setOnFailed(workerStateEvent -> {
                System.out.println("ENDING TASK");
            });
            Thread t = new Thread(connected);
            t.start();
        });
    }

}
