import java.io.IOException;

/**
 * Main class for the Java server for the HTML client.
 */
public class Main {
    public static void main(String[] args) {

        Integer port = args.length >= 1 ? Integer.parseInt(args[0]) : 4544;
        String contentRoot = args.length >= 2 ? args[1] : "release/";

        try {
            new Server(port, contentRoot);
        } catch (IOException e) {
            System.out.println("Something unexpected happened while trying to serve the client.");
        }
    }
}
