package robohawks.controllers.old;

import robohawks.async.io.ControllerEventProcessor;
import robohawks.async.io.EventGamepad;

/**
 * Created by fchoi on 1/28/2017.
 */
public abstract class TeleopController extends Controller implements ControllerEventProcessor{
    protected EventGamepad eventGamepad;

    public TeleopController() {
        eventGamepad = new EventGamepad(this, this);
    }

    @Override
    public void loop() {
        super.loop();
        eventGamepad.loop();
    }
}
