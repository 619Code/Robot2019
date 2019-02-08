package frc.robot.subsystems;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.maps.RobotMap;

public class Hatch{
    private Solenoid grabber;
    private Solenoid extendo;

    public Hatch(){
        extendo = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.HATCH_EXTEND_CHANNEL);
        grabber = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.HATCH_GRABBER_CHANNEL);
    }

    public void extend(boolean dir)
    {
        extendo.set(dir);
    }

    public void grab(boolean dir)
    {
        grabber.set(dir);
    }
    
    public void grabState(double speed){
        if(speed > 0.5)
            grab(true);
        else if(speed < -0.5)
            grab(false);
    }

    public void extendState(double speed){
        if(speed > 0.5)
            extend(true);
        else if(speed < -0.5)
            extend(false);
    }
}