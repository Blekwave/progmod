package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.oneoffbehaviors.FourOneOffBehavior;
import cuttle.game.cards.behaviors.pointcardbehaviors.PointPlayBehavior;
import cuttle.game.cards.behaviors.pointcardbehaviors.ScuttleBehavior;

/**
 * Describe this class and the methods exposed by it.
 */
public class Four extends CuttleCard {
    public Four(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.FOUR);
        bindBehavior(new PointPlayBehavior(this));
        bindBehavior(new ScuttleBehavior(this));
        bindBehavior(new FourOneOffBehavior(this));
    }
}
