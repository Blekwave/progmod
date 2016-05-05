package cuttle.game.cards.behaviors;

import cuttle.game.CuttleGame;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
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

    public Behavior(CuttleGame game, PromptType promptType, String type){
        mGame = game;
        mPromptType = promptType;
        mType = type;
    }

    public ArrayList<? extends BehaviorCall> listValidCalls() {
        ArrayList<BehaviorCall> list = new ArrayList<>();
        list.add(new BehaviorCall(this));
        return list;
    }

    public JSONObject buildCallJSON(T call){
        JSONObject obj = new JSONObject();
        call.defineJSONProperties(obj);
        obj.put("behavior_type", mType);
        return obj;
    }

    public abstract void call(T call, U prompt);
}
