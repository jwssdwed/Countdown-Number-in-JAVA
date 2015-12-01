import java.io.*;
import java.net.Socket;

/**
 * Created by shenchengyu on 15/2/2.
 */
public class GameThread implements  Runnable {
    Socket s;

    public GameThread(Socket s){
        this.s = s;
    }

    @Override
    public void run() {
        boolean duplicate= false;
        try {
            OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
            InputStream is = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line =br.readLine())!=null){
                String[] split = line.split("\\s");
                if (!split[0].equals("SHOW")) {
                    try {
                        for (int j= GameServer.getPlayer().size() - 1; j>=0; j--){

                            if (GameServer.getPlayer().get(j).equals(split[0])){
                                if (GameServer.getScore().get(j) < Integer.parseInt(split[1])){
                                    GameServer.getScore().set(j, Integer.parseInt(split[1]));
                                }
                                duplicate=true;
                                break;
                            }
                        }
                        if (!duplicate) {
                            GameServer.getPlayer().add(split[0]);
                            GameServer.getScore().add(Integer.parseInt(split[1]));
                            //avoid the same ID


                            //insert the new element to its corresponding position in descending order.
                            for (int j = GameServer.getPlayer().size() - 2; j >= 0; j--) {
                                if (GameServer.getScore().get(j + 1) > GameServer.getScore().get(j)) {
                                    String holdName = GameServer.getPlayer().get(j + 1);
                                    int holdValue = GameServer.getScore().get(j + 1);
                                    GameServer.getPlayer().set(j + 1, GameServer.getPlayer().get(j));
                                    GameServer.getScore().set(j + 1, GameServer.getScore().get(j));
                                    GameServer.getPlayer().set(j, holdName);
                                    GameServer.getScore().set(j, holdValue);
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    catch (Exception e){
                        System.out.println("doesn't input a name");
                    }
                }
                else {
                    int i = 0;
                    while (i<GameServer.getPlayer().size()&&i<=10){
                        osw.write(GameServer.getPlayer().get(i)+" "+GameServer.getScore().get(i)+"\n");
                        osw.flush();
                        i++;
                    }
                    osw.write("end\n");
                    osw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
