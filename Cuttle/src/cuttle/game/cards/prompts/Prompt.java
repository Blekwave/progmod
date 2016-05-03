package cuttle.game.cards.prompts;

import cuttle.game.Player;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Prompt {
    private final PromptType mType;

    public Prompt(PromptType type){
        mType = type;
    }

    public abstract void prompt(Player p);
}
