package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.pointcardbehaviors.PointPlayBehavior;
import cuttle.game.cards.behaviors.pointcardbehaviors.ScuttleBehavior;
import cuttle.game.cards.behaviors.oneoffbehaviors.TwoOneOffBehavior;
import cuttle.game.cards.behaviors.reactionbehaviors.TwoReactionBehavior;

/**
 * Two in a game of Cuttle
 */
public class Two extends CuttleCard {
    /**
     * Initializes this card, associating it to a CuttleGame, its ID and its
     * suit.
     *
     * @param game CuttleGame to which this card belongs.
     * @param id Numeric ID of this card.
     * @param suit This card's suit.
     */
    public Two(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.TWO);
        bindBehavior(new PointPlayBehavior(this));
        bindBehavior(new ScuttleBehavior(this));
        bindBehavior(new TwoOneOffBehavior(this));
        bindBehavior(new TwoReactionBehavior(this));
    }
}
