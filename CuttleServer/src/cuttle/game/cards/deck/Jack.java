package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.continuousbehaviors.JackContinuousBehavior;

/**
 * Describe this class and the methods exposed by it.
 */
public class Jack extends CuttleCard {
    public Jack(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.JACK);
        bindBehavior(new JackContinuousBehavior(this));
    }
}
