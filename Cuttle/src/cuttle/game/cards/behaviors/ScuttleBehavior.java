package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
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
        Pile opponentsBoard = card().game().opponent().pointBoard();
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        for (CuttleCard c : opponentsBoard){
            if (scuttleValue(this.card()) > scuttleValue(c)){
                list.add(new TargetedBehaviorCall(this, c));
            }
        }

        return list;
    }

    @Override
    public void call(TargetedBehaviorCall c, Prompt p) {
        // ...
    }
}
