package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.gameactions.Destroy;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * One-off behavior for the Ace.
 */
public class AceOneOffBehavior extends OneOffBehavior<BehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public AceOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "ace_one_off");
    }

    /**
     * Call for an Action to destroy a card.
     *
     * @param card Target card.
     */
    private void destroy(CuttleCard card){
        game().perform(new Destroy(card));
    }

    /**
     * Destroys every point card in the board.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void activateEffect(BehaviorCall call, PlayPrompt prompt) {
        Pile p = game().player().pointBoard();
        for (int i = p.size() - 1; i >= 0; i--){
            destroy(p.get(i));
        }

        Pile o = game().opponent().pointBoard();
        for (int i = o.size() - 1; i >= 0; i--){
            destroy(o.get(i));
        }
    }

}
