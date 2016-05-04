package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;

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
}
