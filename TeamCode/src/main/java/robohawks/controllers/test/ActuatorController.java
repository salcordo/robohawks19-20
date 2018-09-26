package robohawks.controllers.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import robohawks.async.Sequence;
import robohawks.controllers.old.Controller;
import robohawks.modules.base.ActuatorModule;
import robohawks.modules.base.ColorModule;
import robohawks.modules.base.WaitModule;

/**
 * Created by paarth on 1/23/17.
 */
@Autonomous(name="Actuator Controller", group="Test")
public class ActuatorController extends Controller {
    Sequence mainSequence;

    @Override
    public void init() {
        ActuatorModule actuatorModule = new ActuatorModule(hardwareMap);
        ColorModule colorModule = new ColorModule(hardwareMap);

        actuatorModule.initialize();
        colorModule.initialize();

        Sequence leftSequence = sequencer
            .create(actuatorModule.setActuatorLeftOp(true))
            .then(new WaitModule(5500))
            .then(actuatorModule.setActuatorLeftOp(false));
        Sequence rightSequence = sequencer
            .create(actuatorModule.setActuatorRightOp(true))
            .then(new WaitModule(5500))
            .then(actuatorModule.setActuatorRightOp(false));
        mainSequence = sequencer
            .begin(colorModule.isRednotBlueOp())
            .thenStartIf(leftSequence, rightSequence);
    }

    @Override
    public void loop() {
        super.loop();
    }
}
