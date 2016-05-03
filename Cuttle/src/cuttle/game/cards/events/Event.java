package cuttle.game.cards.events;

import cuttle.game.cards.CuttleCard;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Event {
    private Trigger mTrigger;
    private CuttleCard mCard;

    public abstract void execute();
}
