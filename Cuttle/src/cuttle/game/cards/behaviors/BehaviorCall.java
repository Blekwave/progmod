package cuttle.game.cards.behaviors;

import cuttle.game.cards.prompts.Prompt;

/**
 * Describe this class and the methods exposed by it.
 */
public class BehaviorCall {
    private Behavior mBehavior;
    public BehaviorCall(Behavior behavior){
        mBehavior = behavior;
    }

    public void call(Prompt p){
        mBehavior.call(this, p);
    }
}
