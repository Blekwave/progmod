package cuttle.game.cards.prompts;

import cuttle.game.BehaviorIterator;
import cuttle.game.Player;
import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.behaviors.BehaviorCall;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Prompt {
    public PromptType type(){
        return mType;
    }

    public ArrayList<BehaviorCall> calls(){
        return mCalls;
    }

    public JSONObject promptJSON(){
        mPromptJSON.put("calls", mCallsJSONArray);
        return mPromptJSON;
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
    }

    public void registerCall(BehaviorCall call){
        JSONObject callJSON = call.behavior().buildCallJSON(call);
        callJSON.put("id", mCalls.size());
        mCalls.add(call);
        mCallsJSONArray.put(callJSON);
    }

    public BehaviorCall getCallByIndex(Integer index){
        return mCalls.get(index);
    }

    public void prompt(Player player) {
        registerValidCalls(player);
        BehaviorCall chosenCall = player.game().serverAdapter().prompt(this, player);
        player.game().serverAdapter().update(chosenCall);
        chosenCall.call(this);
    }

    public void registerValidCalls(Player player){
        mCallsJSONArray = new JSONArray();
        BehaviorIterator it = player.behaviorIterator();

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
