package robohawks.async.error;

import robohawks.async.Sequence;
import robohawks.async.error.ErrorArgs;

/**
 * Created by fchoi on 9/25/2016.
 */
public interface ErrorHandler {
    void handleError(Sequence sequence, ErrorArgs error);
}
