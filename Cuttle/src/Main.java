import java.lang.System;
import cuttle.server.Server;
import cuttle.client.Client;
import cuttle.client.ui.UI;
import cuttle.client.ui.ConsoleUI;

/**
 * Entry point to the Cuttle game.
 */
public class Main {
    /**
     * Returns the argument position if exists or 0 if wasn't found.
     */
    private static int findArg(String name, String[] args) {
        for(int i = 0; i < args.length; ++i)
            if(args[i].equals(name))
                return i;

        return -1;
    }

    /**
     * Prints the help message to the terminal.
     */
    private static void printHelp() {
        System.out.println("usage: cuttle [options]");
        System.out.println("options:");
        System.out.println(" --gui\t\tOpen the game in GUI mode instead of console.");
        System.out.println(" --server <port>\tOpen a server at the given port.");
        System.out.println(" --client <ip>:<port>\tConnect client to the given ip and port.");
    }

    /**
     * Handles a cuttle server.
     */
    private static void runServer(Integer port) throws Exception {
        Server server = new Server(port);
        server.run();
    }

    /**
     * Handles a cuttle client.
     */
    private static void runClient(String ip, Integer port, Boolean gui) throws Exception {
        UI ui = new ConsoleUI();
        /*
        if(gui) {
            ui = new GraphicalUI();
        }
        else {
            ui = new ConsoleUI();
        }
        */

        Client client = new Client(ip, port, ui);
        client.run();
    }

    public static void main(String[] args) throws Exception {
        Integer pos;
        if((pos = findArg("--server", args)) != -1) {
            runServer(Integer.parseInt(args[pos + 1]));
        }
        else if((pos = findArg("--client", args)) != -1) {
            String[] address = args[pos + 1].split(":");
            Boolean gui = findArg("--gui", args) != -1;
            runClient(address[0], Integer.parseInt(address[1]), gui);
        }
        else {
            printHelp();
        }
    }
}
