package robohawks.modules.base;

import com.qualcomm.robotcore.hardware.*;

/**
 * Created by paarth on 1/28/17.
 */
public class RangeModule {

    private byte[] rangeCache;
    private I2cAddr address = new I2cAddr(0x14);
    static final int REG_START = 0x04;
    static final int READ_LENGTH = 2;

    private I2cDevice ultrasonicSensor;
    private I2cDeviceSynch reader;

    public RangeModule(HardwareMap hardwareMap){
        this.ultrasonicSensor = hardwareMap.i2cDevice.get("ultrasonic");
        this.reader = new I2cDeviceSynchImpl(ultrasonicSensor, address, false);
        reader.engage();
    }

    public double getDistance(){
        rangeCache = reader.read(REG_START, READ_LENGTH);
        return (rangeCache[0] & 0xFF) / 255.0;
    }
}
