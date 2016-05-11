package cuttle.game.cards.behaviors.reactionbehaviors;

import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.prompts.PromptType;
import cuttle.game.cards.prompts.ReactionPrompt;

import java.util.ArrayList;

/**
 * Reaction behavior for the Two.
 */
public class TwoReactionBehavior extends CardBehavior<BehaviorCall, ReactionPrompt> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public TwoReactionBehavior(CuttleCard card){
        super(card, PromptType.ReactionPrompt, "two_reaction");
    }

    /**
     * Lists valid calls for the Two as a reaction card, i.e., registers a sin-
     * gle call if the opponent is not protected.
     *
     * @return List of valid calls.
     */
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
        game().perform(new Discard(card()));
    }
}
