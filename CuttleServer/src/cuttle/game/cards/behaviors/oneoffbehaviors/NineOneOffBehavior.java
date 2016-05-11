package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.gameactions.Return;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.PlayingCard;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.prompts.PlayPrompt;
import cuttle.game.prompts.PromptType;

import java.util.ArrayList;

/**
 * One-off behavior for the Nine.
 */
public class NineOneOffBehavior extends OneOffBehavior<TargetedBehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public NineOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "nine_one_off");
    }

    /**
     * Lists valid calls for playing the Nine as a one-off card, i.e., a call
     * for every continuous card in the opponent's board.
     *
     * @return List of valid calls.
     */
    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        for (CuttleCard c : game().opponent().continuousBoard()){
            if (c.rank().equals(PlayingCard.Rank.QUEEN) || !game().opponent().isProtected()){
                list.add(new TargetedBehaviorCall(this, c));
            }
        }

        return list;
    }

    /**
     * Returns the target card to the top of the deck.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void activateEffect(TargetedBehaviorCall call, PlayPrompt prompt) {
        game().perform(new Return(call.target()));
    }
}
