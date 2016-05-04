package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.TargetedPlayerAction;
import cuttle.game.cards.CuttleCard;

/**
 * Describe this class and the methods exposed by it.
 */
public class Discard extends TargetedPlayerAction {

    private CuttleCard mTarget;

    public Discard(CuttleCard target, Player player){
        super(target, player, "discard");
    }

    @Override
    public void act() {
        Integer index = player().hand().indexOf(target());
        CuttleCard discarded = player().hand().pop(index);
        game().scrapPile().push(discarded);
        game().updateCardPile(discarded, game().scrapPile());
    }
}
