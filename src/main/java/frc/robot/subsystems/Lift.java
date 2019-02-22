package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.maps.ControllerMap;
import frc.robot.maps.RobotMap;

public class Lift extends Subsystem{
    private TalonSRX upboiLeft, upboiRight;
    public Lift(){
        upboiLeft = new TalonSRX(RobotMap.LEFT_LIFT);
        upboiRight = new TalonSRX(RobotMap.RIGHT_LIFT);
        HelperFunctions.configureTalon(upboiLeft, RobotMap.Manipulators.LIFT);
        HelperFunctions.configureTalon(upboiRight, RobotMap.Manipulators.LIFT);

        upboiRight.setInverted(true);
        upboiLeft.follow(upboiRight);
    }

    //target is in rotations (e.g. target=1 is one rotation)
    public void moveToTarget(){
        RobotMap.LIFT_TARGETS target = ControllerMap.LiftControl.goToPosition();
		double targetPos =  (RobotMap.TICKSPERROT_VERSAPLANETARY_ENC*RobotMap.RATIO_LIFT) * target.getValue();
        upboiRight.set(ControlMode.MotionMagic, targetPos);
    }
    
    public void move(){
        upboiRight.set(ControlMode.PercentOutput, ControllerMap.LiftControl.move());
    }

    public void move(double speed){
        upboiRight.set(ControlMode.PercentOutput, speed);
    }

    @Override
    protected void initDefaultCommand() {}
}