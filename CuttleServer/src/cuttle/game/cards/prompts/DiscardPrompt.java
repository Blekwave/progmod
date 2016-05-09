package cuttle.game.cards.prompts;

/**
 * Prompt triggered when the opponent plays a one-off four. Forces the player
 * to discard a card.
 */
public class DiscardPrompt extends Prompt {
    public DiscardPrompt(){
        super(PromptType.DiscardPrompt);
    }
}
