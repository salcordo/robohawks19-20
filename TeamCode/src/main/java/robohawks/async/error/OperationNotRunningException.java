package robohawks.async.error;

/**
 * Created by fchoi on 9/26/2016.
 */
public class OperationNotRunningException extends ErrorArgs {
    public OperationNotRunningException(Object sender) {
        super(sender);
        this.error = "OperationNotRunningException";
        this.message = "Could not stop operation: operation not running";
    }
}
