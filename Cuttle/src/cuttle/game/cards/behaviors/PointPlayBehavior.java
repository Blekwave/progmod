package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class PointPlayBehavior extends Behavior {

    public PointPlayBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt);
    }

    @Override
    public ArrayList<BehaviorCall> listValidCalls() {
        ArrayList<BehaviorCall> l = new ArrayList<>();
        l.add(new BehaviorCall(this));
        return l;
    }

    @Override
    public void call(BehaviorCall c, Prompt p) {
        // ...
    }
}
