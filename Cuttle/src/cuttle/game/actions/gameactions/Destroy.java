package cuttle.game.actions.gameactions;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.actions.Action;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class Destroy extends Action {
    private CuttleCard mCard;

    public Destroy(CuttleCard card){
        super(card.game(), "destroy");
        mCard = card;
    }

    @Override
    public void act(){
        // ...
    }

    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();
        Pile pile = game().cardPile(mCard);

        obj.put("target_pile", pile.name());
        obj.put("target_pile_index", pile.indexOf(mCard));
        return obj;
    }

    @Override
    public JSONObject buildOpponentUpdate() {
        return buildPlayerUpdate();
    }
}
