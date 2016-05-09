package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.TargetedPlayerAction;
import cuttle.game.cards.CuttleCard;

/**
 * Takes a card from the discard pile and gives it to a player.
 */
public class Recover extends TargetedPlayerAction {
    public Recover(CuttleCard card, Player player){
        super(card, player, "recover");
    }

    @Override
    public void act() {
        targetPile().pop(targetPileIndex());
        player().hand().push(target());
        game().updateCardPile(target(), player().hand());
    }
}
