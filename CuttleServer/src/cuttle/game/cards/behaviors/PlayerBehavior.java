package cuttle.game.cards.behaviors;

import cuttle.game.Player;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;
import org.json.JSONObject;

/**
 * Behavior bound to a player.
 */
public abstract class PlayerBehavior<T extends BehaviorCall, U extends Prompt> extends Behavior<T, U> {
    public Player player(){
        return mPlayer;
    }

    private Player mPlayer;

    /**
     * Initializes a new Behavior, associated to a player, a prompt type and a
     * string unique identifier of its own.
     *
     * @param player CuttleCard associated to this behavior.
     * @param promptType Prompt type associated to this behavior.
     * @param type Unique string identifier for this behavior type.
     */
    public PlayerBehavior(Player player, PromptType promptType, String type){
        super(player.game(), promptType, type);
        mPlayer = player;
    }

    /**
     * Builds a JSON object regarding a call to this behavior.
     *
     * @param call BehaviorCall associated to this behavior.
     * @return JSONObject containing the behavior type, player ID, among other
     *         information.
     */
    @Override
    public JSONObject buildCallJSON(T call){
        JSONObject obj = super.buildCallJSON(call);
        obj.put("player", player().id());
        return obj;
    }

}
