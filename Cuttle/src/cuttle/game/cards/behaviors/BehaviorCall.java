package cuttle.game.cards.behaviors;

import cuttle.game.cards.prompts.Prompt;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class BehaviorCall {

    public Behavior behavior(){
        return mBehavior;
    }

    private Behavior mBehavior;

    public BehaviorCall(Behavior behavior){
        mBehavior = behavior;
    }

    public void call(Prompt p){
        mBehavior.call(this, p);
    }

    public void defineJSONProperties(JSONObject obj){}
}
