package cuttle.game.cards.prompts;

import cuttle.game.Player;
import cuttle.game.cards.behaviors.BehaviorCall;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Prompt {
    private final PromptType mType;

    private ArrayList<BehaviorCall> mCalls;
    private JSONObject mPromptJSON;
    private JSONArray mCallsJSONArray;

    public Prompt(PromptType type){
        mType = type;
        mCalls = new ArrayList<>();
        mPromptJSON = new JSONObject();
        mPromptJSON.put("type", "prompt");
        mPromptJSON.put("prompt_type", type.string());
        mCallsJSONArray = new JSONArray();
    }

    public void registerCall(BehaviorCall call){
        JSONObject callJSON = call.behavior().buildCallJSON(call);
        callJSON.put("id", mCalls.size());
        mCalls.add(call);
        mCallsJSONArray.put(callJSON);
    }

    public abstract void prompt(Player p);
}
