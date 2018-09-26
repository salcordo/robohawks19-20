package robohawks.async;

import robohawks.async.error.ErrorArgs;
import robohawks.async.error.ErrorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fchoi on 9/25/2016.
 */
public class Sequence {
    private Sequencer parent;
    private List<Operation> actionQueue;

    private Operation currentAction;
    private boolean actionInProgress;

    private boolean paused;
    private boolean finished;

    private Callback callback;
    private ErrorHandler errorHandler;

    private ArrayList<Object> returnValueStack;

    @Deprecated
    public Sequence() {
        this.actionQueue = new ArrayList<>();
        this.actionInProgress = false;
        this.callback = new Callback(this);
        this.returnValueStack = new ArrayList<>();
    }

    public Sequence(Sequencer parent) {
        this.parent = parent;
        this.actionQueue = new ArrayList<>();
        this.actionInProgress = false;
        this.callback = new Callback(this);
        this.returnValueStack = new ArrayList<>();
    }

    public Sequence then(Operation action) {
        actionQueue.add(action);
        return this;
    }

    public Sequence thenStart(final Sequence sequence) {
        actionQueue.add(new SimpleOperation() {
            @Override
            public void start(Callback callback) {
                sequence.unpause();
                callback.next();
            }
        });
        return this;
    }

    /**
     * Starts a new sequence depending on the value pushed by the previous operation. Pushes 'true' to the sequence if a sequence was successfully started.
     * @param ifTrue Runs if the previously pushed value is a boolean and true
     * @param ifFalse " and is false
     * @return This sequence
     */
    public Sequence thenStartIf(final Sequence ifTrue, final Sequence ifFalse) {
        actionQueue.add(new SimpleOperation() {
            @Override
            public void start(Callback callback) {
                Object lastReturnValue = callback.getPreviousReturnValue();
                if(lastReturnValue instanceof Boolean) {
                    if ((Boolean) lastReturnValue) {
                        ifTrue.unpause();
                        callback.next(true);
                    } else {
                        ifFalse.unpause();
                        callback.next(true);
                    }
                } else {
                    callback.next(false);
                }
            }
        });
        return this;
    }

    public Operation join(final Sequence... sequences) {
        return new Operation() {
            @Override
            public void start(Callback callback) {

            }

            @Override
            public void loop(Callback callback) {
                for(Sequence sequence : sequences) {
                    if (sequence.isFinished()) {
                        callback.next();
                    }
                }
            }

            @Override
            public void stop(Callback callback) {
                callback.next();
            }
        };
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void loop() {
        if (!finished && !paused) {
            if (actionInProgress) {
                currentAction.loop(callback);
            } else if (actionQueue.size() == 0) {
                finished = true;
            } else {
                currentAction = actionQueue.get(0);
                actionQueue.remove(0);

                actionInProgress = true;
                currentAction.start(callback);
            }
        }
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }

    public void terminate() {
        finished = true;
        currentAction.stop(callback);
    }

    public boolean isFinished() {
        return finished;
    }

    public class Callback {
        private Sequence parent;
        private Callback(Sequence parent) {
            this.parent = parent;
        }

        public void next() {
            parent.actionInProgress = false;
            parent.returnValueStack.add(null);
        }

        public void next(Object returnValue) {
            parent.actionInProgress = false;
            parent.returnValueStack.add(returnValue);
        }

        public void err(ErrorArgs error) {
            if(parent.errorHandler != null) {
                parent.errorHandler.handleError(parent, error);
            }
        }

        public Object getPreviousReturnValue() {
            return parent.returnValueStack.get(parent.returnValueStack.size() - 1);
        }

        public ArrayList<Object> getReturnValueStack() {
            return parent.returnValueStack;
        }

        public Sequence getSequence() {
            return parent;
        }
    }
}
