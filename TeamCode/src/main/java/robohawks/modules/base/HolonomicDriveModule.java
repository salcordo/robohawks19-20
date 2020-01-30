package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.async.Operation;
import robohawks.async.Sequence;
import robohawks.async.error.DeviceLockedException;
import robohawks.async.error.OperationNotRunningException;

public class HolonomicDriveModule {
    private DcMotor motor_1;
    private DcMotor motor_2;
    private DcMotor motor_3;
    private DcMotor motor_4;

    private boolean locked;

    public HolonomicDriveModule(HardwareMap hardwareMap){
        motor_1 = hardwareMap.dcMotor.get("m0");
        motor_2 = hardwareMap.dcMotor.get("m1");
        motor_3 = hardwareMap.dcMotor.get("m2");
        motor_4 = hardwareMap.dcMotor.get("m3");
    }

    public void setPowerOne(double power) { motor_1.setPower(power); }
    public void setPowerTwo(double power) { motor_2.setPower(power); }
    public void setPowerThree(double power) { motor_3.setPower(power); }
    public void setPowerFour(double power) { motor_4.setPower(power); }

    public Operation holonomicDrive(double seconds, double powerOne, double powerTwo, double powerThree, double powerFour){
        return new HolonomicDrive(this, seconds, powerOne, powerTwo, powerThree, powerFour);
    }

    private class HolonomicDrive implements Operation{
        private HolonomicDriveModule holonomicDriveModule;
        double seconds;
        double powerOne;
        double powerTwo;
        double powerThree;
        double powerFour;
        private ElapsedTime time;
        private double targetTime;
        private boolean running;

        public HolonomicDrive(HolonomicDriveModule holonomicDriveModule, double seconds, double powerOne, double powerTwo, double powerThree, double powerFour){
            this.holonomicDriveModule = holonomicDriveModule;
            this.seconds = seconds;
            this.powerOne = powerOne;
            this.powerTwo = powerTwo;
            this.powerThree = powerThree;
            this.powerFour = powerFour;
            this.time = new ElapsedTime();
        }

        @Override
        public void start(Sequence.Callback callback) {
            if (holonomicDriveModule.locked){
                callback.err(new DeviceLockedException(this));
            }else{
                holonomicDriveModule.locked = true;
                running = true;
                targetTime = time.time() + seconds;
                holonomicDriveModule.setPowerOne(powerOne);
                holonomicDriveModule.setPowerTwo(powerTwo);
                holonomicDriveModule.setPowerThree(powerThree);
                holonomicDriveModule.setPowerFour(powerFour);
            }
        }

        @Override
        public void loop(Sequence.Callback callback) {
            if (time.time() > targetTime) {
                stop(callback);
            }
        }

        @Override
        public void stop(Sequence.Callback callback) {
            if(running) {
                holonomicDriveModule.locked = false;
                holonomicDriveModule.setPowerOne(0);
                holonomicDriveModule.setPowerTwo(0);
                holonomicDriveModule.setPowerThree(0);
                holonomicDriveModule.setPowerFour(0);
                callback.next();
            } else {
                callback.err(new OperationNotRunningException(this));
            }
        }

    }
}
