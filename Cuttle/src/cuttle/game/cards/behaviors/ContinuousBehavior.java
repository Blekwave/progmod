package cuttle.game.cards.behaviors;

import cuttle.game.actions.playeractions.ContinuousPlay;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class ContinuousBehavior<T extends BehaviorCall, U extends Prompt> extends CardBehavior<T, U> {
    public ContinuousBehavior(CuttleCard card, PromptType promptType, String type){
        super(card, promptType, type);
    }

    @Override
    public void call(T call, U prompt) {
        game().perform(new ContinuousPlay(card()));
        entryEffect(call, prompt);
    }

    public abstract void entryEffect(T call, U prompt);
}
