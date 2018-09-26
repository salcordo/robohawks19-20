package robohawks.modules.base;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TurnModule {
    private DcMotor motor;

    public TurnModule(HardwareMap hardwareMap){
        motor = hardwareMap.dcMotor.get("turn");
    }

    public void setTurn(double power) {
        motor.setPower(power);
    }
}
