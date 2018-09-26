package robohawks.async;

/**
 * Created by fchoi on 9/25/2016.
 */
public interface Operation {
    void start(Sequence.Callback callback);

    void loop(Sequence.Callback callback);

    void stop(Sequence.Callback callback);
}
