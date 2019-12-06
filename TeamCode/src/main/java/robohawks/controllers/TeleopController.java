package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.TimeUnit;

import robohawks.controllers.old.Controller;
import robohawks.modules.base.HolonomicDriveModule;

@TeleOp(name = "TeleOp", group = "Teleop")
public class TeleopController extends Controller {

    HolonomicDriveModule drive;
    DcMotor motorLift;


    float precisionxa = 1;
    float precisionya = 1;
    float precisionxb = 1;
    float precisionyb = 1;
    int armposition;

    @Override
    public void init() {
        drive = new HolonomicDriveModule(hardwareMap);
        motorLift = hardwareMap.dcMotor.get("motorLift");

        telemetry.addData("Arm Position", armposition);
    }

    @Override
    public void loop() {
        super.loop();

        // RESET ENCODER
        motorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Drive
        float gamepad1LeftY = gamepad1.left_stick_y * precisionya;
        float gamepad1LeftX = -gamepad1.left_stick_x * precisionxa;
        float gamepad1RightX = -gamepad1.right_stick_x * precisionxa;

        // Holonomic Formulas
        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

        // Clip the right/left values so that the values never exceed +/- 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

        // Write the values to the motors
        drive.setPowerOne(-FrontRight/2);
        drive.setPowerTwo(FrontLeft/2);
        drive.setPowerThree(BackLeft/2);
        drive.setPowerFour(-BackRight/2);


        //Toggle compliance wheels

        //EXAMPLE BUTTON
        //if(input){
        //  motor.setPower(value);
        //  servo.setPower(value);
        //  servo.setPosition(value);
        //} else {
        //  motor.setPower(0);
        //  servo.setPower(0);
        //  servo.setPosition(0);

        //Toggle precision mode for primary driver (Controller 1)
        boolean precisionmodea = false;
        if(gamepad1.dpad_down){
            precisionxa = (float) 0.6;
            precisionya = (float) 0.4;
        } else if (gamepad1.dpad_up){
            precisionxa = (float) 1;
            precisionya = (float) 1;
        }

        //Raise ARM
        if(gamepad2.right_trigger > 0.5){
            motorLift.setPower(1);
        } else {
            motorLift.setPower(0);
        }

        //Lower Arm
        if(gamepad2.left_trigger > 0.5){
            motorLift.setPower(-1);
        } else {
            motorLift.setPower(0);
        }

        //GRIPPER


        //GRIPPER PIVOT
        if(gamepad1.left_bumper){

        }

        //BRIDGE MODE CALIBRATE
        armposition = motorLift.getCurrentPosition();
        if(gamepad1.x || gamepad2.x){
            armposition = motorLift.getCurrentPosition();
        }
        //Bridge Mode Reset
        if(gamepad1.y || gamepad2.y){
            motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorLift.setTargetPosition(armposition);
            motorLift.setPower(0.5);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            motorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }


    }

}