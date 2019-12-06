package robohawks.controllers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

@Autonomous(name = "AutoFoundation")
public class AutoFoundation extends LinearOpMode {

    HolonomicDriveModule drive;
    ElapsedTime time;
    DcMotor motorLift;

    @Override
    public void runOpMode() {

        //**********INIT START**********//

        drive = new HolonomicDriveModule(hardwareMap);
        motorLift = hardwareMap.dcMotor.get("motorLift");
        time = new ElapsedTime();
        waitForStart();

        //**********INIT STOP**********//
        //**********DETECT START**********//

        time.reset();

        while (time.seconds() < 0.5 && opModeIsActive()) {

            //Forward
            drive.setPowerOne(1);
            drive.setPowerTwo(1);
            drive.setPowerThree(1);
            drive.setPowerFour(1);
            sleep(500);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);


            //ARM
            motorLift.setPower(0.75);
            sleep(2500);
            motorLift.setPower(0);

            //BACKWARDS
            sleep(1500);
            drive.setPowerOne(-1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(-1);
            drive.setPowerFour(-1);
            sleep(2000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(200);

            //ARM
            motorLift.setPower(1);
            sleep(1000);
            motorLift.setPower(0);
            sleep(200);
            motorLift.setPower(-1);
            sleep(1000);
            motorLift.setPower(0);

            //LEFT
            //RIGHT
            drive.setPowerOne(1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(1);
            drive.setPowerFour(-1);
            sleep(2500);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
        }
    }
}