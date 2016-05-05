package cuttle.game.cards.behaviors.pointcardbehaviors;

import cuttle.game.Player;
import cuttle.game.actions.gameactions.Destroy;
import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class ScuttleBehavior extends CardBehavior<TargetedBehaviorCall> {
    public ScuttleBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "scuttle");
    }

    private Integer scuttleValue(CuttleCard c){
        return c.rank().value() * 4 + c.suit().value();
    }

    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        Pile opponentsBoard = card().game().opponent().pointBoard();
        for (CuttleCard c : opponentsBoard){
            if (scuttleValue(this.card()) > scuttleValue(c)){
                list.add(new TargetedBehaviorCall(this, c));
            }
        }

        return list;
    }

    @Override
    public void call(TargetedBehaviorCall c, Prompt p) {
        Player cardOwner = game().cardPile(card()).owner();
        game().perform(new Discard(card(), cardOwner));
        game().perform(new Destroy(c.target()));
    }
}
