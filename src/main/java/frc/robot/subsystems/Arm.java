package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;

public class Arm{
    private final boolean up = true;

    private CANSparkMax flexin;

    private CANPIDController flex_controler;

    private CANEncoder flexEncoder;

    public Arm(){
        flexin = new CANSparkMax(RobotMap.ARM, MotorType.kBrushless);
        flex_controler = flexin.getPIDController();
        flexEncoder = flexin.getEncoder();

        flex_controler.setP(RobotMap.ARM_kP);
        flex_controler.setI(RobotMap.ARM_Ki);
        flex_controler.setD(RobotMap.ARM_Kd);
        flex_controler.setIZone(RobotMap.ARM_kIZONE);
        flex_controler.setFF(RobotMap.ARM_KFF);
        flex_controler.setOutputRange(RobotMap.ARM_MINOUTPUT, RobotMap.ARM_MAXOUTPUT);
    }
    public void moveArm(double rotations){
        flex_controler.setReference(rotations, ControlType.kPosition);
    }
    
}