package cuttle.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import cuttle.CuttleSocket;

/**
 * Represents a game player in the server.
 */
public class Player extends Thread {
    CuttleSocket mSocket;

    public Player(Socket socket) throws IOException {
        mSocket = new CuttleSocket(socket);
        synchronized(System.out) {
            System.out.println("New player!");
        }
    }

    public void run() {
        synchronized(System.out) {
            System.out.println("Running...");
        }

        try {
            while(true) {
                String m = mSocket.receive();
                Scanner s = new Scanner(m);

                if(s.next().equals("PRINT")) {
                    synchronized(System.out) {
                        System.out.println(String.format("%f", s.nextFloat()));
                    }
                }
            }
        }
        catch(IOException e) {
            System.out.println("shit");
        }
    }
}
