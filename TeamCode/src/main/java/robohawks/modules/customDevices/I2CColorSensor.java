package robohawks.modules.customDevices;

import com.qualcomm.robotcore.hardware.*;

/**
 * Created by paarth on 2/2/17.
 */
public class I2CColorSensor implements ColorSensor{
    private I2cDevice device;

    private HardwareMap hardwareMap;
    private String deviceName;
    private I2cAddr addr;

    private byte[] readCache;
    private I2cDeviceSynchImpl io;

    public I2CColorSensor(HardwareMap hardwareMap, String deviceName, I2cAddr addr) {
        this.hardwareMap = hardwareMap;
        this.deviceName =deviceName;
        this.addr =addr;

        this.device = hardwareMap.i2cDevice.get(deviceName);
        this.io = new I2cDeviceSynchImpl(device, addr, false);
        io.engage();
    }

    @Override
    public int red() {
        return io.read8(0x05);
    }

    @Override
    public int green() {
        return io.read8(0x06);
    }

    @Override
    public int blue() {
        return io.read8(0x07);
    }

    @Override
    public int alpha() {
        return io.read8(0x08);
    }

    @Override
    public int argb() {
        int argb = this.alpha();
        argb = argb << 8 + this.red();
        argb = argb << 8 + this.green();
        argb = argb << 8 + this.blue();
        return argb;
    }

    @Override
    public void enableLed(boolean enable) {
        io.write8(0x03, enable ? 0x00 : 0x01);
    }

    @Override
    public void setI2cAddress(I2cAddr newAddress) {
        this.addr = newAddress;
    }

    @Override
    public I2cAddr getI2cAddress() {
        return addr;
    }

    @Override
    public Manufacturer getManufacturer() {
        return null;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public String getConnectionInfo() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {

    }

    @Override
    public void close() {
        io.disengage();
    }
}
