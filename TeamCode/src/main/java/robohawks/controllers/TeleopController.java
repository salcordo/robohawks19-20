package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import robohawks.controllers.old.Controller;
import robohawks.modules.base.HolonomicDriveModule;

@TeleOp(name = "TeleOp", group = "Teleop")
public class TeleopController extends Controller {

    HolonomicDriveModule drive;
    DcMotor motorRCurl;
    DcMotor motorLCurl;
    Servo servoLoaf;
    Servo servoFoundation;


    float precisionxa = 1;
    float precisionya = 1;
    float precisionxb = 1;
    float precisionyb = 1;
    int armposition;

    @Override
    public void init() {
        drive = new HolonomicDriveModule(hardwareMap);
        motorRCurl = hardwareMap.dcMotor.get("motorRCurl");
        motorLCurl = hardwareMap.dcMotor.get("motorLCurl");
        servoFoundation = hardwareMap.servo.get("servoFoundation");
        servoLoaf = hardwareMap.servo.get("servoLoaf");
    }

    @Override
    public void loop() {
        super.loop();
//CONTROLLER 1
    //Left Stick:   Drive
    //Right Stick:  Rotate
    //B:            Precision Toggle
    //DPAD:         Motor Test

//CONTROLLER 2
    //Left Bumper:  Curl
    //Right Bumper: Curl
    //X:            Loaf
    //Y:            Foundation

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
        boolean precision = false;
        if(gamepad1.b && !precision){
            if(precisionxa == 1) {
                precisionya = (float) 0.4;
                precisionxa = (float) 0.6;
            } else {
                precisionxa = (float) 1;
                precisionya = (float) 1;
                precision = true;
            }
        } else if (!gamepad1.b){
            precision = false;
        }

        //Curl
        if(gamepad2.left_bumper){
            motorLCurl.setPower(1);
            motorRCurl.setPower(1);
        }else{
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
        }

        //Curl
        if(gamepad2.right_bumper){
            motorLCurl.setPower(-1);
            motorRCurl.setPower(-1);
        }else{
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
        }

        //Servo Loaf
        boolean loaf = false; //Outside of loop()
        if(gamepad2.x && !loaf) {
            if(servoLoaf.getPosition() == 0) servoLoaf.setPosition(90);
            else servoLoaf.setPosition(0);
            loaf = true;
        } else if(!gamepad2.x) loaf = false;

        //Servo Foundation
        boolean foundation = false; //Outside of loop()
        if(gamepad2.y && !foundation) {
            if(servoFoundation.getPosition() == 0) servoFoundation.setPosition(90);
            else servoFoundation.setPosition(0);
            foundation = true;
        } else if(!gamepad2.x) foundation = false;

        //DPAD TESTING
        if(gamepad1.dpad_up){
            drive.setPowerOne(0.5);
        } else {
            drive.setPowerOne(0);
        }

        if(gamepad1.dpad_left){
            drive.setPowerTwo(0.5);
        } else {
            drive.setPowerTwo(0);
        }

        if(gamepad1.dpad_right){
            drive.setPowerThree(0.5);
        } else {
            drive.setPowerThree(0);
        }

        if(gamepad1.dpad_down){
            drive.setPowerFour(0.5);
        } else {
            drive.setPowerFour(0);
        }
    }

}