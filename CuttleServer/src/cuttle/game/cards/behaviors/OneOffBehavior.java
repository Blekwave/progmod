package cuttle.game.cards.behaviors;

import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;
import cuttle.game.cards.prompts.ReactionPrompt;

/**
 * Behavior related to a card's one-off play.
 */
public abstract class OneOffBehavior<T extends BehaviorCall> extends CardBehavior<T, PlayPrompt> {
    public OneOffBehavior(CuttleCard card, PromptType promptType, String type){
        super(card, promptType, type);
    }

    /**
     * Prompts the opponent for a reaction. If there's no reaction, executes
     * the card's one-off effect. Finally, the card is discarded.
     *
     * @param call BehaviorCall to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void call(T call, PlayPrompt prompt) {
        game().perform(new Discard(card()));
        ReactionPrompt reactionPrompt = new ReactionPrompt();
        reactionPrompt.prompt(card().owner().opponent());
        if (!reactionPrompt.reacted()){
            activateEffect(call, prompt);
        }
    }

    /**
     * Effect triggered when a one-off card is played.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    public abstract void activateEffect(T call, PlayPrompt prompt);
}
