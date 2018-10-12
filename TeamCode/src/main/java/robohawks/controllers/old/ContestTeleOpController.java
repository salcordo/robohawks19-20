package robohawks.controllers.old;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import robohawks.modules.base.HolonomicDriveModule;


public class ContestTeleOpController extends Controller{

    HolonomicDriveModule drive;
    DcMotor leftarm;
    DcMotor rightarm;
    boolean inverse;

    @Override
    public void init() {
        drive = new HolonomicDriveModule(hardwareMap);
        leftarm = hardwareMap.dcMotor.get("leftarm");
        rightarm = hardwareMap.dcMotor.get("rightarm");
        inverse = false;
    }

    @Override
    public void loop() {
        super.loop();

        //Test(Alcordo)
        if(gamepad1.a){
            drive.setPowerOne(1);
        } else {
            drive.setPowerOne(0);
        }

        if(gamepad1.b){
            drive.setPowerTwo(1);
        } else {
            drive.setPowerTwo(0);
        }

        if(gamepad1.x){
            drive.setPowerThree(1);
        } else {
            drive.setPowerThree(0);
        }

        if(gamepad1.y){
            drive.setPowerFour(1);
        } else {
            drive.setPowerFour(0);
        }

        //Drive

        if (gamepad1.right_trigger>0){
            inverse = true;
        } else {
            inverse = false;
        }

        if (!inverse) {

            if (gamepad1.dpad_up) {
                drive.setPowerOne(-1);
                drive.setPowerTwo(1);
                drive.setPowerThree(1);
                drive.setPowerFour(-1);
            } else if (gamepad1.dpad_down) {
                drive.setPowerOne(1);
                drive.setPowerTwo(-1);
                drive.setPowerThree(-1);
                drive.setPowerFour(1);
            } else if (gamepad1.dpad_left) {
                drive.setPowerOne(1);
                drive.setPowerTwo(1);
                drive.setPowerThree(-1);
                drive.setPowerFour(-1);
            } else if (gamepad1.dpad_right) {
                drive.setPowerOne(-1);
                drive.setPowerTwo(-1);
                drive.setPowerThree(1);
                drive.setPowerFour(1);
            } else {
                drive.setPowerOne(0);
                drive.setPowerTwo(0);
                drive.setPowerThree(0);
                drive.setPowerFour(0);
            }

            if (gamepad1.right_stick_x > 0) {
                drive.setPowerOne(-1);
                drive.setPowerTwo(-1);
                drive.setPowerThree(-1);
                drive.setPowerFour(-1);
            } else {
                drive.setPowerOne(0);
                drive.setPowerTwo(0);
                drive.setPowerThree(0);
                drive.setPowerFour(0);
            }

            if (gamepad1.right_stick_x < 0) {
                drive.setPowerOne(1);
                drive.setPowerTwo(1);
                drive.setPowerThree(1);
                drive.setPowerFour(1);
            } else {
                drive.setPowerOne(0);
                drive.setPowerTwo(0);
                drive.setPowerThree(0);
                drive.setPowerFour(0);
            }
        } else {
            if (gamepad1.dpad_up) {
                drive.setPowerOne(1);
                drive.setPowerTwo(-1);
                drive.setPowerThree(-1);
                drive.setPowerFour(1);
            } else if (gamepad1.dpad_down) {
                drive.setPowerOne(-1);
                drive.setPowerTwo(1);
                drive.setPowerThree(1);
                drive.setPowerFour(-1);
            } else if (gamepad1.dpad_left) {
                drive.setPowerOne(-1);
                drive.setPowerTwo(-1);
                drive.setPowerThree(1);
                drive.setPowerFour(1);
            } else if (gamepad1.dpad_right) {
                drive.setPowerOne(1);
                drive.setPowerTwo(1);
                drive.setPowerThree(-1);
                drive.setPowerFour(-1);
            } else {
                drive.setPowerOne(0);
                drive.setPowerTwo(0);
                drive.setPowerThree(0);
                drive.setPowerFour(0);
            }

            if (gamepad1.right_stick_x > 0) {
                drive.setPowerOne(1);
                drive.setPowerTwo(1);
                drive.setPowerThree(1);
                drive.setPowerFour(1);
            } else {
                drive.setPowerOne(0);
                drive.setPowerTwo(0);
                drive.setPowerThree(0);
                drive.setPowerFour(0);
            }

            if (gamepad1.right_stick_x < 0) {
                drive.setPowerOne(-1);
                drive.setPowerTwo(-1);
                drive.setPowerThree(-1);
                drive.setPowerFour(-1);
            } else {
                drive.setPowerOne(0);
                drive.setPowerTwo(0);
                drive.setPowerThree(0);
                drive.setPowerFour(0);
            }
        }

//        float gamepad1LeftY = gamepad1.left_stick_y;
//        float gamepad1LeftX = -gamepad1.left_stick_x;
//        float gamepad1RightX = -gamepad1.right_stick_x;
//
//        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
//        float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
//        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
//        float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
//
//        FrontRight = Range.clip(FrontRight, -1, 1);
//        FrontLeft = Range.clip(FrontLeft, -1, 1);
//        BackLeft = Range.clip(BackLeft, -1, 1);
//        BackRight = Range.clip(BackRight, -1, 1);
//
//        drive.setPowerTwo(FrontRight/2);
//        drive.setPowerOne(FrontLeft/2);
//        drive.setPowerThree(BackLeft/2);
//        drive.setPowerFour(BackRight/2);

        //Arm

        if (gamepad2.dpad_down){
            leftarm.setPower(.5);
            rightarm.setPower(.5);
        } else {
            leftarm.setPower(0);
            rightarm.setPower(0);
        }

        if (gamepad2.dpad_up){
            leftarm.setPower(-.5);
            rightarm.setPower(-.5);
        } else {
            leftarm.setPower(0);
            rightarm.setPower(0);
        }

    }

}
