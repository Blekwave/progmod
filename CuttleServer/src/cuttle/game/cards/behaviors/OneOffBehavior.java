package cuttle.game.cards.behaviors;

import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;
import cuttle.game.cards.prompts.ReactionPrompt;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class OneOffBehavior<T extends BehaviorCall> extends CardBehavior<T, PlayPrompt> {
    public OneOffBehavior(CuttleCard card, PromptType promptType, String type){
        super(card, promptType, type);
    }

    @Override
    public void call(T call, PlayPrompt prompt) {
        ReactionPrompt reactionPrompt = new ReactionPrompt();
        reactionPrompt.prompt(card().owner().opponent());
        if (!reactionPrompt.reacted()){
            activateEffect(call, prompt);
        }
        game().perform(new Discard(card()));
    }

    public abstract void activateEffect(T call, PlayPrompt prompt);
}
