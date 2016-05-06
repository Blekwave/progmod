package cuttle.game.cards.prompts;

/**
 * Describe this class and the methods exposed by it.
 */
public class ReactionPrompt extends Prompt {
    public Boolean reacted(){
        return mReacted;
    }

    public void react(){
        mReacted = true;
    }

    private Boolean mReacted;

    public ReactionPrompt(){
        super(PromptType.ReactionPrompt);
        mReacted = false;
    }
}
