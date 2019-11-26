        package robohawks.controllers;

        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.CRServo;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.Range;

        import robohawks.controllers.old.Controller;
        import robohawks.modules.base.HolonomicDriveModule;

@TeleOp(name = "TeleOp", group = "Teleop")
public class TeleopController extends Controller {

    HolonomicDriveModule drive;
    DcMotor motorLift;
    DcMotor motorCwheels;

    float precisionx = 1;
    float precisiony = 1;

    @Override
    public void init() {
        drive = new HolonomicDriveModule(hardwareMap);
        motorLift = hardwareMap.dcMotor.get("motorLift");
        motorCwheels = hardwareMap.dcMotor.get("motorCwheels");
    }

    @Override
    public void loop() {
        super.loop();

        // DRIVE
        float gamepad1LeftY = gamepad1.left_stick_y * precisiony;
        float gamepad1LeftX = -gamepad1.left_stick_x * precisionx;
        float gamepad1RightX = gamepad1.right_stick_x * precisionx;

        // holonomic formulas
        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

        // clip the right/left values so that the values never exceed +/- 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

        // write the values to the motors
        drive.setPowerOne(-FrontRight/2);
        drive.setPowerTwo(FrontLeft/2);
        drive.setPowerThree(BackLeft/2);
        drive.setPowerFour(-BackRight/2);


        //TOGGLE COMPLIANCE WHEELS
        boolean compliancewheels = false;
        if((gamepad1.a) && (compliancewheels = false)){
            compliancewheels = true;
            motorCwheels.setPower(1);
        } else if ((gamepad1.a) && (compliancewheels = true)){
            compliancewheels = false;
            motorCwheels.setPower(0);
        }

        //EXAMPLE BUTTON
        //if(input){
        //  motor.setPower(value);
        //  servo.setPower(value);
        //  servo.setPosition(value);
        //} else {
        //  motor.setPower(0);
        //  servo.setPower(0);
        //  servo.setPosition(0);

        //PRECISION X AND Y
        if (gamepad1.dpad_down){
            precisionx = (float) 0.6;
            precisiony = (float) 0.4;
        } else if (gamepad1.dpad_up){
            precisionx = (float) 1;
            precisiony = (float) 1;
        }

        //LIFT ARM
        if(gamepad2.right_bumper){
            motorLift.setPower(-1);
        } else {
            motorLift.setPower(0);
        }

        //LOWER ARM
        if(gamepad2.left_bumper){
            motorLift.setPower(1);
        } else {
            motorLift.setPower(0);
        }
    }
}