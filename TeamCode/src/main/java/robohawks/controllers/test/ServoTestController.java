package robohawks.controllers.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import robohawks.controllers.old.Controller;

/**
 * Created by Paarth Tandon on 11/9/2017.
 */

@TeleOp(name = "Servo Test", group = "Teleop")
public class ServoTestController extends Controller{
    Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("1");
    }

    @Override
    public void loop() {
        super.loop();

        if (gamepad1.a){
            servo.setPosition(0);
        }

        if (gamepad1.b){
            servo.setPosition(0.5);
        }

    }
}
