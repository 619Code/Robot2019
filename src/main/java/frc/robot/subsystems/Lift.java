package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Lift{
    private WPI_TalonSRX upboi1, upboi2;
    public Lift(){
        upboi1 = new WPI_TalonSRX(RobotMap.INTAKE1);
        upboi2 = new WPI_TalonSRX(RobotMap.INTAKE2);
        HelperFunctions.configureTalon(upboi1, RobotMap.Subsystem.LIFT);
        HelperFunctions.configureTalon(upboi2, RobotMap.Subsystem.LIFT);
        upboi2.follow(upboi1);
        
    }

    //target is in rotations (e.g. target=1 is one rotation)
    public void moveLift(double target){
			double targetPos =  1024 * target;
            upboi1.set(ControlMode.MotionMagic, targetPos);
    }
    

}