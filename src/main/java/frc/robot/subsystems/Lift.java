package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.maps.RobotMap;

public class Lift{
    private WPI_TalonSRX upboiLeft, upboiRight;
    public Lift(){
        upboiLeft = new WPI_TalonSRX(RobotMap.LEFT_LIFT);
        upboiRight = new WPI_TalonSRX(RobotMap.RIGHT_LIFT);
        HelperFunctions.configureTalon(upboiLeft, RobotMap.Subsystem.LIFT);
        //HelperFunctions.configureTalon(upboiRight, RobotMap.Subsystem.LIFT);
        upboiRight.follow(upboiLeft);
    }

    //target is in rotations (e.g. target=1 is one rotation)
    public void moveLiftToTarget(RobotMap.LIFT_TARGETS target){
			double targetPos =  (RobotMap.TICKSPERROT_VERSAPLANETARY_ENC*RobotMap.RATIO_LIFT) * target.getValue();
            upboiLeft.set(ControlMode.MotionMagic, targetPos);
    }
    
    public void moveLift(double speed){
        upboiLeft.set(ControlMode.PercentOutput, speed);
        upboiRight.set(ControlMode.PercentOutput, speed);
    }

}