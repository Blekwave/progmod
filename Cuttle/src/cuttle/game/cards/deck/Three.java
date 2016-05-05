package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.oneoffbehaviors.ThreeOneOffBehavior;
import cuttle.game.cards.behaviors.pointcardbehaviors.PointPlayBehavior;
import cuttle.game.cards.behaviors.pointcardbehaviors.ScuttleBehavior;

/**
 * Describe this class and the methods exposed by it.
 */
public class Three extends CuttleCard {
    public Three(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.THREE);
        bindBehavior(new PointPlayBehavior(this));
        bindBehavior(new ScuttleBehavior(this));
        bindBehavior(new ThreeOneOffBehavior(this));
    }
}
