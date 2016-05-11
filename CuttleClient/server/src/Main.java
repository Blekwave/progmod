import java.io.IOException;

/**
 * Main class for the Java server for the HTML client.
 */
public class Main {
    public static void main(String[] args) {
        for (String s : args){
            System.out.println(s);
        }
        try {
            Server server = new Server(4544, "release/");
        } catch (IOException e) {
            System.out.println("Something unexpected happened while trying to serve the client.");
        }
    }
}
