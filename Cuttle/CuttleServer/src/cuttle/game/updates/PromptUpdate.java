package cuttle.game.updates;

import cuttle.game.Player;
import cuttle.game.prompts.PromptType;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class PromptUpdate implements UpdateInterface {

    private PromptType mPromptType;
    private Player mPlayer;

    public PromptUpdate(PromptType promptType, Player player){
        mPromptType = promptType;
        mPlayer = player;
    }

    @Override
    public SymmetricUpdateContainer buildUpdate() {
        JSONObject obj = new JSONObject();
        obj.put("type", "prompt_update");
        obj.put("prompt_type", mPromptType.string());
        obj.put("player", mPlayer.id());
        return new SymmetricUpdateContainer(obj);
    }
}
