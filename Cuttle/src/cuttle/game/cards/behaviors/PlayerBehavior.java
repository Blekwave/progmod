package cuttle.game.cards.behaviors;

import cuttle.game.Player;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class PlayerBehavior<T extends BehaviorCall> extends Behavior<T> {
    private Player mPlayer;

    public PlayerBehavior(Player player, PromptType prompt){
        super(prompt);
        mPlayer = player;
    }

    public Player player(){
        return mPlayer;
    }
}
