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

    //HARDWARE
    HolonomicDriveModule drive;
    Servo leftLoaf;
    Servo rightLoaf;
    CRServo succLeft;
    CRServo succRight;
    DcMotor verticalpulley1;
    DcMotor verticalpulley2;
    DcMotor succ;
    DcMotor horizontalextender;
    DcMotor m1;

    //VARIABLES
    float precisionxa = 1;
    float precisionya = 1;
    float succprecision = 1;
    boolean precisionreset = true;
    boolean precisiontoggle = false;
    boolean loafreset = true;
    boolean loaftoggle = false;
    boolean foundationreset = true;
    boolean foundationtoggle = false;
    boolean succreset = true;
    boolean succtoggle = false;


    @Override
    public void init() {
        drive = new HolonomicDriveModule(hardwareMap);
        verticalpulley1 = hardwareMap.dcMotor.get("vpulley1");
        verticalpulley2 = hardwareMap.dcMotor.get("vpulley2");
        succ = hardwareMap.dcMotor.get("succ");
        horizontalextender = hardwareMap.dcMotor.get("horizontalextender");
        rightLoaf = hardwareMap.servo.get("rightLoaf");
        leftLoaf = hardwareMap.servo.get("leftLoaf");
        succLeft = hardwareMap.crservo.get("succLeft");
        succRight = hardwareMap.crservo.get("succRight");

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
    //B:            Succ
    //A:            Succ
    //Left Trigger: Vertical Pulley
    //Right Trigger:Vertical Pulley
    //Left Bumper:  Negative Horizontal Extender
    //Right Bumper: Horizontal Extender

        // Drive
        float gamepad1LeftY = -gamepad1.left_stick_y * precisionya;
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
        drive.setPowerOne(FrontRight);
        drive.setPowerTwo(FrontLeft);
        drive.setPowerThree(-BackLeft);
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


        if(gamepad2.dpad_down){
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(1);
            drive.setPowerFour(-1);
        } else {
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
        }


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

        //BRIDGE RESET
        if(gamepad2.b){
            loaftoggle = false;
            foundationtoggle = false;
        }

        //VERTICAL PULLEY
        if(gamepad2.left_trigger >= 0.5){
            verticalpulley1.setPower(-1);
            verticalpulley2.setPower(-1);
        } else {
            verticalpulley1.setPower(0);
            verticalpulley2.setPower(0);
        }

        if(gamepad2.right_trigger >= 0.5){
            verticalpulley1.setPower(1);
            verticalpulley2.setPower(1);
        } else {
            verticalpulley1.setPower(0);
            verticalpulley2.setPower(0);
        }

        //SUCC
        if(gamepad2.a){
            succ.setPower(-1);
        } else {
            succ.setPower(0);
        }


        //HORIZONTAL EXTENDER
        if(gamepad2.left_bumper){
            horizontalextender.setPower(-1);
        } else {
            horizontalextender.setPower(0);
        }

        if(gamepad2.right_bumper){
            horizontalextender.setPower(1);
        } else {
            horizontalextender.setPower(0);
        }

        //BRIDGE RESET
        if(gamepad2.x){
            leftLoaf.setPosition(45);
            rightLoaf.setPosition(45);
        }

        //LEFT LOAF
        //if(gamepad2.x){
        //    leftLoaf.setPosition(0);
        //} else {
        //    leftLoaf.setPosition(45);
        //}

        //RIGHT LOAF
        //if(gamepad2.y){
        //    rightLoaf.setPosition(45);
        //} else {
        //    rightLoaf.setPosition(0);
        //}

        //OPEN SUCC
        if(gamepad2.b){
            succLeft.setPower(0.2);
            succRight.setPower(0.2);
        } else {
            succLeft.setPower(0);
            succRight.setPower(0);
        }
    }
}
