package cuttle.game;

import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.updates.PromptUpdate;
import cuttle.game.updates.UpdateContainer;
import cuttle.game.updates.UpdateInterface;
import org.json.JSONObject;

/**
 * Adapter between the server interface and the game's events.
 *
 * This adapter is responsible for mapping in-game objects to the interface's
 * protocol, i.e., getting a prompt or update's JSON objects, the players' IDs
 * and sending the appropriate requests to the Interface.
 */
public class ServerAdapter {
    private ServerInterface mServer;
    private CuttleGame mGame;

    /**
     * Initializes the adapter between a server and a CuttleGame.
     *
     * @param server Server with which the game should communicate.
     * @param game Game of cuttle.
     */
    public ServerAdapter(ServerInterface server, CuttleGame game){
        mServer = server;
        mGame = game;
    }

    /**
     * Sends a prompt to a player.
     *
     * @param prompt Prompt to be sent.
     * @param player Player to which the prompt should be sent.
     * @return call chosen by the player in response to the prompt.
     */
    public BehaviorCall prompt(Prompt prompt, Player player) {
        JSONObject message = prompt.promptJSON();
        update(new PromptUpdate(prompt.type(), player));
        JSONObject response = mServer.prompt(message, player.id());
        Integer chosenCallId = (Integer) response.get("id");
        return prompt.getCallByIndex(chosenCallId);
    }

    /**
     * Sends an update to both players.
     *
     * @param builder UpdateInterface from which the update's JSONs will be
     *                generated.
     */
    public void update(UpdateInterface builder){
        UpdateContainer update = builder.buildUpdate();
        mServer.update(update.playerUpdate(), mGame.player().id());
        mServer.update(update.opponentUpdate(), mGame.opponent().id());
    }
}
