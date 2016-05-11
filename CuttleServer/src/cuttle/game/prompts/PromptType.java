package cuttle.game.prompts;

/**
 * Types of prompts, defined in the prompts themselves and in the behaviors
 * which correspond to a specific type of prompt.
 */
public enum PromptType {
    PlayPrompt("play_prompt"),
    ReactionPrompt("reaction_prompt"),
    DiscardPrompt("discard_prompt");

    public String string(){
        return mString;
    }

    private String mString;

    /**
     * Initializes a prompt type.
     *
     * @param string Unique string identifier for this prompt type.
     */
    PromptType(String string){
        mString = string;
    }
}
