package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Hatch{
    private DoubleSolenoid grabber, extendo;

    public Hatch(){
        grabber = new DoubleSolenoid(RobotMap.HATCH_GRABBER_CHANNEL[0], 
                                        RobotMap.HATCH_GRABBER_CHANNEL[1]);
                                        
        extendo = new DoubleSolenoid(RobotMap.HATCH_EXTEND_CHANNEL[0], 
                                        RobotMap.HATCH_EXTEND_CHANNEL[1]);
    }

    public void extendo(DoubleSolenoid.Value val)
    {
        extendo.set(val);
    }

    public void grab(DoubleSolenoid.Value val)
    { 
        grabber.set(val);
    }
}