package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import robohawks.async.Operation;
import robohawks.async.Sequence;
import robohawks.async.SimpleOperation;
import robohawks.async.error.DeviceLockedException;
import robohawks.async.error.OperationNotRunningException;

/**
 * Created by fchoi on 9/25/2016.
 */
// This is a "base" class, which is a combination of bindings and modules
public class DriveModule {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private boolean locked;

    // This initializes the bindings
    public DriveModule(HardwareMap hwMap) {
        leftMotor = hwMap.dcMotor.get("driveLeft");
        rightMotor = hwMap.dcMotor.get("driveRight");

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    // This is a binding
    public void setPowerLeft(double power) {
        leftMotor.setPower(power);
    }

    public void setPowerRight(double power) {
        rightMotor.setPower(power);
    }

    public void setHeadingXZ(double x, double z) {
        setPowerLeft(z - x);
        setPowerRight(z + x);
    }

    public void setHeadingXP(double x, double p) {
        if(x > 0) {
            setPowerLeft(p - p * x);
            setPowerRight(p);
        } else if(x < 0) {
            setPowerRight(p + p * x);
            setPowerLeft(p);
        } else {
            setPowerLeft(p);
            setPowerRight(p);
        }
    }

    public Operation setHeadingXPOp(final double x, final double p) {
        return new SimpleOperation() {
            @Override
            public void start(Sequence.Callback callback) {
                setHeadingXP(x, p);
                callback.next();
            }
        };
    }

    /**
     * Creates an operation that drives the robot with control to the left and right power for 'second' seconds
     * @param seconds the amount of time to drive in an arc in seconds
     * @param leftPower the amount of power given to the left side
     * @param rightPower the amount of power given to the right side
     * @return an operation
     */
    public Operation drive(double seconds, double leftPower, double rightPower) {
        return new Drive(this, seconds, leftPower, rightPower);
    }

    private class Drive implements Operation {
        private DriveModule driveModule;
        private double seconds;
        private double leftPower;
        private double rightPower;
        private ElapsedTime time;
        private double targetTime;
        private boolean running;

        public Drive(DriveModule driveModule, double seconds, double leftPower, double rightPower){
            this.driveModule = driveModule;
            this.seconds = seconds;
            this.leftPower = leftPower;
            this.rightPower = rightPower;
            this.time = new ElapsedTime();
        }

        @Override
        public void start(Sequence.Callback callback) {
            if (driveModule.locked){
                callback.err(new DeviceLockedException(this));
            }else{
                driveModule.locked = true;
                running = true;
                targetTime = time.time() + seconds;
                driveModule.setPowerLeft(leftPower);
                driveModule.setPowerRight(rightPower);
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
                driveModule.locked = false;
                driveModule.setPowerLeft(0);
                driveModule.setPowerRight(0);
                callback.next();
            } else {
                callback.err(new OperationNotRunningException(this));
            }
        }
    }

}
