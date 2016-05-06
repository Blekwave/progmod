package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class TargetedBehaviorCall extends BehaviorCall {
    private final CuttleCard mTarget;

    public TargetedBehaviorCall(Behavior b, CuttleCard target){
        super(b);
        mTarget = target;
    }

    public CuttleCard target(){
        return mTarget;
    }

    @Override
    public void defineJSONProperties(JSONObject obj){
        Pile targetPile = target().game().cardPile(target());
        obj.put("target_location", targetPile.name());
        obj.put("target_index", targetPile.indexOf(target()));
    }
}
