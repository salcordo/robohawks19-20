package robohawks.controllers.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import robohawks.controllers.old.Controller;

public class HangTestController extends Controller {

    DcMotor arm;

    @Override
    public void init() {
        arm = hardwareMap.dcMotor.get("arm");
    }

    @Override
    public void loop() {
        super.loop();

        if (gamepad2.dpad_down){
            arm.setPower(1);
        } else {
            arm.setPower(0);
        }

        if (gamepad2.dpad_up){
            arm.setPower(-1);
        } else {
            arm.setPower(0);
        }
    }
}
