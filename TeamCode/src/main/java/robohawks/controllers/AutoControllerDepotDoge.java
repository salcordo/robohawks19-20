package robohawks.controllers;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

public class AutoControllerDepotDoge extends LinearOpMode {

    private GoldAlignDetector detector;
    HolonomicDriveModule drive;
    DcMotor liftArm;
    DcMotor liftArm2;
    Servo phone;
    Servo drop;

    ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {

        //**********INIT START**********//

        drive = new HolonomicDriveModule(hardwareMap);
        liftArm = hardwareMap.dcMotor.get("liftArm");
        liftArm2 = hardwareMap.dcMotor.get("liftArm2");
        phone = hardwareMap.servo.get("phone");
        drop = hardwareMap.servo.get("drop");
        time = new ElapsedTime();

        phone.setPosition(1);
        drop.setPosition(0);

        // Set up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable(); // Start the detector!

        telemetry.addData("IsAligned", detector.getAligned()); // Is the bot aligned with the gold mineral?
        telemetry.addData("X Pos", detector.getXPosition()); // Gold X position.

        waitForStart();

        //**********INIT STOP**********//

        //**********DROP START**********//

        //DROP
        liftArm.setPower(-1);
        liftArm2.setPower(1);
        sleep(4000);
        liftArm.setPower(0);
        liftArm2.setPower(0);

        //CLOCKWISE
        drive.setPowerOne(-1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(-1);
        sleep(300);
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(500);

        //BACK
        drive.setPowerOne(-1);
        drive.setPowerTwo(-1);
        drive.setPowerThree(-1);
        drive.setPowerFour(-1);
        sleep(100);
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(500);

        //COUNTER CLOCKWISE
        drive.setPowerOne(1);
        drive.setPowerTwo(-1);
        drive.setPowerThree(-1);
        drive.setPowerFour(1);
        sleep(300);
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(500);

        //**********DROP STOP**********//

        //**********MINERAL TAP START**********//

        //SET SERVO
        phone.setPosition(.65);

        //BACK
        drive.setPowerOne(-1);
        drive.setPowerTwo(-1);
        drive.setPowerThree(-1);
        drive.setPowerFour(-1);
        sleep(150);
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(500);

        //CLOCKWISE
        drive.setPowerOne(-1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(-1);
        sleep(700);
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(500);

        //BACK
        drive.setPowerOne(-1);
        drive.setPowerTwo(-1);
        drive.setPowerThree(-1);
        drive.setPowerFour(-1);
        sleep(200);
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(500);

        time.reset();

        boolean gold = false;
        while(!gold){
            drive.setPowerOne(.25);
            drive.setPowerTwo(.25);
            drive.setPowerThree(.25);
            drive.setPowerFour(.25);

            telemetry.addData("IsAligned" , detector.getAligned());
            telemetry.addData("X Pos" , detector.getXPosition());

            if(detector.getAligned()){
                gold = true;
            }

            if(time.seconds() > 5){
                gold = true;
            }
        }

        detector.disable();

        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(500);

        //CLOCKWISE
        drive.setPowerOne(-1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(-1);
        sleep(1000);
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(500);

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
        sleep(500);

        phone.setPosition(1);

        //DROP
        drop.setPosition(1);

        //**********MINERAL TAP STOP**********//

    }

}
