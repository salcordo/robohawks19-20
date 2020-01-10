package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "LoadingRed")
public class LoadingRed extends LinearOpMode {

    HolonomicDriveModule drive;
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

        drive = new HolonomicDriveModule(hardwareMap);
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


        while (time.seconds() < 0.5 && opModeIsActive()) {
            //FOUNDATION
            servoFoundation.setPosition(90);
            sleep(150);

            //Left
            drive.setPowerOne(1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(1);
            drive.setPowerFour(-1);
            sleep(700);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FORWARD
            drive.setPowerOne(0.9);
            drive.setPowerTwo(0.9);
            drive.setPowerThree(0.9);
            drive.setPowerFour(0.9);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //TURN RIGHT
            drive.setPowerOne(0.7);
            drive.setPowerTwo(-0.7);
            drive.setPowerThree(-0.7);
            drive.setPowerFour(0.7);
            sleep(700);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.5);
            drive.setPowerTwo(-0.5);
            drive.setPowerThree(-0.5);
            drive.setPowerFour(-0.5);
            sleep(300);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(0);
            sleep(300);

            //FORWARD
            drive.setPowerOne(0.4);
            drive.setPowerTwo(0.4);
            drive.setPowerThree(0.4);
            drive.setPowerFour(0.4);
            sleep(3200);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(90);
            sleep(300);

            //RIGHT
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(-1);
            drive.setPowerFour(1);
            sleep(1400);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.5);
            drive.setPowerTwo(-0.5);
            drive.setPowerThree(-0.5);
            drive.setPowerFour(-0.5);
            sleep(500);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //TURN RIGHT
            drive.setPowerOne(0.75);
            drive.setPowerTwo(-0.75);
            drive.setPowerThree(-0.75);
            drive.setPowerFour(0.75);
            sleep(600);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.5);
            drive.setPowerTwo(-0.5);
            drive.setPowerThree(-0.5);
            drive.setPowerFour(-0.5);
            sleep(200);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(0);
            sleep(300);

            //TURN RIGHT
            drive.setPowerOne(0.7);
            drive.setPowerTwo(-0.7);
            drive.setPowerThree(-0.7);
            drive.setPowerFour(0.7);
            sleep(600);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.5);
            drive.setPowerTwo(-0.5);
            drive.setPowerThree(-0.5);
            drive.setPowerFour(-0.5);
            sleep(300);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(90);
            sleep(300);

            //LEFT
            drive.setPowerOne(1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(1);
            drive.setPowerFour(-1);
            sleep(200);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //Lower SUCK arm
            motorLCurl.setPower(-0.2);
            motorRCurl.setPower(-0.2);
            sleep(500);
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
            sleep(300);

            //FORWARD
            drive.setPowerOne(0.9);
            drive.setPowerTwo(0.9);
            drive.setPowerThree(0.9);
            drive.setPowerFour(0.9);
            sleep(400);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);
        }
    }
}