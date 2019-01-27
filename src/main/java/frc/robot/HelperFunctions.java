package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HelperFunctions {
    public static void configureTalon(WPI_TalonSRX talon, RobotMap.Subsystem type) {
        talon.configFactoryDefault();
        switch (type) {
        case LIFT:
            break;
        case ARM:
            break;
        case INTAKE:
            talon.configFactoryDefault();
            talon.setNeutralMode(NeutralMode.Brake);
            break;
        }
    }
}