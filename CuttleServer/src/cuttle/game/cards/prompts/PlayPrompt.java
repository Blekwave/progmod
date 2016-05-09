package cuttle.game.cards.prompts;

/**
 * Describe this class and the methods exposed by it.
 */
public class PlayPrompt extends Prompt {

    public Boolean passed(){
        return mPassed;
    }

    public void pass(){
        mPassed = true;
    }

    private Boolean mPassed;

    public PlayPrompt(){
        super(PromptType.PlayPrompt);
        mPassed = false;
    }
}
