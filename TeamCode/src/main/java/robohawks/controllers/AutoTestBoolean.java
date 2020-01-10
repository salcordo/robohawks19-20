package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "AutoTestBoolean")
public class AutoTestBoolean extends LinearOpMode {

    HolonomicDriveModule drive;
    DcMotor motorRCurl;
    DcMotor motorLCurl;
    Servo servoLoaf;
    Servo servoFoundation;
    DcMotor motorRSuck;
    DcMotor motorLSuck;
    ElapsedTime time;
    boolean forward = false;
    boolean back = false;
    boolean left = false;
    boolean right = false;
    boolean turnleft = false;
    boolean turnright = false;

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

        if(forward){
            drive.setPowerOne(0.5);
            drive.setPowerTwo(0.5);
            drive.setPowerThree(0.5);
            drive.setPowerFour(0.5);
        } else {
            drive.setPowerOne(0.0);
            drive.setPowerTwo(0.0);
            drive.setPowerThree(0.0);
            drive.setPowerFour(0.0);
        }

        if(back){
            drive.setPowerOne(-0.5);
            drive.setPowerTwo(-0.5);
            drive.setPowerThree(-0.5);
            drive.setPowerFour(-0.5);
        } else {
            drive.setPowerOne(0.0);
            drive.setPowerTwo(0.0);
            drive.setPowerThree(0.0);
            drive.setPowerFour(0.0);
        }

        if(left){
            drive.setPowerOne(1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(1);
            drive.setPowerFour(-1);
        } else {
            drive.setPowerOne(0.0);
            drive.setPowerTwo(0.0);
            drive.setPowerThree(0.0);
            drive.setPowerFour(0.0);
        }

        if(right){
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(-1);
            drive.setPowerFour(1);
        } else {
            drive.setPowerOne(0.0);
            drive.setPowerTwo(0.0);
            drive.setPowerThree(0.0);
            drive.setPowerFour(0.0);
        }

        if(turnleft){
            drive.setPowerOne(-0.5);
            drive.setPowerTwo(0.5);
            drive.setPowerThree(0.5);
            drive.setPowerFour(-0.5);
        } else {
            drive.setPowerOne(0.0);
            drive.setPowerTwo(0.0);
            drive.setPowerThree(0.0);
            drive.setPowerFour(0.0);
        }

        if(turnright){
            drive.setPowerOne(0.5);
            drive.setPowerTwo(-0.5);
            drive.setPowerThree(-0.5);
            drive.setPowerFour(0.5);
        } else {
            drive.setPowerOne(0.0);
            drive.setPowerTwo(0.0);
            drive.setPowerThree(0.0);
            drive.setPowerFour(0.0);
        }

        while (time.seconds() < 0.5 && opModeIsActive()) {
            sleep(300);
            forward = true;
            sleep(500);
            forward = false;
            sleep(300);

            back = true;
            sleep(500);
            back = false;
            sleep(300);

            left = true;
            sleep(500);
            left = false;
            sleep(300);

            right = true;
            sleep(500);
            right = false;
            sleep(300);

            turnright = true;
            sleep(500);
            turnright = false;
            sleep(300);

            turnleft = true;
            sleep(500);
            turnleft = false;
            sleep(300);

        }
    }
}