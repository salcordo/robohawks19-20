package robohawks.modules.base;

import com.qualcomm.robotcore.util.ElapsedTime;
import robohawks.async.Operation;
import robohawks.async.Sequence;

/**
 * Created by fchoi on 1/14/2017.
 */
public class WaitModule implements Operation {
    private ElapsedTime time;
    private double startTime;
    private double waitTime;

    public WaitModule(double waitTime) {
        time = new ElapsedTime();
        this.waitTime = waitTime;
    }

    @Override
    public void start(Sequence.Callback callback) {
        startTime = time.milliseconds();
    }

    @Override
    public void loop(Sequence.Callback callback) {
        if(time.milliseconds() > startTime + waitTime) {
            callback.next();
        }
    }

    @Override
    public void stop(Sequence.Callback callback) {
        callback.next();
    }
}
