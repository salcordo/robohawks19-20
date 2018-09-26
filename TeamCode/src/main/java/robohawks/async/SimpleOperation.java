package robohawks.async;

/**
 * Created by fchoi on 12/14/2016.
 */
public abstract class SimpleOperation implements Operation {
    @Override
    public void loop(Sequence.Callback callback) {
        callback.next();
    }

    @Override
    public void stop(Sequence.Callback callback) {
        callback.next();
    }
}
