package robohawks.controllers.old;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import robohawks.modules.base.HolonomicDriveModule;

/**
 * Created by Paarth Tandon on 1/3/2018.
 */


public class ContestAutoController extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private HolonomicDriveModule drive;
    private DcMotor leftarm;
    private DcMotor rightarm;
    private Servo jewl;
    private boolean locked;


    @Override
    public void runOpMode() throws InterruptedException {

        drive = new HolonomicDriveModule(hardwareMap);
        leftarm = hardwareMap.dcMotor.get("leftarm");
        rightarm = hardwareMap.dcMotor.get("rightarm");
        jewl = hardwareMap.servo.get("jewl");

        waitForStart();
        runtime.reset();

        jewl.setPosition(0);

        sleep(1000);

        runtime.reset();

        while(opModeIsActive() && runtime.seconds() <= 1) {

            // Forward
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(1);
            drive.setPowerFour(-1);
            idle();
        }

        sleep(1000);

        jewl.setPosition(1);

        sleep(1000);

        runtime.reset();


        while(opModeIsActive() && runtime.seconds() <= 1){
            //Turn Right
            drive.setPowerOne(-1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(-1);
            drive.setPowerFour(-1);

            idle();
        }

        sleep(1000);

        runtime.reset();



        while(opModeIsActive() && runtime.seconds() <= 1) {

            // Back
            drive.setPowerOne(1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(-1);
            drive.setPowerFour(1);

            idle();

        }

        sleep(1000);
        runtime.reset();



        while(opModeIsActive() && runtime.seconds() <= 1) {
            //Drop
            leftarm.setPower(.5);
            rightarm.setPower(.5);

            idle();
        }



        runtime.reset();
    }

}
