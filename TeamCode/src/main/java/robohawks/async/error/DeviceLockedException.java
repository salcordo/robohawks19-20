package robohawks.async.error;

/**
 * Created by fchoi on 9/25/2016.
 */
public class DeviceLockedException extends ErrorArgs{
    public DeviceLockedException(Object sender) {
        super(sender);

        error = "DeviceLockedException";
        message = "Failed to start device: Device already in use";
    }
}
