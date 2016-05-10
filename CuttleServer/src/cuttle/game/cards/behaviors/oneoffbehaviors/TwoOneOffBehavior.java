package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.gameactions.Destroy;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.PlayingCard;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * One-off behavior for the Two.
 */
public class TwoOneOffBehavior extends OneOffBehavior<TargetedBehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public TwoOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "two_one_off");
    }

    /**
     * Lists valid calls for playing the Two as a one-off card, i.e., a call
     * for every card in the opponent's continuous board.
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
     * Destroys the chosen card.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void activateEffect(TargetedBehaviorCall call, PlayPrompt prompt) {
        game().perform(new Destroy(call.target()));
    }
}
