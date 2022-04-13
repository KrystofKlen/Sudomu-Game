package sudoku_game.sudoku.client;

import sudoku_game.sudoku.game.GameControl;
import sudoku_game.sudoku.game.GameControlMultiPlayer;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public String userName;
    AtomicBoolean authenticated;

    public Client(String userName){
        this.userName = userName;
        authenticated = new AtomicBoolean(false);
    }
    public boolean connectClient(String serverIP, int port){
        try{
            this.socket = new Socket(serverIP, port);
            this.bufferedWriter =
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())
                    );
            this.bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream())
                    );


        }catch (IOException ioEx){
            System.out.println("CONNECTION FAILED" + " USERNAME: " + userName);
            closeEverything();
            return false;
        }

        return true;
    }

    public boolean sendMessage(String content){

        String message = String.format("#%s:%s\n", GameControlMultiPlayer.client.userName, content);
        try {
            bufferedWriter.write(message);
            bufferedWriter.flush();
            System.out.println("SENDING MESSAGE: " + message);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while(socket.isConnected()){
                    try{
                        message = bufferedReader.readLine();
                        System.out.println("RECEIVED: " + message);
                        if(message.equals("#AUTHENTICATED\n")){
                            authenticated.set(true);
                        }
                        if(message.startsWith("#GRID:")){
                            GameControlMultiPlayer.gameCanStart = true;
                            GameControlMultiPlayer.gridServer = message;
                        }
                    }catch(IOException e){
                        closeEverything();
                    }
                }
            }
        }).start();
    }

    public boolean authenticateSuccess() {
        sendMessage("NEW_USER");
        String message;
        System.out.println("LISTENING");
        while (socket.isConnected()) {
            try {
                message = bufferedReader.readLine();
                System.out.println("RECEIVED: " + message);
                if (message.equals("#AUTHENTICATED\n")) {
                    authenticated.set(true);
                    return true;
                }
            } catch (IOException e) {
                closeEverything();
            }
            return true;
        }
        return false;
    }

    public void closeEverything(){
        try{
            if(bufferedReader != null
                    && bufferedWriter != null
                    && socket != null){
                bufferedReader.close();
                bufferedWriter.close();
                socket.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
