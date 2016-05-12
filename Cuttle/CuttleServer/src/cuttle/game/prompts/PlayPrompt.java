package cuttle.game.prompts;

/**
 * Prompt triggered at the beginning of every turn, allows a player to make a
 * regular play.
 */
public class PlayPrompt extends Prompt {

    public Boolean passed(){
        return mPassed;
    }

    /**
     * Called to specify that a player has passed his turn (attempted to draw a
     * card from an empty deck of cards).
     */
    public void pass(){
        mPassed = true;
    }

    private Boolean mPassed;

    /**
     * Initializes the prompt and defines its type.
     */
    public PlayPrompt(){
        super(PromptType.PlayPrompt);
        mPassed = false;
    }
}
