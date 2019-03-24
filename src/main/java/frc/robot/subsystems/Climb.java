package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.maps.RobotMap;
import frc.robot.maps.ControllerMap.ClimbControl;

public class Climb extends Subsystem {
    private Solenoid boost;
    private boolean state;

    public Climb(){
        boost = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.BOOST_CHANNEL);
    }

    public void climb() {
        state = ClimbControl.climb();
        boost.set(state);
    }

    @Override
    protected void initDefaultCommand() {}
}