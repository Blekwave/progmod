package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.continuousbehaviors.KingContinuousBehavior;

/**
 * Describe this class and the methods exposed by it.
 */
public class King extends CuttleCard {
    public King(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.KING);
        bindBehavior(new KingContinuousBehavior(this));
    }
}
