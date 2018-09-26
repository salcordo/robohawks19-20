package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import robohawks.async.Operation;
import robohawks.async.Sequence;
import robohawks.async.SimpleOperation;

/**
 * Created by paarth on 1/23/17.
 */
public class ActuatorModule {
    private Servo actuatorLeft;
    private Servo actuatorRight;

    public boolean actuator1Extended;
    public boolean actuator2Extended;

    private boolean locked;

    private double length = 0.4;

    public ActuatorModule(HardwareMap hardwareMap){
        actuatorLeft = hardwareMap.servo.get("actLeft");
        actuatorRight = hardwareMap.servo.get("actRight");
    }

    public void initialize() {
        setActuatorLeft(false);
        setActuatorRight(false);
    }

    public boolean isActuator1Extended() {
        return actuator1Extended;
    }

    public boolean isActuator2Extended() {
        return actuator2Extended;
    }

    public void setActuatorLeft(boolean extended){
        actuator1Extended = extended;
        if(extended){
            actuatorLeft.setPosition(length);
        } else {
            actuatorLeft.setPosition(.11);
        }
    }

    public void setActuatorRight(boolean extended) {
        actuator2Extended = extended;
        if (extended){
            actuatorRight.setPosition(length);
        } else {
            actuatorRight.setPosition(.11);
        }
    }

    public Operation setActuatorLeftOp(final boolean extended) {
        return new SimpleOperation() {
            @Override
            public void start(Sequence.Callback callback) {
                setActuatorLeft(extended);
                callback.next();
            }
        };
    }

    public Operation setActuatorRightOp(final boolean extended){
        return new SimpleOperation() {
            @Override
            public void start(Sequence.Callback callback) {
                setActuatorRight(extended);
                callback.next();
            }
        };
    }

    public void toggleActuatorLeft(){ setActuatorLeft(!isActuator1Extended());}

    public void toggleActuatorRight(){ setActuatorRight(!isActuator2Extended());}
}
