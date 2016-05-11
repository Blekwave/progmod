package cuttle.game.cards.behaviors;

import cuttle.game.CuttleGame;
import cuttle.game.prompts.Prompt;
import cuttle.game.prompts.PromptType;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Occurrence bound to a card or player at their initialization. Executed in
 * reaction to a Prompt of the same type as the Behavior.
 */
public abstract class Behavior<T extends BehaviorCall, U extends Prompt> {

    public CuttleGame game(){
        return mGame;
    }

    public PromptType promptType(){
        return mPromptType;
    }

    private CuttleGame mGame;
    private PromptType mPromptType;
    private String mType;

    /**
     * Initializes a new Behavior, associated to a game, a prompt type and a
     * string unique identifier of its own.
     *
     * @param game CuttleGame associated to this behavior.
     * @param promptType Prompt type associated to this behavior.
     * @param type Unique string identifier for this behavior type.
     */
    public Behavior(CuttleGame game, PromptType promptType, String type){
        mGame = game;
        mPromptType = promptType;
        mType = type;
    }

    /**
     * Generates a list of valid behavior calls for this behavior, according
     * to the state of the game.
     *
     * The default behavior (defined here) is to add a single generic call,
     * but this is overridden by many subclasses.
     *
     * @return List of valid calls for this behavior.
     */
    public ArrayList<? extends BehaviorCall> listValidCalls() {
        ArrayList<BehaviorCall> list = new ArrayList<>();
        list.add(new BehaviorCall(this));
        return list;
    }

    /**
     * Builds a JSON object regarding a call to this behavior.
     *
     * @param call BehaviorCall associated to this behavior.
     * @return JSONObject containing the behavior type among other information.
     */
    public JSONObject buildCallJSON(T call){
        JSONObject obj = new JSONObject();
        call.defineJSONProperties(obj);
        obj.put("type", "behavior_update");
        obj.put("behavior_type", mType);
        return obj;
    }

    /**
     * Executes the actions associated to this behavior, given a call and a
     * prompt.
     *
     * @param call BehaviorCall to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    public abstract void call(T call, U prompt);
}
