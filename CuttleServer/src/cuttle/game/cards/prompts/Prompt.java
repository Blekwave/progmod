package cuttle.game.cards.prompts;

import cuttle.game.BehaviorIterator;
import cuttle.game.Player;
import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.behaviors.BehaviorCall;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Prompt which can prompt a player for a decision regarding a specific situ-
 * ation and get their response, which is a behavior call to be executed.
 */
public abstract class Prompt {
    public PromptType type(){
        return mType;
    }

    public ArrayList<BehaviorCall> calls(){
        return mCalls;
    }

    /**
     * Generates a JSON object containing information regarding the prompt, to
     * be relayed to the prompted player.
     *
     * The object contains a list of the possible behavior calls to be made.
     *
     * @return JSONObject to be sent to the player.
     */
    public JSONObject promptJSON(){
        mPromptJSON.put("calls", mCallsJSONArray);
        return mPromptJSON;
    }

    private final PromptType mType;

    private ArrayList<BehaviorCall> mCalls;
    private JSONObject mPromptJSON;
    private JSONArray mCallsJSONArray;

    /**
     * Initializes the prompt.
     *
     * @param type Type of the prompt, also defined in the behaviors which cor-
     *             respond to it.
     */
    public Prompt(PromptType type){
        mType = type;
        mCalls = new ArrayList<>();
        mPromptJSON = new JSONObject();
        mPromptJSON.put("type", "prompt");
        mPromptJSON.put("prompt_type", type.string());
    }

    /**
     * Registers a behavior call to the list of valid calls for this prompt.
     *
     * @param call BehaviorCall to be registered.
     */
    public void registerCall(BehaviorCall call){
        JSONObject callJSON = call.behavior().buildCallJSON(call);
        callJSON.put("id", mCalls.size());
        mCalls.add(call);
        mCallsJSONArray.put(callJSON);
    }

    /**
     * Gets a BehaviorCall from the list by its index in the list of calls.
     *
     * @param index Index in the list of calls.
     * @return BehaviorCall corresponding to the index in this prompt.
     */
    public BehaviorCall getCallByIndex(Integer index){
        return mCalls.get(index);
    }

    public void prompt(Player player) {
        mCallsJSONArray = new JSONArray();
        registerValidCalls(player);
        BehaviorCall chosenCall = player.game().serverAdapter().prompt(this, player);
        player.game().serverAdapter().update(chosenCall);
        chosenCall.call(this);
    }

    /**
     * Registers all valid calls from a player and their cards to this prompt.
     *
     * @param player Player whose Behaviors should be iterated.
     */
    public void registerValidCalls(Player player){
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
