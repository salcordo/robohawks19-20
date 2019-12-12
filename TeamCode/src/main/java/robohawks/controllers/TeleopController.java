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
    boolean precisionreset = true;
    boolean precisiontoggle = false;
    boolean loafreset = true;
    boolean loaftoggle = false;
    boolean foundationreset = true;
    boolean foundationtoggle = false;


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
    //Left Bumper:  Curl Up
    //Right Bumper: Curl Down
    //X:            Loaf
    //Y:            Foundation
    //B:            Bridge Reset

        // Drive
        float gamepad1LeftY = gamepad1.left_stick_y * precisionya;
        float gamepad1LeftX = -gamepad1.left_stick_x * precisionxa;
        float gamepad1RightX = gamepad1.right_stick_x * precisionxa;

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
        drive.setPowerOne(-FrontRight);
        drive.setPowerTwo(FrontLeft);
        drive.setPowerThree(BackLeft);
        drive.setPowerFour(-BackRight);


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
        if(gamepad1.b && precisionreset){
            precisiontoggle = !precisiontoggle;
            precisionreset = false;
        }

        if(!gamepad1.b){
            precisionreset = true;
        }

        if(precisiontoggle){
            precisionya = (float) 0.4;
            precisionxa = (float) 0.6;
        }
        if(!precisiontoggle){
            precisionya = (float) 1;
            precisionxa = (float) 1;
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
            motorLCurl.setPower(-0.5);
            motorRCurl.setPower(-0.5);
        }else{
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
        }

        //Servo Foundation
        if(gamepad2.y && foundationreset){
            foundationtoggle = !foundationtoggle;
            foundationreset = false;
        }

        if(!gamepad2.y){
            foundationreset = true;
        }

        if(foundationtoggle){
            servoFoundation.setPosition(0);
        }
        if(!foundationtoggle){
            servoFoundation.setPosition(90);
        }
        //Servo Loaf
        if(gamepad2.x && loafreset){
            loaftoggle = !loaftoggle;
            loafreset = false;
        }

        if(!gamepad2.x){
            loafreset = true;
        }

        if(loaftoggle){
            servoLoaf.setPosition(0);
        }
        if(!loaftoggle){
            servoLoaf.setPosition(90);
        }

        //BRIDGE RESET
        if(gamepad2.b){
            loaftoggle = false;
            foundationtoggle = false;
        }

        //DPAD TESTING
        if(gamepad1.dpad_up){
            drive.setPowerOne(1);
        } else {
            drive.setPowerOne(0);
        }

        if(gamepad1.dpad_left){
            drive.setPowerTwo(1);
        } else {
            drive.setPowerTwo(0);
        }

        if(gamepad1.dpad_right){
            drive.setPowerThree(1);
        } else {
            drive.setPowerThree(0);
        }

        if(gamepad1.dpad_down){
            drive.setPowerFour(1);
        } else {
            drive.setPowerFour(0);
        }
    }
}