package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.continuousbehaviors.EightContinuousBehavior;
import cuttle.game.cards.behaviors.pointcardbehaviors.PointPlayBehavior;
import cuttle.game.cards.behaviors.pointcardbehaviors.ScuttleBehavior;

/**
 * Eight in a game of Cuttle.
 */
public class Eight extends CuttleCard {
    /**
     * Initializes this card, associating it to a CuttleGame, its ID and its
     * suit.
     *
     * @param game CuttleGame to which this card belongs.
     * @param id Numeric ID of this card.
     * @param suit This card's suit.
     */
    public Eight(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.EIGHT);
        bindBehavior(new PointPlayBehavior(this));
        bindBehavior(new ScuttleBehavior(this));
        bindBehavior(new EightContinuousBehavior(this));
    }
}
