package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.pointcardbehaviors.PointPlayBehavior;
import cuttle.game.cards.behaviors.pointcardbehaviors.ScuttleBehavior;
import cuttle.game.cards.behaviors.oneoffbehaviors.TwoOneOffBehavior;
import cuttle.game.cards.behaviors.reactionbehaviors.TwoReactionBehavior;

/**
 * Describe this class and the methods exposed by it.
 */
public class Two extends CuttleCard {
    public Two(CuttleGame game, Integer id, Suit suit){
        super(game, id, suit, Rank.TWO);
        bindBehavior(new PointPlayBehavior(this));
        bindBehavior(new ScuttleBehavior(this));
        bindBehavior(new TwoOneOffBehavior(this));
        bindBehavior(new TwoReactionBehavior(this));
    }
}
