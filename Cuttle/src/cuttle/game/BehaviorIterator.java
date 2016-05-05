package cuttle.game;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.Behavior;

import java.util.Iterator;

/**
 * Describe this class and the methods exposed by it.
 */
public class BehaviorIterator implements Iterator<Behavior> {

    private Player mPlayer;
    private Iterator<CuttleCard> mHandIterator;
    private Iterator<? extends Behavior> mInternalIterator;

    public BehaviorIterator(Player player){
        mPlayer = player;
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
