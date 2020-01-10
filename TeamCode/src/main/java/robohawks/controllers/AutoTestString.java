package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "AutoTestString")
public class AutoTestString extends LinearOpMode {

    HolonomicDriveModule drive;
    DcMotor motorRCurl;
    DcMotor motorLCurl;
    Servo servoLoaf;
    Servo servoFoundation;
    DcMotor motorRSuck;
    DcMotor motorLSuck;
    ElapsedTime time;
    String state = "off";

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

        if(state.equals("forward")){
            drive.setPowerOne(0.5);
            drive.setPowerTwo(0.5);
            drive.setPowerThree(0.5);
            drive.setPowerFour(0.5);
        }

        if(state.equals("back")){
            drive.setPowerOne(-0.5);
            drive.setPowerTwo(-0.5);
            drive.setPowerThree(-0.5);
            drive.setPowerFour(-0.5);
        }

        if(state.equals("left")){
            drive.setPowerOne(1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(1);
            drive.setPowerFour(-1);
        }

        if(state.equals("right")){
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(-1);
            drive.setPowerFour(1);
        }

        if(state.equals("turnleft")){
            drive.setPowerOne(-0.5);
            drive.setPowerTwo(0.5);
            drive.setPowerThree(0.5);
            drive.setPowerFour(-0.5);
        }

        if(state.equals("turnright")){
            drive.setPowerOne(0.5);
            drive.setPowerTwo(-0.5);
            drive.setPowerThree(-0.5);
            drive.setPowerFour(0.5);
        }

        if(state.equals("off")){
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
        }

        while (time.seconds() < 0.5 && opModeIsActive()) {
            sleep(300);
            state = "forward";
            sleep(500);
            state = "off";
            sleep(300);

            state = "back";
            sleep(500);
            state = "off";
            sleep(300);

            state = "left";
            sleep(500);
            state = "off";
            sleep(300);

            state = "right";
            sleep(500);
            state = "off";
            sleep(300);

            state = "turnright";
            sleep(500);
            state = "off";
            sleep(300);

            state = "turnleft;";
            sleep(500);
            state = "off";
            sleep(300);

        }
    }
}