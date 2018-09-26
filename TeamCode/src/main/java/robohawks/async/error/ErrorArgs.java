package robohawks.async.error;

/**
 * Created by fchoi on 9/25/2016.
 */
public class ErrorArgs {
    public Object sender;
    public String error;
    public String message;

    public ErrorArgs(Object sender) {
        this.sender = sender;
    }
}
