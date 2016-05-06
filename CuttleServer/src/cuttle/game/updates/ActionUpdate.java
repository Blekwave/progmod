package cuttle.game.updates;

import cuttle.game.actions.Action;

/**
 * Describe this class and the methods exposed by it.
 */
public class ActionUpdate implements UpdateInterface {

    private Action mAction;

    public ActionUpdate(Action action){
        mAction = action;
    }

    @Override
    public UpdateContainer buildUpdate() {
        return new UpdateContainer(mAction.buildPlayerUpdate(), mAction.buildOpponentUpdate());
    }
}
