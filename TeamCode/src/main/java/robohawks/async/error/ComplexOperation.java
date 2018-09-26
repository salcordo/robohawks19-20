package robohawks.async.error;

import robohawks.async.Operation;
import robohawks.async.Sequencer;

/**
 * Created by fchoi on 1/27/2017.
 */
public abstract class ComplexOperation implements Operation{
    protected Sequencer sequencer;
    public ComplexOperation (Sequencer sequencer) {
        this.sequencer = sequencer;
    }
}
