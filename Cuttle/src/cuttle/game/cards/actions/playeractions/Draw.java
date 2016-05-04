package cuttle.game.cards.actions.playeractions;

import cuttle.game.CuttleGame;
import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.actions.PlayerAction;

/**
 * Describe this class and the methods exposed by it.
 */
public class Draw extends PlayerAction {
    public Draw(Player player){
        super(player, "draw");
    }

    @Override
    public void act() {
        CuttleCard drawn = game().deck().pop();
        player().hand().push(drawn);
        game().updateCardPile(drawn, player().hand());
    }
}
