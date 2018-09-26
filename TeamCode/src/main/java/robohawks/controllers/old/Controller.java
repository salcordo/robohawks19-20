package robohawks.controllers.old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import robohawks.async.Sequence;
import robohawks.async.Sequencer;

/**
 * Created by fchoi on 9/25/2016.
 */
public abstract class Controller extends OpMode {
    public Sequencer sequencer;

    public Controller() {
        sequencer = new Sequencer();
    }

    @Override
    public void loop() {
        sequencer.loop();
    }
}
