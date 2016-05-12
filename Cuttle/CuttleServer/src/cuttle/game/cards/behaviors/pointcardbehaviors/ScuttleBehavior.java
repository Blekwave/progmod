package cuttle.game.cards.behaviors.pointcardbehaviors;

import cuttle.game.actions.gameactions.Destroy;
import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.prompts.PlayPrompt;
import cuttle.game.prompts.PromptType;

import java.util.ArrayList;

/**
 * Scuttle behavior.
 */
public class ScuttleBehavior extends CardBehavior<TargetedBehaviorCall, PlayPrompt> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public ScuttleBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "scuttle");
    }

    /**
     * Computes a card's scuttle value, accounting for its suit and rank. A
     * card can scuttle cards of lower scuttle value than its own.
     *
     * @param c Card whose scuttle value is desired.
     * @return Card's scuttle value.
     */
    private Integer scuttleValue(CuttleCard c){
        return c.rank().value() * 4 + c.suit().value();
    }

    /**
     * List valid scuttle calls.
     *
     * @return List of valid calls.
     */
    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        Pile opponentsBoard = game().opponent().pointBoard();
        for (CuttleCard c : opponentsBoard){
            if (scuttleValue(this.card()) > scuttleValue(c)){
                list.add(new TargetedBehaviorCall(this, c));
            }
        }

        return list;
    }

    /**
     * Discards the card associated with this behavior and destroys the target.
     *
     * @param c BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void call(TargetedBehaviorCall c, PlayPrompt prompt) {
        game().perform(new Discard(card()));
        game().perform(new Destroy(c.target()));
    }
}
