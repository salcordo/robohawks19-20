package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import robohawks.controllers.old.Controller;
import robohawks.modules.base.HolonomicDriveModule;

@TeleOp(name = "TeleopControllerTeaching", group = "Teleop")
@Disabled
public class TeleopControllerTeaching extends Controller {
    //HARDWARE
    HolonomicDriveModule drive;

    Servo servo2;
    DcMotor motor2;

    //VARIABLES
    float precisionxa = 1;
    float precisionya = 1;
    boolean precisionreset = true;
    boolean precisiontoggle = false;


    @Override
    public void init() {
        drive = new HolonomicDriveModule(hardwareMap);
        servo2 = hardwareMap.servo.get("servo2");
        motor2 = hardwareMap.dcMotor.get("motor2");
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
        //}

        //PRESS TO SET SERVO POSITION
        if(gamepad1.a){
            servo2.setPosition(50);
        }

        //HOLD TO TOGGLE MOTOR
        if(gamepad1.b){
            motor2.setPower(1);
        } else {
            motor2.setPower(0);
        }

        //HOLD TO TOGGLE SERVO
        if(gamepad1.x) {
            servo2.setPosition(100);
        } else {
            servo2.setPosition(0);
        }

        //BRIDGE RESET


        //TOGGLE SERVO


    }
}
