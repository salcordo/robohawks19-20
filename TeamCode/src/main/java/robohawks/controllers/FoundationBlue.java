package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "FoundationBlue")
public class FoundationBlue extends LinearOpMode {

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
        //**********DETECT START**********//

        time.reset();


        while (time.seconds() < 0.5 && opModeIsActive()) {

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

            //FORWARD
            drive.setPowerOne(1);
            drive.setPowerTwo(1);
            drive.setPowerThree(1);
            drive.setPowerFour(1);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);

            //RIGHT
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(-1);
            drive.setPowerFour(1);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);

            //TURN LEFT

            //LEFT

            //RECOGNIZE

            //Go to brick

            //PICK UP BRICK

            //RIGHT

            //FORWARD

            //Drop brick

            //BACKWARDS



        }
    }
}