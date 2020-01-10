package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "FoundationBlue")
public class OptimizedLoadingRed extends LinearOpMode {

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

            //RIGHT
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(-1);
            drive.setPowerFour(1);
            sleep(500);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(-1);
            drive.setPowerFour(-1);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //RIGHT
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(-1);
            drive.setPowerFour(1);
            sleep(500);
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
            sleep(150);

            //TURN RIGHT
            drive.setPowerOne(0.75);
            drive.setPowerTwo(-0.75);
            drive.setPowerThree(-0.75);
            drive.setPowerFour(0.75);
            sleep(750);
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
            sleep(600);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FORWARD
            drive.setPowerOne(0.5);
            drive.setPowerTwo(0.5);
            drive.setPowerThree(0.5);
            drive.setPowerFour(0.5);
            sleep(500);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //TURN LEFT
            drive.setPowerOne(-0.75);
            drive.setPowerTwo(0.75);
            drive.setPowerThree(0.75);
            drive.setPowerFour(-0.75);
            sleep(750);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //DROP CURL
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