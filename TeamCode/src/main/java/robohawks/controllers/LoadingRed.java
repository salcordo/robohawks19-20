package robohawks.controllers;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import org.openftc.revextensions2.ExpansionHubEx;
        import robohawks.modules.base.HolonomicDriveModule;
        import com.qualcomm.robotcore.util.Range;


@Autonomous(name = "LoadingRed")
public class LoadingRed extends LinearOpMode {

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

            //Left
            drive.setPowerOne(1 * poweradjust);
            drive.setPowerTwo(-1* poweradjust);
            drive.setPowerThree(1 * poweradjust);
            drive.setPowerFour(-1 * poweradjust);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(200);

            //DROP CURL
            motorLCurl.setPower(-0.2 * poweradjust);
            motorRCurl.setPower(-0.2 * poweradjust);
            sleep(400);
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
            sleep(150);

            //TURN LEFT
            drive.setPowerOne(-0.75 * poweradjust);
            drive.setPowerTwo(0.75 * poweradjust);
            drive.setPowerThree(0.75 * poweradjust);
            drive.setPowerFour(-0.75 * poweradjust);
            sleep(95);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
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
            motorLCurl.setPower(1 * poweradjust);
            motorRCurl.setPower(1 * poweradjust);
            sleep(200);
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
            sleep(300);

            //RIGHT
            drive.setPowerOne(-1 * poweradjust);
            drive.setPowerTwo(1 * poweradjust);
            drive.setPowerThree(-1 * poweradjust);
            drive.setPowerFour(1 * poweradjust);
            sleep(200);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //TURN RIGHT
            drive.setPowerOne(0.7 * poweradjust);
            drive.setPowerTwo(-0.7 * poweradjust);
            drive.setPowerThree(-0.7 * poweradjust);
            drive.setPowerFour(0.7 * poweradjust);
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
            sleep(400);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(0);

            //FOUNDATION
            servoFoundation.setPosition(0);
            sleep(300);

            //FORWARD
            drive.setPowerOne(0.4 * poweradjust);
            drive.setPowerTwo(0.4 * poweradjust);
            drive.setPowerThree(0.4 * poweradjust);
            drive.setPowerFour(0.4 * poweradjust);
            sleep(3800);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //FOUNDATION
            servoFoundation.setPosition(90);
            sleep(300);

            //RIGHT
            drive.setPowerOne(-1 * poweradjust);
            drive.setPowerTwo(1 * poweradjust);
            drive.setPowerThree(-1 * poweradjust);
            drive.setPowerFour(1 * poweradjust);
            sleep(1400);
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

            //TURN RIGHT
            drive.setPowerOne(0.75 * poweradjust);
            drive.setPowerTwo(-0.75 * poweradjust);
            drive.setPowerThree(-0.75 * poweradjust);
            drive.setPowerFour(0.75 * poweradjust);
            sleep(600);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);

            //BACK
            drive.setPowerOne(-0.65 * poweradjust);
            drive.setPowerTwo(-0.65 * poweradjust);
            drive.setPowerThree(-0.65 * poweradjust);
            drive.setPowerFour(-0.65 * poweradjust);
            sleep(200);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(50);

            //FOUNDATION
            servoFoundation.setPosition(0);
            sleep(300);

            //TURN RIGHT
            drive.setPowerOne(0.7 * poweradjust);
            drive.setPowerTwo(-0.7 * poweradjust);
            drive.setPowerThree(-0.7 * poweradjust);
            drive.setPowerFour(0.7 * poweradjust);
            sleep(600);
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
            sleep(100);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(50);

            //FOUNDATION
            servoFoundation.setPosition(90);
            sleep(200);

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
            sleep(100);

            //Lower SUCK arm
            motorLCurl.setPower(-0.2 * poweradjust);
            motorRCurl.setPower(-0.2 * poweradjust);
            sleep(500);
            motorLCurl.setPower(0);
            motorRCurl.setPower(0);
            sleep(200);

            //FORWARD
            drive.setPowerOne(0.4 * poweradjust);
            drive.setPowerTwo(0.4 * poweradjust);
            drive.setPowerThree(0.4 * poweradjust);
            drive.setPowerFour(0.4 * poweradjust);
            sleep(600);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(300);
        }
    }
}