package cuttle.server;

import java.net.ServerSocket;

/**
 * Represents a dedicated server of a cuttle game.
 * Clients connect to this server to play a game.
 */
public class Server {
    private final Integer mPort;

    public Server(Integer port) {
        mPort = port;
    }

    /**
     * Runs the server and starts listening to clients.
     */
    public void run() throws Exception {
        ServerSocket listener = new ServerSocket(mPort);

        try {
            while(true) {
                synchronized(System.out) {
                    System.out.println("Creating new game...");
                }

                // Create a new game.
                Player p1 = new Player(listener.accept());
                Player p2 = new Player(listener.accept());

                p1.start();
                p2.start();
            }
        }
        finally {
            listener.close();
        }
    }
}
