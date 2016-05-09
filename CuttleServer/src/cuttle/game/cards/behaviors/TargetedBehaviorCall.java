package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONObject;

/**
 * Stateful call for a behavior which requires a target card.
 */
public class TargetedBehaviorCall extends BehaviorCall {

    public CuttleCard target(){
        return mTarget;
    }

    private final CuttleCard mTarget;

    /**
     * Initializes a call, associated with a behavior and a target card.
     *
     * @param b Behavior to which this call is associated.
     * @param target Card targeted by this behavior call.
     */
    public TargetedBehaviorCall(Behavior b, CuttleCard target){
        super(b);
        mTarget = target;
    }

    /**
     * Defines extra properties to the JSON object associated with the behavior
     * call which are related to this call's state, i.e. the target card's lo-
     * cation and index.
     *
     * @param obj JSONObject to be modified.
     */
    @Override
    public void defineJSONProperties(JSONObject obj){
        Pile targetPile = target().game().cardPile(target());
        obj.put("target_location", targetPile.name());
        obj.put("target_index", targetPile.indexOf(target()));
    }
}
