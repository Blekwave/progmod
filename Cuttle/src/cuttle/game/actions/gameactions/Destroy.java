package cuttle.game.actions.gameactions;

import cuttle.game.actions.TargetedAction;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class Destroy extends TargetedAction{
    private CuttleCard mTarget;

    public Destroy(CuttleCard target){
        super(target, "destroy");
    }

    @Override
    public void act(){
        // ...
    }
}
