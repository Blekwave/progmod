package cuttle.server;

import cuttle.game.CuttleGame;
import cuttle.game.ServerInterface;

/**
 * Describe this class and the methods exposed by it.
 */
public class GameThread extends Thread {
    private CuttleGame mGame;
    public GameThread(ServerInterface server, Integer idFirst, Integer idSecond){
        mGame = new CuttleGame(server, idFirst, idSecond);
    }

    public void run(){
        mGame.start();
    }
}
