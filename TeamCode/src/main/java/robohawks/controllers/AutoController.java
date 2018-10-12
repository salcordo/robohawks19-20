package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name="Autonomous")
public class AutoController extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private HolonomicDriveModule drive;

    @Override
    public void runOpMode() throws InterruptedException {
        /* INIT */
        drive = new HolonomicDriveModule(hardwareMap);

        waitForStart();

        /* SEQUENCE */

        //backwards
        drive.setPowerOne(-1);
        drive.setPowerTwo(-1);
        drive.setPowerThree(-1);
        drive.setPowerFour(-1);
        sleep(1500);

        //stop
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(1000);

        //clockwise
        drive.setPowerOne(-1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(-1);
        sleep(1000);

        //stop
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(1000);

        //forward
        drive.setPowerOne(1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(1);
        sleep(1000);

        //stop
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(1000);

        //clockwise
        drive.setPowerOne(-1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(-1);
        sleep(1000);

        //stop
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(1000);

        //forward
        drive.setPowerOne(1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(1);
        sleep(3000);

        //stop
        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        sleep(1000);

        //backwards
        drive.setPowerOne(-1);
        drive.setPowerTwo(-1);
        drive.setPowerThree(-1);
        drive.setPowerFour(-1);
        sleep(5000);
    }
}
