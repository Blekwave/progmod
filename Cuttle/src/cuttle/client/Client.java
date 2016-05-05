package cuttle.client;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import cuttle.client.ui.UI;
import cuttle.CuttleSocket;

/**
 * Class that represents a client in the Cuttle game.
 */
public class Client {
    private UI mUI;
    private CuttleSocket mSocket;

    /**
     * Constructs a Client, connecting to the given IP at the given serverPort,
     * using the given UI interface.
     */
    public Client(String ip, int serverPort, UI ui) throws IOException {
        mUI = ui;
        mSocket = new CuttleSocket(new Socket(ip, serverPort));
    }

    public void run() throws IOException {
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.print("Enter a number: ");
            Float f = scan.nextFloat();
            mSocket.send(String.format("PRINT %f", f));
        }
    }
}
