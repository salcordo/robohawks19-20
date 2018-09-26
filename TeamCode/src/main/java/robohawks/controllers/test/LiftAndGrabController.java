package robohawks.controllers.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robohawks.controllers.old.Controller;
import robohawks.modules.base.GrabModule;
import robohawks.modules.base.LiftModule;

/**
 * Created by paarth on 2/2/17.
 */

@TeleOp(name = "LiftAndGrabController", group = "Teleop")
public class LiftAndGrabController extends Controller{
    LiftModule liftModule;
    GrabModule grabModule;

    @Override
    public void init(){
        liftModule = new LiftModule(hardwareMap);
        grabModule = new GrabModule(hardwareMap);

        grabModule.setLeftServo(.5);
        grabModule.setRightServo(.5);
    }

    @Override
    public void loop() {
        super.loop();

        if (gamepad2.dpad_up){
            liftModule.setPower(.5);
        } else {
            liftModule.setPower(0);
        }

        if (gamepad2.dpad_down){
            liftModule.setPower(-.5);
        } else {
            liftModule.setPower(0);
        }

        if (gamepad2.a){
            grabModule.setLeftServo(1);
            grabModule.setRightServo(1);
        }

        if (gamepad2.b){
            grabModule.setLeftServo(.5);
            grabModule.setRightServo(.5);
        }
    }

}
