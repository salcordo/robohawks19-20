package robohawks.modules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import robohawks.async.Operation;
import robohawks.async.Sequence;
import robohawks.async.Sequencer;
import robohawks.async.error.ErrorArgs;
import robohawks.async.error.ErrorHandler;
import robohawks.modules.base.DriveModule;

public class SimpleModule {
    private DriveModule driveModule;
    private Sequencer sequencer;

    public SimpleModule(Sequencer sequencer, DriveModule driveModule) {
        this.sequencer = sequencer;
        this.driveModule = driveModule;
    }

    public Operation simple() {
        return new SimpleOperation(this);
    }

    private class SimpleOperation implements Operation, ErrorHandler {
        private SimpleModule module;
        private Sequence inflectionSequence;

        private SimpleOperation(SimpleModule module) {
            this.module = module;
        }

        @Override
        public void start(Sequence.Callback callback) {
            // Creating a sequence that first drives in an arc and then drives forward
            inflectionSequence = module.sequencer.begin(module.driveModule.drive(1,.5, 1)).then(module.driveModule.drive(1, 1, .5));
            inflectionSequence.setErrorHandler(this);
        }

        @Override
        public void loop(Sequence.Callback callback) {
            // Goes on to the next operation in the sequence when 'inflectionSequence' is finished
            if(inflectionSequence.isFinished()) callback.next();
        }

        @Override
        public void stop(Sequence.Callback callback) {
            // Aborting the sequence and going on to the next sequence
            inflectionSequence.terminate();
            callback.next();
        }

        @Override
        public void handleError(Sequence sequence, ErrorArgs error) {
            System.out.println(error.message + " from " + error.sender.toString());
        }
    }
}
