package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import robohawks.controllers.old.Controller;
import robohawks.modules.base.HolonomicDriveModule;

@TeleOp(name = "TeleOp", group = "Teleop")
public class TeleopController extends Controller {

    HolonomicDriveModule drive;
    DcMotor liftArm;
    DcMotor liftArm2;
    DcMotor mineralArm;
    DcMotor mineralSpool;

    @Override
    public void init() {
        drive = new HolonomicDriveModule(hardwareMap);
        liftArm = hardwareMap.dcMotor.get("liftArm");
        liftArm2 = hardwareMap.dcMotor.get("liftArm2");
        mineralArm = hardwareMap.dcMotor.get("mineralArm");
        mineralSpool = hardwareMap.dcMotor.get("mineralSpool");
    }

    @Override
    public void loop() {
        super.loop();

        // DRIVE
        float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

                // holonomic formulas
        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

                // clip the right/left values so that the values never exceed +/- 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

                // write the values to the motors
        drive.setPowerOne(FrontRight/2);
        drive.setPowerTwo(-FrontLeft/2);
        drive.setPowerThree(-BackLeft/2);
        drive.setPowerFour(BackRight/2);

        // LIFT ARM
        if (gamepad1.dpad_down){
            liftArm.setPower(1);
            liftArm2.setPower(-1);
        } else {
            liftArm.setPower(0);
            liftArm2.setPower(0);
        }

        if (gamepad1.dpad_up){  
            liftArm.setPower(-1);
            liftArm2.setPower(1);
        } else {
            liftArm.setPower(0);
            liftArm2.setPower(0);
        }

        // MINERAL ARM
        if (gamepad1.x){
            mineralArm.setPower(1);
        } else {
            mineralArm.setPower(0);
        }

        if (gamepad1.a){
            mineralArm.setPower(-1);
        } else {
            mineralArm.setPower(0);
        }

        // MINERAL SPOOL
        if (gamepad1.b){
            mineralSpool.setPower(1);
        } else {
            mineralSpool.setPower(0);
        }

        if (gamepad1.y){
            mineralSpool.setPower(-1);
        } else {
            mineralSpool.setPower(0);
        }
    }
}
