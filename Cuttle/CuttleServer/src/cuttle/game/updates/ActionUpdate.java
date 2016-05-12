package cuttle.game.updates;

import cuttle.game.actions.Action;

/**
 * Update sent regarding an action which has been executed by the game.
 */
public class ActionUpdate implements UpdateInterface {

    private Action mAction;

    /**
     * Initializes the update.
     *
     * @param action Action which was executed.
     */
    public ActionUpdate(Action action){
        mAction = action;
    }

    /**
     * Generates the update container.
     *
     * @return UpdateContainer for the update.
     */
    @Override
    public UpdateContainer buildUpdate() {
        return new UpdateContainer(mAction.buildPlayerUpdate(), mAction.buildOpponentUpdate());
    }
}
