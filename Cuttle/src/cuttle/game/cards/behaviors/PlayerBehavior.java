package cuttle.game.cards.behaviors;

import cuttle.game.Player;
import cuttle.game.cards.prompts.PromptType;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class PlayerBehavior<T extends BehaviorCall> extends Behavior<T> {
    public Player player(){
        return mPlayer;
    }

    private Player mPlayer;

    public PlayerBehavior(Player player, PromptType prompt, String type){
        super(player.game(), prompt, type);
        mPlayer = player;
    }

    @Override
    public JSONObject buildCallJSON(T call){
        JSONObject obj = super.buildCallJSON(call);
        obj.put("player", player().id());
        return obj;
    }

}
