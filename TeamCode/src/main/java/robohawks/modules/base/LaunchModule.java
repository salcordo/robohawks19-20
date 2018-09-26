package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import robohawks.async.Operation;
import robohawks.async.Sequence;
import robohawks.async.Sequencer;
import robohawks.async.SimpleOperation;
import robohawks.async.error.DeviceLockedException;

/**
 * This module launches stuff --Fred Choi 2016
 */
public class LaunchModule {
    private DcMotor wheelMotor;
    private DcMotor motor2;
    private DcMotor feedMotor;
    private DcMotor loadMotor;

    private boolean locked;

    public LaunchModule(HardwareMap hwMap) {
        wheelMotor = hwMap.dcMotor.get("launchMotorWheel");
        wheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        feedMotor = hwMap.dcMotor.get("launchFeedMotor");
        feedMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        loadMotor = hwMap.dcMotor.get("loadMotor");
        loadMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setFeedPower(double power) {
        feedMotor.setPower(power);
    }

    public void setWheelPower(double power) {
        wheelMotor.setPower(power);
    }

    public void setLoadPower(double power) {
        loadMotor.setPower(power);
    }

    public double getLaunchPower() {
        return wheelMotor.getPower();
    }

    public double getLoadPower() {
        return loadMotor.getPower();
    }

    public boolean isLocked() {
        return locked;
    }

    public Operation load(double loadTime) {
        return new Load(this, loadTime);
    }

    public Operation loadDecel() {
        return new LoadDecel(this);
    }

    public Operation launchRev(double power) {
        return new LaunchRev(this, power);
    }

    public Operation launchDecel() {
        return new LaunchDecel(this);
    }

    public Operation launch(Sequencer sequencer) {
        return new Launch(this, sequencer);
    }

    private class Load implements Operation {
        private LaunchModule launchModule;
        private ElapsedTime time;

        private double initialTime;
        private double loadTime;

        public Load(LaunchModule launchModule, double loadTime) {
            this.launchModule = launchModule;
            this.loadTime = loadTime;

            this.time = new ElapsedTime();
        }

        @Override
        public void start(Sequence.Callback callback) {
            if(launchModule.locked == true){
                callback.err(new DeviceLockedException(this));
            } else {
                launchModule.locked = true;
                launchModule.setLoadPower(1);

                initialTime = time.milliseconds();
            }
        }

        @Override
        public void loop(Sequence.Callback callback) {
            if(time.milliseconds() > initialTime + loadTime){
                stop(callback);
            }
        }

        @Override
        public void stop(Sequence.Callback callback) {
            launchModule.setLoadPower(0);
            launchModule.locked = false;
            callback.next();
        }
    }

    private class LoadDecel implements Operation {
        private LaunchModule launchModule;
        private ElapsedTime time;

        private double targetTime;

        public LoadDecel(LaunchModule launchModule) {
            this.launchModule = launchModule;
            this.time = new ElapsedTime();
        }

        @Override
        public void start(Sequence.Callback callback) {
            if(launchModule.locked) {
                callback.err(new DeviceLockedException(this));
            } else {
//                launchModule.locked = true;

                targetTime = time.milliseconds() + 3000 * Math.abs(launchModule.getLoadPower());
            }
        }

        @Override
        public void loop(Sequence.Callback callback) {
            if(time.milliseconds() > targetTime) {
                stop(callback);
            } else {
                launchModule.setLoadPower(Math.signum(launchModule.getLoadPower()) * (targetTime - time.milliseconds()) / 3000.0);
            }
        }

        @Override
        public void stop(Sequence.Callback callback) {
//            launchModule.locked = false;
            launchModule.setLoadPower(0);
            callback.next();
        }
    }

    private class LaunchRev implements Operation {
        private LaunchModule launchModule;
        private ElapsedTime time;

        private double initialTime;
        private double power;

        public LaunchRev(LaunchModule launchModule, double power) {
            this.launchModule = launchModule;
            this.power = power;
            this.time = new ElapsedTime();
        }

        @Override
        public void start(Sequence.Callback callback) {
            if(launchModule.locked) {
                callback.err(new DeviceLockedException(this));
            } else {
                launchModule.locked = true;

                initialTime = time.milliseconds();
            }
        }

        @Override
        public void loop(Sequence.Callback callback) {
            double dtime = time.milliseconds() - initialTime;
            if(dtime / 3000.0 > power) {
                launchModule.setWheelPower(power);
                launchModule.locked = false;
                callback.next();
            } else {
                launchModule.setWheelPower(dtime / 3000.0);
            }
        }

        @Override
        public void stop(Sequence.Callback callback) {
            launchModule.locked = false;
            launchModule.setWheelPower(0);
            launchModule.setFeedPower(0);
            callback.next();
        }
    }

    private class LaunchDecel implements Operation {
        private LaunchModule launchModule;
        private ElapsedTime time;

        private double targetTime;

        public LaunchDecel(LaunchModule launchModule) {
            this.launchModule = launchModule;
            this.time = new ElapsedTime();
        }

        @Override
        public void start(Sequence.Callback callback) {
            if(launchModule.locked) {
                callback.err(new DeviceLockedException(this));
            } else {
                targetTime = time.milliseconds() + 3000 * launchModule.getLaunchPower();
            }
        }

        @Override
        public void loop(Sequence.Callback callback) {
            if(time.milliseconds() > targetTime) {
                stop(callback);
            } else {
                launchModule.setWheelPower((targetTime - time.milliseconds()) / 3000.0);
            }
        }

        @Override
        public void stop(Sequence.Callback callback) {
            launchModule.setWheelPower(0);
            callback.next();
        }
    }

    private class Launch implements Operation {
        private LaunchModule launchModule;
        private ElapsedTime time;

        private Sequencer sequencer;

        private Sequence sequence;

        private double startTime;

        public Launch(LaunchModule launchModule, Sequencer sequencer) {
            this.launchModule = launchModule;
            this.time = new ElapsedTime();

            this.sequencer = sequencer;
        }

        @Override
        public void start(Sequence.Callback callback) {
            if(launchModule.locked) {
                callback.err(new DeviceLockedException(this));
            } else {
                startTime = time.milliseconds();
                sequence = sequencer
                    .begin(launchModule.launchRev(1))
                    .then(new WaitModule(1000))
                    .then(new SimpleOperation() {
                        @Override
                        public void start(Sequence.Callback callback) {
                            launchModule.setFeedPower(0.3);
                        }
                    })
                    .then(new WaitModule(2000))
                    .then(new SimpleOperation() {
                        @Override
                        public void start(Sequence.Callback callback) {
                            launchModule.setFeedPower(0);
                        }
                    })
                    .then(launchModule.launchDecel());
            }
        }

        @Override
        public void loop(Sequence.Callback callback) {
            if(sequence.isFinished()) {
                callback.next();
            }
        }

        @Override
        public void stop(Sequence.Callback callback) {
            sequence.terminate();
            callback.next();
        }
    }
}
