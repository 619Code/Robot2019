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
    private Solenoid front;
    private Solenoid back;
    private boolean[] state;

    public Climb(){
        front = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.FRONT_CHANNEL);
        back = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.BACK_CHANNEL);
    }

    public void climb() {
        state = ClimbControl.climb();
        front.set(state[0]);
        back.set(state[1]);
    }

    @Override
    protected void initDefaultCommand() {}
}