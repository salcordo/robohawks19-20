//THIS IS ALL IMPORTING PACKAGES, DON'T WORRY ABOUT IT.
package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "EncoderRed")
@Disabled
public class EncoderRed extends LinearOpMode {

    //DEFINE GLOBAL MOTOR NAMES
    //{hardware type} {name};
    //{var type} {name} (= {value}); The value is not necessary.

    //DRIVE MOTORS
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;

    //MOTORS
    DcMotor motorRCurl;
    DcMotor motorLCurl;
    Servo servoLoaf;
    Servo servoFoundation;
    DcMotor motorRSuck;
    DcMotor motorLSuck;
    ElapsedTime time;

    @Override
    public void runOpMode() {

        //**********INIT START**********//

        //GET MOTOR NAMES FROM HARDWARE
        //WITHIN PHONE, THE NAMES ARE FOUND WITHIN CONFIGURE ROBOT
        //{motor name} = hardwareMap.{hardware type, IE: dcMotor}.get("{name of motor}");

        //DRIVE MOTORS
        motor1 = hardwareMap.dcMotor.get("m1");
        motor2 = hardwareMap.dcMotor.get("m2");
        motor3 = hardwareMap.dcMotor.get("m3");
        motor4 = hardwareMap.dcMotor.get("m4");

        //MOTORS
        motorRCurl = hardwareMap.dcMotor.get("motorRCurl");
        motorLCurl = hardwareMap.dcMotor.get("motorLCurl");
        servoFoundation = hardwareMap.servo.get("servoFoundation");
        servoLoaf = hardwareMap.servo.get("servoLoaf");
        motorRSuck = hardwareMap.dcMotor.get("motorRSuck");
        motorLSuck = hardwareMap.dcMotor.get("motorLSuck");
        time = new ElapsedTime();
        waitForStart();

        //**********INIT STOP**********//
        //**********DETECT START*********FoundationBlue*//

        time.reset();
        //RESETS THE TIME BACK TO 0 SECONDS

        while (time.seconds() < 0.5 && opModeIsActive()) { //WHILE THE PROGRAM IS ACTIVE AND THE TIME IS UNDER 0.5 SECONDS(SO THE PROGRAM DOESN'T LOOP)
            //STOP AND RESET MOTOR ENCODERS
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //RESET FOUNDATION SERVO
            servoFoundation.setPosition(90);

            //RESET BREAD LOAF SERVO
            servoLoaf.setPosition(90);

            //SET MOTORS TO RUN TO POSITION
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //MOVE ONE MOTOR ROTATION FORWARD AND SET SPEED TO FULL
            motor1.setTargetPosition(1120); motor1.setPower(1);
            motor2.setTargetPosition(1120); motor2.setPower(1);
            motor3.setTargetPosition(1120); motor3.setPower(1);
            motor4.setTargetPosition(1120); motor4.setPower(1);

            //WAIT UNTIL MOTORS ARE NO LONGER BUSY
            while(motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() &&opModeIsActive()) {
                //Loop body can be empty
            }

            //STOP MOTORS
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);

            //TURN TO THE RIGHT AND SET SPEED TO FULL
            motor1.setTargetPosition(1120); motor1.setPower(1);
            motor2.setTargetPosition(1120); motor2.setPower(1);
            motor3.setTargetPosition(1120); motor3.setPower(1);
            motor4.setTargetPosition(1120); motor4.setPower(1);

            //WAIT UNTIL MOTORS ARE NO LONGER BUSY
            while(motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() &&opModeIsActive()) {
                //Loop body can be empty
            }

            //STOP MOTORS
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);

            //STRAFE TO THE LEFT AND SET SPEED TO FULL
            motor1.setTargetPosition(1120); motor1.setPower(1);
            motor2.setTargetPosition(1120); motor2.setPower(1);
            motor3.setTargetPosition(1120); motor3.setPower(1);
            motor4.setTargetPosition(1120); motor4.setPower(1);

            //WAIT UNTIL MOTORS ARE NO LONGER BUSY
            while(motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() &&opModeIsActive()) {
                //Loop body can be empty
            }

            //STOP MOTORS
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);

            //BREAD LOAF
            servoLoaf.setPosition(0);
            sleep(150);

            //STRAFE RIGHT AND SET SPEED TO FULL
            motor1.setTargetPosition(1120); motor1.setPower(1);
            motor2.setTargetPosition(1120); motor2.setPower(1);
            motor3.setTargetPosition(1120); motor3.setPower(1);
            motor4.setTargetPosition(1120); motor4.setPower(1);

            //WAIT UNTIL MOTORS ARE NO LONGER BUSY
            while(motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() &&opModeIsActive()) {
                //Loop body can be empty
            }

            //STOP MOTORS
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);

            //MOVE ONE MOTOR ROTATION FORWARD AND SET SPEED TO FULL
            motor1.setTargetPosition(1120); motor1.setPower(1);
            motor2.setTargetPosition(1120); motor2.setPower(1);
            motor3.setTargetPosition(1120); motor3.setPower(1);
            motor4.setTargetPosition(1120); motor4.setPower(1);

            //WAIT UNTIL MOTORS ARE NO LONGER BUSY
            while(motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() &&opModeIsActive()) {
                //Loop body can be empty
            }

            //STOP MOTORS
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);

            //UNLOAF
            servoLoaf.setPosition(90);
            sleep(150);

            //MOVE ONE MOTOR ROTATION BACKWARDS AND SET SPEED TO FULL
            motor1.setTargetPosition(1120); motor1.setPower(1);
            motor2.setTargetPosition(1120); motor2.setPower(1);
            motor3.setTargetPosition(1120); motor3.setPower(1);
            motor4.setTargetPosition(1120); motor4.setPower(1);

            //WAIT UNTIL MOTORS ARE NO LONGER BUSY
            while(motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() &&opModeIsActive()) {
                //Loop body can be empty
            }

            //STOP MOTORS
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
        }
    }
}