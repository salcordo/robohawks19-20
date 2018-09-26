package robohawks.controllers.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import robohawks.controllers.old.Controller;
import robohawks.modules.base.ColorModule;

/**
 * Created by paarth on 10/19/16.
 */

@Autonomous(name="color", group="Test")
public class ColorSensorController extends Controller {
    ColorModule colorModule;

    @Override
    public void init() {
        this.colorModule = new ColorModule(hardwareMap);
        colorModule.initialize();
    }

    @Override
    public void loop() {
        super.loop();

        telemetry.addData("button", colorModule.getButtonColor());
        telemetry.addData("left", colorModule.getLeftColor());
        telemetry.addData("right", colorModule.getRightColor());
    }

}
