package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.openftc.revextensions2.ExpansionHubEx;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "LoadingBlue")
public class LoadingBlue extends LinearOpMode {

    HolonomicDriveModule drive;
    DcMotor motorRCurl;
    DcMotor motorLCurl;
    Servo servoLoaf;
    Servo servoFoundation;
    DcMotor motorRSuck;
    DcMotor motorLSuck;
    ElapsedTime time;
    ExpansionHubEx expansionHub;
    double poweradjust;

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
        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 2");
        telemetry.addData("12v monitor", expansionHub.read12vMonitor(ExpansionHubEx.VoltageUnits.VOLTS)); //Battery voltage



        waitForStart();

        //**********INIT STOP**********//
        //**********DETECT START*********FoundationBlue*//

        time.reset();


        while (time.seconds() < 0.5 && opModeIsActive()) {
            double voltage = expansionHub.read12vMonitor(ExpansionHubEx.VoltageUnits.VOLTS);
            poweradjust = (13/voltage);
            poweradjust = Range.clip(poweradjust, 0, 1);
            //FOUNDATION
            servoFoundation.setPosition(90);
            sleep(150);

            //Right
            drive.setPowerOne(-1 * poweradjust);
            drive.setPowerTwo(1 * poweradjust);
            drive.setPowerThree(-1 * poweradjust);
            drive.setPowerFour(1 * poweradjust);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //DROP CURL
            motorLCurl.setPower(-0.2 * poweradjust);
            motorRCurl.setPower(-0.2 * poweradjust);
            sleep(400);
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
            sleep(300);

            //FORWARD
            drive.setPowerOne(0.7 * poweradjust);
            drive.setPowerTwo(0.7 * poweradjust);
            drive.setPowerThree(0.7 * poweradjust);
            drive.setPowerFour(0.7 * poweradjust);
            sleep(1500);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //RAISE CURL
            motorLCurl.setPower(1);
            motorRCurl.setPower(1);
            sleep(300);
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
            sleep(300);

            //TURN Left
            drive.setPowerOne(-0.7 * poweradjust);
            drive.setPowerTwo(0.7 * poweradjust);
            drive.setPowerThree(0.7 * poweradjust);
            drive.setPowerFour(-0.7 * poweradjust);
            sleep(700);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.5 * poweradjust);
            drive.setPowerTwo(-0.5 * poweradjust);
            drive.setPowerThree(-0.5 * poweradjust);
            drive.setPowerFour(-0.5 * poweradjust);
            sleep(400);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(0);
            sleep(300);

            //FORWARD
            drive.setPowerOne(0.4 * poweradjust);
            drive.setPowerTwo(0.4 * poweradjust);
            drive.setPowerThree(0.4 * poweradjust);
            drive.setPowerFour(0.4 * poweradjust);
            sleep(3200);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(90);
            sleep(300);

            //LEFT
            drive.setPowerOne(0.95 * poweradjust);
            drive.setPowerTwo(-0.95 * poweradjust);
            drive.setPowerThree(0.95 * poweradjust);
            drive.setPowerFour(-0.95 * poweradjust);
            sleep(1300);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.5 * poweradjust);
            drive.setPowerTwo(-0.5 * poweradjust);
            drive.setPowerThree(-0.5 * poweradjust);
            drive.setPowerFour(-0.5 * poweradjust);
            sleep(650);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //TURN LEFT
            drive.setPowerOne(-0.75 * poweradjust);
            drive.setPowerTwo(0.75 * poweradjust);
            drive.setPowerThree(0.75 * poweradjust);
            drive.setPowerFour(-0.75 * poweradjust);
            sleep(800);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.5 * poweradjust);
            drive.setPowerTwo(-0.5 * poweradjust);
            drive.setPowerThree(-0.5 * poweradjust);
            drive.setPowerFour(-0.5 * poweradjust);
            sleep(500);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(0);
            sleep(300);

            //TURN LEFT
            drive.setPowerOne(-0.7 * poweradjust);
            drive.setPowerTwo(0.7 * poweradjust);
            drive.setPowerThree(0.7 * poweradjust);
            drive.setPowerFour(-0.7 * poweradjust);
            sleep(600);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.6 * poweradjust);
            drive.setPowerTwo(-0.6 * poweradjust);
            drive.setPowerThree(-0.6 * poweradjust);
            drive.setPowerFour(-0.6 * poweradjust);
            sleep(500);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(90);
            sleep(300);

            //LEFT
            drive.setPowerOne(1 * poweradjust);
            drive.setPowerTwo(-1 * poweradjust);
            drive.setPowerThree(1 * poweradjust);
            drive.setPowerFour(-1 * poweradjust);
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
            drive.setPowerOne(0.5 * poweradjust);
            drive.setPowerTwo(0.5 * poweradjust);
            drive.setPowerThree(0.5 * poweradjust);
            drive.setPowerFour(0.5 * poweradjust);
            sleep(600);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);
        }
    }
}