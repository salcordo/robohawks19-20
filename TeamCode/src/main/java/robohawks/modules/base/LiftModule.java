package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import robohawks.async.Operation;
import robohawks.async.Sequence;
import robohawks.async.error.DeviceLockedException;
import robohawks.utils.MathX;

/**
 * Created by paarth on 2/2/17.
 */
public class LiftModule {
    DcMotor lift;

    private boolean locked;

    public LiftModule(HardwareMap hardwareMap){
        lift = hardwareMap.dcMotor.get("arm");
    }

    public void setPower(double power){
        lift.setPower(power);
    }

    public Operation lift(){
        return new Lift(this);
    }

    public class Lift implements Operation{
        private LiftModule liftModule;
        private ElapsedTime elapsedTime;

        private double initialTime;

        public Lift(LiftModule liftModule){
            this.liftModule = liftModule;
            this.elapsedTime = new ElapsedTime();
        }

        @Override
        public void start(Sequence.Callback callback) {
            if(liftModule.locked){
                callback.err(new DeviceLockedException(this));
            } else {
                liftModule.locked = true;
                liftModule.setPower(.5);

                initialTime = elapsedTime.milliseconds();
            }
        }

        @Override
        public void loop(Sequence.Callback callback) {
            double dtime = elapsedTime.milliseconds();
            if (dtime > 8000){
                stop(callback);
            }else if(dtime > 4000){
                double pow = 1 - MathX.expScale((dtime - 4000) / 4000, .4);
                liftModule.setPower(pow);
            }
        }

        @Override
        public void stop(Sequence.Callback callback) {
            liftModule.locked = false;
            liftModule.setPower(0);
            callback.next();
        }
    }
}
