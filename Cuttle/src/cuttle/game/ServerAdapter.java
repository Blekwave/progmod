package cuttle.game;

import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.updates.UpdateContainer;
import cuttle.game.updates.UpdateInterface;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class ServerAdapter {
    private ServerInterface mServer;
    private CuttleGame mGame;

    public ServerAdapter(ServerInterface server, CuttleGame game){
        mServer = server;
        mGame = game;
    }

    public BehaviorCall prompt(Prompt prompt, Player player){
        JSONObject message = prompt.promptJSON();
        JSONObject response = mServer.prompt(message, player.id());
        Integer chosenCallId = (Integer) response.get("id");
        return prompt.getCallByIndex(chosenCallId);
    }

    public void update(UpdateInterface builder){
        UpdateContainer update = builder.buildUpdate();
        mServer.update(update.playerUpdate(), mGame.player().id());
        mServer.update(update.opponentUpdate(), mGame.opponent().id());
    }
}
