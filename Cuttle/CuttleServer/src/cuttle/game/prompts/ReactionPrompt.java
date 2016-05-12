package cuttle.game.prompts;

/**
 * Prompt triggered when a player can react to another's one-off play.
 */
public class ReactionPrompt extends Prompt {
    public Boolean reacted(){
        return mReacted;
    }

    /**
     * Called to specify that the prompted player has reacted to his opponent's
     * play.
     */
    public void react(){
        mReacted = true;
    }

    private Boolean mReacted;

    /**
     * Initializes the prompt and defines its type.
     */
    public ReactionPrompt(){
        super(PromptType.ReactionPrompt);
        mReacted = false;
    }
}
