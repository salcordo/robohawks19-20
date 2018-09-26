package robohawks.async;

/**
 * Created by fchoi on 1/27/2017.
 */
public abstract class LoopOperation implements Operation{
    @Override
    public void start(Sequence.Callback callback) {

    }

    @Override
    public void stop(Sequence.Callback callback) {
        callback.next();
    }
}
