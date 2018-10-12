package robohawks.controllers.old;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.modules.base.HolonomicDriveModule;

/**
 * Created by Paarth Tandon on 2/1/2018.
 */

public class ContestAutoControllerv2 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private HolonomicDriveModule drive;
    private DcMotor leftarm;
    private DcMotor rightarm;
    private Servo jewl;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new HolonomicDriveModule(hardwareMap);
        leftarm = hardwareMap.dcMotor.get("leftarm");
        rightarm = hardwareMap.dcMotor.get("rightarm");
        jewl = hardwareMap.servo.get("jewl");

        waitForStart();

        jewl.setPosition(0);
        sleep(1000);

        // Forward
        drive.setPowerOne(-1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(-1);

        sleep(500);

        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        // Forward End

        jewl.setPosition(1);
        sleep(1000);

        // Forward
        drive.setPowerOne(-1);
        drive.setPowerTwo(1);
        drive.setPowerThree(1);
        drive.setPowerFour(-1);

        sleep(500);

        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        // Forward End

        sleep(1000);

        // Turn Left
        drive.setPowerOne(-1);
        drive.setPowerTwo(-1);
        drive.setPowerThree(-1);
        drive.setPowerFour(-1);

        sleep(750);

        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        // Turn End

        sleep(1000);

        // Back
        drive.setPowerOne(1);
        drive.setPowerTwo(-1);
        drive.setPowerThree(-1);
        drive.setPowerFour(1);

        sleep(500);

        drive.setPowerOne(0);
        drive.setPowerTwo(0);
        drive.setPowerThree(0);
        drive.setPowerFour(0);
        // Back End

        sleep(1000);

        // Drop
        leftarm.setPower(.5);
        rightarm.setPower(.5);

        sleep(5000);

        leftarm.setPower(0);
        rightarm.setPower(0);
        // Drop End
    }
}
