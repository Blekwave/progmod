package cuttle.server;

import cuttle.game.ServerInterface;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

/**
 * Represents a dedicated server of a cuttle game.
 * Clients connect to this server to play a game.
 */
public class Server extends WebSocketServer implements ServerInterface {
    private static int mClientIdGenerator;
    private Random mRandom;
    private HashMap<WebSocket, Integer> mSocketIds;
    private HashMap<Integer, SocketData> mSocketDatas;
    private WebSocket mWaitingSocket; // Socket waiting for a new connection.

    /**
     * Generates a new unique Id for a client.
     */
    static int genUniqueClientId() {
        return mClientIdGenerator++;
    }

    /**
     * Initializes a cuttle game server, given a port.
     */
    public Server(Integer port) {
        super(new InetSocketAddress(port));

        mRandom = new Random();
        mSocketDatas = new HashMap<>();
        mSocketIds = new HashMap<>();
    }

    @Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
        int id = genUniqueClientId();
        System.out.println("A wild Player " + id + " appears");

        mSocketIds.put(conn, id);
        mSocketDatas.put(id, new SocketData(conn));

        if(mWaitingSocket != null) {
            Integer wId = mSocketIds.get(mWaitingSocket);

            // Choose a random first player.
            if(mRandom.nextBoolean())
                new GameThread(this, id, wId).start();
            else
                new GameThread(this, wId, id).start();

            System.out.println("Game started: " + id + " vs " + wId);
            mWaitingSocket = null;
        }
        else {
            mWaitingSocket = conn;
        }
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Integer id = mSocketIds.get(conn);
        System.out.println("Player " + id + " closed the connection");

        if(mWaitingSocket == conn)
            mWaitingSocket = null;
        mSocketDatas.remove(id);
        mSocketIds.remove(conn);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
        mSocketDatas.get(mSocketIds.get(conn)).storeMessage(message);
	}

    @Override
	public void onError(WebSocket conn, Exception e) {
		e.printStackTrace();
	}

    @Override
    public JSONObject prompt(JSONObject message, Integer playerId) {
        // In case wait() doesn't work, this loop will busy wait until a message
        // arrives.
        while(true) {
            try {
                SocketData data = mSocketDatas.get(playerId);
                data.sendMessage(message.toString());
                return new JSONObject(data.waitMessage());
            } catch(InterruptedException e) {
                // Go to new iteration.
            }
        }
    }

    @Override
    public void update(JSONObject message, Integer playerId)  {
        mSocketDatas.get(playerId).sendMessage(message.toString());
    }
}

/**
 * Container to hold all the data needed for sending and receiving messages
 * to/from a socket.
 */
class SocketData {
    private Queue<String> mMessages; // Messages queued.
    private WebSocket mSocket;

    /**
     * Initializes the SocketData with a socket.
     */
    public SocketData(WebSocket socket) {
        mMessages = new LinkedList<>();
        mSocket = socket;
    }

    /**
     * Adds a message to the message queue.
     */
    public synchronized void storeMessage(String message) {
        Boolean wasEmpty = mMessages.isEmpty();

        mMessages.add(message);
        if(wasEmpty)
            notify();
    }

    /**
     * Pops a message from the message queue.
     * In case there aren't any messages, the thread will block until a message
     * arrives.
     */
    public synchronized String waitMessage() throws InterruptedException {
        if(mMessages.isEmpty())
            wait();

        return mMessages.remove();
    }

    /**
     * Sends a message through the socket.
     */
    public synchronized void sendMessage(String message) {
        mSocket.send(message);
    }
}
