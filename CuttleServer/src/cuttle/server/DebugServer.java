package cuttle.server;

import cuttle.game.CuttleGame;
import cuttle.game.ServerInterface;
import org.json.JSONObject;

import java.util.Scanner;

/**
 * Describe this class and the methods exposed by it.
 */
public class DebugServer implements ServerInterface {
    private Scanner mScanner;

    public DebugServer(){
        mScanner = new Scanner(System.in);
    }

    public void run(){
        new CuttleGame(this, 0, 1).start();
    }

    @Override
    public void update(JSONObject message, Integer playerId) {
        System.out.println("[UPDATE -> " + playerId + "]");
        System.out.println(message.toString(4));
    }

    @Override
    public JSONObject prompt(JSONObject message, Integer playerId) {
        System.out.println("[PROMPT -> " + playerId + "]");
        System.out.println(message.toString(4));
        System.out.println("Choose a call ID\n> ");
        int id = mScanner.nextInt();
        return buildPromptResponse(id);
    }

    private JSONObject buildPromptResponse(Integer callId){
        JSONObject response = new JSONObject();
        response.put("type", "prompt_response");
        response.put("id", callId);
        return response;
    }
}
