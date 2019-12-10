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
        if((gamepad1.b) && (precision == false)){
            precisionxa = (float) 0.6;
            precisionya = (float) 0.4;
        } else if ((gamepad1.b) && (precision == true)){
            precisionxa = (float) 1;
            precisionya = (float) 1;
        }

        //Curl
        if(gamepad2.left_bumper){
            motorLCurl.setPower(1);
            motorRCurl.setPower(-1);
        }else{
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
        }

        //Curl
        if(gamepad2.right_bumper){
            motorLCurl.setPower(-1);
            motorRCurl.setPower(1);
        }else{
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
        }

        //Servo Foundation
        boolean loaf = false;
        if((gamepad2.x) && (loaf == false)){
            servoLoaf.setPosition(90);
        } else if ((gamepad2.x) && (loaf == true)){
            servoLoaf.setPosition(0);
        }

        //Servo Foundation
        boolean foundation = false;
        if((gamepad2.y) && (foundation == false)){
            servoFoundation.setPosition(90);
        } else if ((gamepad2.y) && (foundation == true)){
            servoFoundation.setPosition(0);
        }
    }

}