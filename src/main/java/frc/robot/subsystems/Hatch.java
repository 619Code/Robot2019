package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.maps.RobotMap;

public class Hatch extends Subsystem{
    private Solenoid grabber;
    private DoubleSolenoid extendo;

    public Hatch(){
        extendo = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.HATCH_EXTEND_CHANNEL[0], RobotMap.HATCH_EXTEND_CHANNEL[1]);
        grabber = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.HATCH_GRABBER_CHANNEL);
    }

    public void extend(Value dir)
    {
        extendo.set(dir);
    }

    public void grab(boolean dir)
    {
        grabber.set(dir);
    }

    @Override
    protected void initDefaultCommand() {}
}