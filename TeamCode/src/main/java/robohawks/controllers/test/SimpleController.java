package robohawks.controllers.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import robohawks.async.Sequence;
import robohawks.controllers.old.Controller;
import robohawks.modules.base.DriveModule;

/**
 * Created by fchoi on 9/26/2016.
 */
@Autonomous(name="Simple", group ="Test")
public class SimpleController extends Controller {
    Sequence mainSequence;

    @Override
    public void init() {
        DriveModule driveModule = new DriveModule(hardwareMap);
        mainSequence = sequencer.begin(driveModule.drive(2, 1, 1)).then(driveModule.drive(2, 1, .5));
    }

    @Override
    public void loop() {
        super.loop();

        if(mainSequence.isFinished()) requestOpModeStop();
    }
}
