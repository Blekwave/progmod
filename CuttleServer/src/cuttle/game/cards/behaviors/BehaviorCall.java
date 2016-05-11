package cuttle.game.cards.behaviors;

import cuttle.game.updates.SymmetricUpdateContainer;
import cuttle.game.prompts.Prompt;
import cuttle.game.updates.UpdateInterface;
import org.json.JSONObject;

/**
 * Stateful call to a Behavior.
 */
public class BehaviorCall implements UpdateInterface {

    public Behavior behavior(){
        return mBehavior;
    }

    private Behavior mBehavior;

    /**
     * Initializes the call, associated to a behavior.
     *
     * @param behavior Behavior to which this call is associated.
     */
    public BehaviorCall(Behavior behavior){
        mBehavior = behavior;
    }

    /**
     * Calls the behavior, passing this call object as parameter.
     *
     * @param p Prompt associated with the call.
     */
    public void call(Prompt p){
        mBehavior.call(this, p);
    }

    /**
     * Defines extra properties in the JSON object associated with the behavior
     * call which are related to this call's state.
     *
     * The regular BehaviorCall defines no extra properties. Subclasses may
     * override this behavior.
     *
     * @param obj JSONObject to be modified.
     */
    public void defineJSONProperties(JSONObject obj){}

    /**
     * Generates an update container for this behavior call's behavior update.
     *
     * @return UpdateContainer for the update.
     */
    @Override
    public SymmetricUpdateContainer buildUpdate(){
        return new SymmetricUpdateContainer(behavior().buildCallJSON(this));
    }

}
