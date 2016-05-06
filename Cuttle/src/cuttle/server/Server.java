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
    public void run() {
    }
}
