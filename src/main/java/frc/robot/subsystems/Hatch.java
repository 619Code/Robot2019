package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.maps.RobotMap;

public class Hatch{
    private DoubleSolenoid grabber, extendo;

    public Hatch(){
        grabber = new DoubleSolenoid(RobotMap.HATCH_GRABBER_CHANNEL[0], 
                                        RobotMap.HATCH_GRABBER_CHANNEL[1]);
                                        
        extendo = new DoubleSolenoid(RobotMap.HATCH_EXTEND_CHANNEL[0], 
                                        RobotMap.HATCH_EXTEND_CHANNEL[1]);
    }

    public void extendo(Value val)
    {
        extendo.set(val);
    }

    public void close(Value val)
    { 
        grabber.set(val);
    }
    
    public void extendOrClose(double speed){
        if(speed > 0.5)
            extendo(Value.kForward);
        else if(speed < -0.5)
            close(Value.kReverse);
    }
}