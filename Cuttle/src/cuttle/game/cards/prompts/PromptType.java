package cuttle.game.cards.prompts;

/**
 * Describe this class and the methods exposed by it.
 */
public enum PromptType {
    PlayPrompt("play_prompt"),
    ReactionPrompt("reaction_prompt"),
    DiscardPrompt("discard_prompt");

    public String string(){
        return mString;
    }

    private String mString;

    PromptType(String string){
        mString = string;
    }
}
