import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by shenchengyu on 15/2/2.
 */
public class GameServer {
    private static Vector<String> player = new Vector<String>();
    private static Vector<Integer> score = new Vector<Integer>();
    public static void main(String[] args){
        try {

            ServerSocket socket = new ServerSocket(5000);
            System.out.println("port 5000 is open");
            while (true) {

                    try {
                        Socket s = socket.accept();
                        System.out.println("Accept a new client");
                        GameThread gt = new GameThread(s);
                        Thread th = new Thread(gt);
                        th.start();
                    } catch (IOException e) {
                        System.out.println("Error: " + "socket.accept");
                        break;
                    }
                }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Vector<String> getPlayer(){
        return player;
    }

    static Vector<Integer> getScore(){
        return score;
    }
}
