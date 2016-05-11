package cuttle.server;

import cuttle.game.CuttleGame;
import cuttle.game.ServerInterface;
import org.json.JSONObject;

import java.util.Scanner;

/**
 * Minimal compact server used for debugging the communication protocol.
 */
public class DebugServer implements ServerInterface {
    private Scanner mScanner;

    /**
     * Initializes the DebugServer.
     */
    public DebugServer(){
        mScanner = new Scanner(System.in);
    }

    /**
     * Runs the DebugServer, starting a new game of Cuttle between two players.
     */
    public void run(){
        new CuttleGame(this, 0, 1).start();
    }

    /**
     * Sends an update to the command line interface.
     *
     * @param message JSON message to be communicated.
     * @param playerId ID of the player to which the update should be sent.
     */
    @Override
    public void update(JSONObject message, Integer playerId) {
        System.out.println("[UPDATE -> " + playerId + "]");
        System.out.println(message.toString(4));
    }

    /**
     * Prompts one of the players for choosing an action through the command
     * line interface.
     *
     * @param message Prompt message to be communicated.
     * @param playerId ID of the player to be prompted.
     * @return Object corresponding to the chosen BehaviorCall.
     */
    @Override
    public JSONObject prompt(JSONObject message, Integer playerId) {
        System.out.println("[PROMPT -> " + playerId + "]");
        System.out.println(message.toString(4));
        System.out.println("Choose a call ID\n> ");
        int id = mScanner.nextInt();
        return buildPromptResponse(id);
    }

    /**
     * Generates a valid prompt response JSON object from a call ID.
     *
     * @param callId ID of the chosen call.
     * @return JSONObject to be sent as a response to the prompt.
     */
    private JSONObject buildPromptResponse(Integer callId){
        JSONObject response = new JSONObject();
        response.put("type", "prompt_response");
        response.put("id", callId);
        return response;
    }
}
