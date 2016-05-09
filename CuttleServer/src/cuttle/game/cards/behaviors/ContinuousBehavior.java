package cuttle.game.cards.behaviors;

import cuttle.game.actions.playeractions.ContinuousPlay;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Behavior related to a card's continuous play.
 */
public abstract class ContinuousBehavior<T extends BehaviorCall> extends CardBehavior<T, PlayPrompt> {

    public ContinuousBehavior(CuttleCard card, PromptType promptType, String type){
        super(card, promptType, type);
    }

    /**
     * Plays the card as a continuous card and calls its entry effect.
     *
     * @param call BehaviorCall to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void call(T call, PlayPrompt prompt) {
        game().perform(new ContinuousPlay(card()));
        entryEffect(call, prompt);
    }

    /**
     * Effect triggered when a continuous card enters the board.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    public abstract void entryEffect(T call, PlayPrompt prompt);
}
