package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.I2cAddr;
import robohawks.async.Operation;
import robohawks.async.Sequence;
import robohawks.async.SimpleOperation;
import robohawks.modules.customDevices.I2CColorSensor;
import robohawks.utils.Color;

/**
 * Attempt to make a module myself --Paarth Tandon
 */
public class ColorModule {
    private ColorSensor buttonSensor;
    private ColorSensor lineSensorLeft;
    private ColorSensor lineSensorRight;

    public ColorModule(HardwareMap hardwareMap){
        buttonSensor = new I2CColorSensor(hardwareMap, "buttonSensor", I2cAddr.create8bit(0x40));

        lineSensorLeft = new I2CColorSensor(hardwareMap, "lineSensorLeft", I2cAddr.create8bit(0x3e));

        lineSensorRight = new I2CColorSensor(hardwareMap, "lineSensorRight", I2cAddr.create8bit(0x3c));
    }

    public void initialize() {
        buttonSensor.enableLed(false);
        lineSensorLeft.enableLed(true);
        lineSensorRight.enableLed(true);
    }

    public Color getButtonColor() {
        return new Color(buttonSensor.alpha(), buttonSensor.red(), buttonSensor.green(), buttonSensor.blue());
    }

    public int getColorArgb() {
        return buttonSensor.argb();
    }

    public Color getLeftColor() {
        return new Color(lineSensorLeft.alpha(), lineSensorLeft.red(), lineSensorLeft.green(), lineSensorLeft.blue());
    }

    public Color getRightColor() {
        return new Color(lineSensorRight.alpha(), lineSensorRight.red(), lineSensorRight.green(), lineSensorRight.blue());
    }

    public void setLight(boolean lightOn) {
        buttonSensor.enableLed(lightOn);
    }

    public boolean isRednotBlue() {
        Color color = getButtonColor();
        return color.r > color.b;
    }

    /**
     * Creates an operation that pushes the color (as a boolean) to the sequence
     * @return the operation
     */
    public Operation isRednotBlueOp() {
        return new SimpleOperation() {
            @Override
            public void start(Sequence.Callback callback) {
                callback.next(isRednotBlue());
            }
        };
    }

    public Operation isRednotBlueOp(final boolean flip) {
        return new SimpleOperation() {
            @Override
            public void start(Sequence.Callback callback) {
                callback.next(isRednotBlue() ^ flip);
            }
        };
    }

    public boolean isLeftWhitenotBlack() {
        return getLeftColor().a > 10;
    }

    public boolean isRightWhitenotBlack() {
        return getRightColor().a > 10;
    }
}
