//THIS IS ALL IMPORTING PACKAGES, DON'T WORRY ABOUT IT.
package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "EncoderTesting")
public class EncoderTesting extends LinearOpMode {

    //DEFINE GLOBAL MOTOR NAMES
    //{hardware type} {name};
    //{var type} {name} (= {value}); The value is not necessary.

    //DRIVE MOTORS
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;

    //MOTORS

    //TIMEx
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
        time = new ElapsedTime();

        waitForStart();

        //**********INIT STOP**********//
        //**********DETECT START*******//

        time.reset();
        //RESETS THE TIME BACK TO 0 SECONDS

        while (time.seconds() < 0.5 && opModeIsActive()) { //WHILE THE PROGRAM IS ACTIVE AND THE TIME IS UNDER 0.5 SECONDS(SO THE PROGRAM DOESN'T LOOP)
            //STOP AND RESET MOTOR ENCODERS
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            //SET TARGET POSITION TO ONE FULL ROTATION
            motor1.setTargetPosition(1120);
            motor2.setTargetPosition(1120);
            motor3.setTargetPosition(1120);
            motor4.setTargetPosition(1120);

            //SET MOTORS TO RUN TO POSITION
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motor1.setPower(0.5);
            motor2.setPower(0.5);
            motor3.setPower(0.5);
            motor4.setPower(0.5);

            //WAIT UNTIL MOTORS ARE NO LONGER BUSY
            while(motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy() &&opModeIsActive()) {
                //Loop body can be empty
            }

            //STOP MOTORS
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);

            sleep(5000);
        }
    }
}