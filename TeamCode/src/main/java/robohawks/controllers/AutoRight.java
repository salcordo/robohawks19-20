package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.openftc.revextensions2.ExpansionHubEx;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "AutoRight")
public class AutoRight extends LinearOpMode {

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

            //RIGHT
            drive.setPowerOne(-1 * poweradjust);
            drive.setPowerTwo(1 * poweradjust);
            drive.setPowerThree(-1 * poweradjust);
            drive.setPowerFour(1 * poweradjust);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
        }
    }
}