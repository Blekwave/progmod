package cuttle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * Class that represents the communication between the server and a client.
 * This class can be used to send/receive packets to/from the client, working
 * as the communication layer between the client and server.
 */
public class CuttleSocket {
    private Socket mSocket;
    private BufferedReader mIn;
    private PrintWriter mOut;

    public CuttleSocket(Socket socket) throws IOException {
        mSocket = socket;
        mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
        mOut = new PrintWriter(mSocket.getOutputStream(), true);
    }

    public void send(String message) throws IOException {
        mOut.println(message);
    }

    public String receive() throws IOException {
        return mIn.readLine();
    }
}
