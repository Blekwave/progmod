package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.continuousbehaviors.QueenContinuousBehavior;

/**
 * Describe this class and the methods exposed by it.
 */
public class Queen extends CuttleCard {
    public Queen(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.QUEEN);
        bindBehavior(new QueenContinuousBehavior(this));
    }
}
