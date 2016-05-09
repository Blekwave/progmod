package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.playeractions.Recover;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * One-off behavior for the Three.
 */
public class ThreeOneOffBehavior extends OneOffBehavior<TargetedBehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public ThreeOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "three_one_off");
    }

    /**
     * Lists valid calls for playing the Three as a one-off card, i.e., a play
     * for every card in the scrap pile.
     *
     * @return List of valid calls.
     */
    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        for (CuttleCard c : game().scrapPile()){
            list.add(new TargetedBehaviorCall(this, c));
        }

        return list;
    }

    /**
     * Recovers the target card from the scrap pile and gives it to the Three's
     * player.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void activateEffect(TargetedBehaviorCall call, PlayPrompt prompt) {
        game().perform(new Recover(call.target(), game().player()));
    }
}
