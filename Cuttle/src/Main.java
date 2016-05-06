import java.lang.System;
import cuttle.server.Server;
import cuttle.server.DebugServer;

/**
 * Entry point to the Cuttle game.
 */
public class Main {
    /**
     * Returns the argument position if exists or -1 if wasn't found.
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
        System.out.println(" --debug\t\tStart the debug game interface. Does not open connections.");
        System.out.println(" --port <port>\t\tDefine port to server. Default is 44001.");
    }

    public static void main(String[] args) {
        Boolean debug = false;
        Integer port = 44001;
        try {
            Integer pos;

            if(findArg("--debug", args) != -1)
                debug = true;
            if((pos = findArg("--port", args)) != -1)
                port = Integer.parseInt(args[pos + 1]);
        }
        catch(Exception e) {
            printHelp();
            return;
        }

        if(!debug)
            new Server(port).run();
        else
            new DebugServer().run();
    }
}
