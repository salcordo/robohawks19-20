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
    int jonathan = 0;
    int joseph = 0;
    int jotaro = 0;
    int josuke = 0;
    int giorno = 0;
    int jolyne = 0;
    boolean active = false;
    boolean underbridge = false;
    boolean runningscan = false;
    int scancount = 0;

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
            sleep(150);

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
            sleep(150);

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
            sleep(150);

            //TURN LEFT
            drive.setPowerOne(-1);
            drive.setPowerTwo(-1);
            drive.setPowerThree(1);
            drive.setPowerFour(1);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(150);

            //LEFT
            drive.setPowerOne(-1);
            drive.setPowerTwo(1);
            drive.setPowerThree(-1);
            drive.setPowerFour(1);
            sleep(1000);
            drive.setPowerOne(0);
            drive.setPowerTwo(0);
            drive.setPowerThree(0);
            drive.setPowerFour(0);
            sleep(150);

            //RECOGNIZE
            runningscan = true;
            while(runningscan == true){
                //Jonathan
                if(jonathan == 1 && !active){
                    active = true;

                    //MOVE TO BRICK
                    //LEFT
                    drive.setPowerOne(-1);
                    drive.setPowerTwo(1);
                    drive.setPowerThree(-1);
                    drive.setPowerFour(1);
                    sleep(1000);
                    drive.setPowerOne(0);
                    drive.setPowerTwo(0);
                    drive.setPowerThree(0);
                    drive.setPowerFour(0);
                    sleep(150);

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
                    sleep(150);

                    //PICK UP BRICK
                    servoLoaf.setPosition(45);
                    sleep(250);

                    //MOVE TO COMMON END
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
                    sleep(150);

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
                    sleep(150);

                    //TAKE BRICK UNDER BRIDGE
                    underbridge = true;
                }

                //Joseph
                if(joseph == 1 && !active){

                }

                //Jotaro
                if(jotaro == 1){

                }

                //Josuke
                if(josuke == 1){

                }

                //Giorno
                if(giorno == 1){

                }

                //Jolyne
                if(jonathan == 1){

                }

                //GO UNDER BRIDGE
                if(underbridge == true){
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
                    sleep(150);

                    //Drop brick
                    servoLoaf.setPosition(90);
                    sleep(250);

                    //BACKWARDS
                    drive.setPowerOne(-1);
                    drive.setPowerTwo(-1);
                    drive.setPowerThree(-1);
                    drive.setPowerFour(-1);
                    sleep(1000);
                    drive.setPowerOne(0);
                    drive.setPowerTwo(0);
                    drive.setPowerThree(0);
                    drive.setPowerFour(0);
                    sleep(150);
                    underbridge = false;
                    scancount = scancount + 1;
                }

                if(scancount == 2){
                    runningscan = false;
                }
            }
        }
    }
}