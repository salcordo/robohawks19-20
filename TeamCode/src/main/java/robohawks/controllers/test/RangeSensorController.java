package robohawks.controllers.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import robohawks.async.Sequence;
import robohawks.controllers.old.Controller;
import robohawks.modules.base.RangeModule;

/**
 * Created by paarth on 1/28/17.
 */
@Autonomous(name = "RangeSensorController", group = "Test")
public class RangeSensorController extends Controller {

    Sequence mainSequence;
    RangeModule rangeModule;

    @Override
    public void init() {
        rangeModule = new RangeModule(hardwareMap);
    }

    @Override
    public void loop() {
        super.loop();

        double data = rangeModule.getDistance();
        telemetry.addData("Range: ", Double.toString(data));
    }

}
