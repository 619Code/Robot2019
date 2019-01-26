package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake{
    WPI_TalonSRX leftSpinny, rightSpinny;

    public Intake(){
        leftSpinny = new WPI_TalonSRX(RobotMap.LEFT_INTAKE);
        rightSpinny = new WPI_TalonSRX(RobotMap.RIGHT_INTAKE);
    }
}