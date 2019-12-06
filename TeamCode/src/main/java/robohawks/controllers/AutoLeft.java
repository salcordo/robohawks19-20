package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "AutoLeft")
public class AutoLeft extends LinearOpMode {

    HolonomicDriveModule drive;
    ElapsedTime time;

    @Override
    public void runOpMode() {

        //**********INIT START**********//

        drive = new HolonomicDriveModule(hardwareMap);
        time = new ElapsedTime();
        waitForStart();

        //**********INIT STOP**********//
        //**********DETECT START**********//

        time.reset();

        while (time.seconds() < 0.5 && opModeIsActive()) {

            //LEFT
            drive.setPowerOne(1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(1);
            drive.setPowerFour(-1);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
        }
    }
}