package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import frc.robot.maps.RobotMap;

public class Arm{

    private CANSparkMax flexin;

    private CANPIDController flexController;

    private CANEncoder flexEncoder;

    public Arm(){
        flexin = new CANSparkMax(RobotMap.ARM, MotorType.kBrushless);
        flexController = flexin.getPIDController();
        flexEncoder = flexin.getEncoder();

        HelperFunctions.configurePIDController(flexController, RobotMap.Manipulators.ARM);
    }

    public void moveArmToTarget(RobotMap.ARM_TARGETS target){
        if (target.equals(RobotMap.ARM_TARGETS.NULL_POSITION)) return;
        double targetPos =  (RobotMap.TICKSPERROT_NEO_ENC*RobotMap.RATIO_ARM) * target.getValue();
        flexController.setReference(targetPos, ControlType.kPosition);    
    }

    public void moveArm(double speed){
       // System.out.println(speed);
        flexin.set(speed);
    }
    
}