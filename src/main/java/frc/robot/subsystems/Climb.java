package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.hardware.LimitSwitch;
import frc.robot.maps.RobotMap;
import frc.robot.maps.ControllerMap.ClimbControl;

public class Climb extends Subsystem {
    private Solenoid boost;
    private boolean state;
    //private LimitSwitch boostDoneSwitch = new LimitSwitch(RobotMap.CLIMB_SWITCH);
    private boolean startClimb = false;

    public Climb(){
        boost = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.BOOST_CHANNEL);
    }

    public void climb() {
        state = ClimbControl.climb();
        //System.out.println(state);
        boost.set(state);
        // if(state) startClimb = true;
        // if(startClimb && boostDoneSwitch.get()){
        //     Robot.sunKist.setLeftandRight(0.7, 0.7);
        //     Timer.delay(3);
        //     Robot.sunKist.setLeftandRight(0, 0);
        // } 
    }

    @Override
    protected void initDefaultCommand() {}
}