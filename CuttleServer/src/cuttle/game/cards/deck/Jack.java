package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.continuousbehaviors.JackContinuousBehavior;

/**
 * Jack in a game of Cuttle.
 */
public class Jack extends CuttleCard {
    /**
     * Initializes this card, associating it to a CuttleGame, its ID and its
     * suit.
     *
     * @param game CuttleGame to which this card belongs.
     * @param id Numeric ID of this card.
     * @param suit This card's suit.
     */
    public Jack(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.JACK);
        bindBehavior(new JackContinuousBehavior(this));
    }
}
