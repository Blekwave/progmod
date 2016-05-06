package cuttle.game.cards.behaviors.reactionbehaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.prompts.PromptType;
import cuttle.game.cards.prompts.ReactionPrompt;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class TwoReactionBehavior extends CardBehavior<BehaviorCall, ReactionPrompt> {
    public TwoReactionBehavior(CuttleCard card){
        super(card, PromptType.ReactionPrompt, "two_reaction");
    }

    @Override
    public ArrayList<BehaviorCall> listValidCalls() {
        ArrayList<BehaviorCall> list = new ArrayList<>();
        if (!card().owner().opponent().isProtected()){
            list.add(new BehaviorCall(this));
        }
        return list;
    }

    @Override
    public void call(BehaviorCall call, ReactionPrompt prompt) {
        prompt.react();
    }
}
