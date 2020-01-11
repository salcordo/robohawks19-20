package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.async.Operation;
import robohawks.async.Sequence;
import robohawks.async.error.DeviceLockedException;
import robohawks.async.error.OperationNotRunningException;

public class HolonomicDriveAutoModule {
    private DcMotor motor_1;
    private DcMotor motor_2;
    private DcMotor motor_3;
    private DcMotor motor_4;

    private boolean locked;

    public HolonomicDriveAutoModule(HardwareMap hardwareMap){
        motor_1 = hardwareMap.dcMotor.get("m1");
        motor_2 = hardwareMap.dcMotor.get("m2");
        motor_3 = hardwareMap.dcMotor.get("m3");
        motor_4 = hardwareMap.dcMotor.get("m4");
    }

    public void setPowerOne(double power) { motor_1.setPower(power); }
    public void setPowerTwo(double power) { motor_2.setPower(power); }
    public void setPowerThree(double power) { motor_3.setPower(power); }
    public void setPowerFour(double power) { motor_4.setPower(power); }

    public Operation holonomicDrive(double seconds, double powerOne, double powerTwo, double powerThree, double powerFour){
        return new HolonomicDrive(this, seconds, powerOne, powerTwo, powerThree, powerFour);
    }

    private class HolonomicDrive implements Operation{
        private HolonomicDriveAutoModule holonomicDriveAutoModule;
        double seconds;
        double powerOne;
        double powerTwo;
        double powerThree;
        double powerFour;
        private ElapsedTime time;
        private double targetTime;
        private boolean running;

        public HolonomicDrive(HolonomicDriveAutoModule holonomicDriveAutoModule, double seconds, double powerOne, double powerTwo, double powerThree, double powerFour){
            this.holonomicDriveAutoModule = holonomicDriveAutoModule;
            this.seconds = seconds;
            this.powerOne = powerOne;
            this.powerTwo = powerTwo;
            this.powerThree = powerThree;
            this.powerFour = powerFour;
            this.time = new ElapsedTime();
        }

        @Override
        public void start(Sequence.Callback callback) {
            if (holonomicDriveAutoModule.locked){
                callback.err(new DeviceLockedException(this));
            }else{
                holonomicDriveAutoModule.locked = true;
                running = true;
                targetTime = time.time() + seconds;
                holonomicDriveAutoModule.setPowerOne(powerOne);
                holonomicDriveAutoModule.setPowerTwo(powerTwo);
                holonomicDriveAutoModule.setPowerThree(powerThree);
                holonomicDriveAutoModule.setPowerFour(powerFour);
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
                holonomicDriveAutoModule.locked = false;
                holonomicDriveAutoModule.setPowerOne(0);
                holonomicDriveAutoModule.setPowerTwo(0);
                holonomicDriveAutoModule.setPowerThree(0);
                holonomicDriveAutoModule.setPowerFour(0);
                callback.next();
            } else {
                callback.err(new OperationNotRunningException(this));
            }
        }

    }
}
