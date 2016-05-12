package cuttle.game;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.Behavior;

import java.util.Iterator;

/**
 * Iterator over a player's behaviors and every one of its cards' behaviors.
 */
public class BehaviorIterator implements Iterator<Behavior> {

    private Iterator<CuttleCard> mHandIterator;
    private Iterator<? extends Behavior> mInternalIterator;

    /**
     * Initializes the iterator from a player's cards and behaviors.
     *
     * @param player Player over whose behaviors the iterator should iterate.
     */
    public BehaviorIterator(Player player){
        mHandIterator = player.hand().iterator();
        mInternalIterator = player.behaviors().iterator();
    }

    @Override
    public boolean hasNext() {
        return mInternalIterator.hasNext() || mHandIterator.hasNext();
    }

    @Override
    public Behavior next() {
        if (!mInternalIterator.hasNext()){
            mInternalIterator = mHandIterator.next().behaviors().iterator();
        }
        return mInternalIterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
