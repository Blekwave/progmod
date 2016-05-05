package cuttle.game.cards.prompts;

import cuttle.game.Player;
import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.behaviors.BehaviorCall;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Prompt {
    public PromptType type(){
        return mType;
    }

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

    public void prompt(Player player) {
        registerValidCalls(player);
        BehaviorCall chosenCall = player.game().serverInterface().prompt(this);
        chosenCall.call(this);
    }

    public void registerValidCalls(Player player){
        Iterator<Behavior> it = player.behaviorIterator();

        while (it.hasNext()){
            Behavior<? extends BehaviorCall, Prompt> b = it.next();
            if (b.promptType().equals(type())){
                for (BehaviorCall c : b.listValidCalls()){
                    registerCall(c);
                }
            }
        }
    }
}
