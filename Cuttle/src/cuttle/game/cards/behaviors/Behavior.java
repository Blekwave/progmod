package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Behavior {
    private CuttleCard mCard;
    private PromptType mPromptType;

    public Behavior(CuttleCard card, PromptType promptType){
        mCard = card;
        mPromptType = promptType;
    }

    public abstract ArrayList<BehaviorCall> listValidCalls();

    public abstract void call(BehaviorCall c, Prompt p);
}
