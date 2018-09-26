package robohawks.async.io;

/**
 * Created by fchoi on 12/1/2015.
 */
public interface ControllerEventProcessor {
    boolean buttonDown(int controller, int buttonCode);
    boolean buttonUp(int controller, int buttonCode);

    boolean joystickMoved(int controller, int joystickCode, float x, float y);

    boolean triggerPressed(int controller, int triggerCode, float value);
}
