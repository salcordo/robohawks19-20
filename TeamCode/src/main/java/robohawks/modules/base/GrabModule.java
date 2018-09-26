package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import robohawks.async.Operation;

/**
 * Created by Paarth Tandon on 10/20/2017.
 */

public class GrabModule {
    private Servo leftServo;
    private Servo rightServo;

    public GrabModule(HardwareMap hardwareMap){
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
    }

    public void setLeftServo(double x) {leftServo.setPosition(x);}

    public void setRightServo(double x) {rightServo.setPosition(x);}
}
