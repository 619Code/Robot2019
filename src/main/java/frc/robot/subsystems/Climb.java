package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.maps.RobotMap;
import frc.robot.maps.RobotMap.Manipulators;
import frc.robot.hardware.LimitSwitch;

public class Climb extends Subsystem{
    //ratio is 1:16
    WPI_TalonSRX _left, _right;
    LimitSwitch _leftMiddle,_leftEnd, _rightEnd, _rightMiddle;

    public Climb(){
        _left = new WPI_TalonSRX(RobotMap.LEFT_CLIMB);
        _right = new WPI_TalonSRX(RobotMap.RIGHT_CLIMB);
        _leftMiddle = new LimitSwitch(RobotMap.LEFT_MIDDLE_CLIMB_SWITCH);
        _leftEnd = new LimitSwitch(RobotMap.LEFT_END_CLIMB_SWITCH);
        _rightMiddle = new LimitSwitch(RobotMap.RIGHT_MIDDLE_CLIMB_SWITCH);
        _rightEnd = new LimitSwitch(RobotMap.RIGHT_END_CLIMB_SWITCH);
        HelperFunctions.configureTalon(_left, Manipulators.CLIMB);
        HelperFunctions.configureTalon(_right, Manipulators.CLIMB);
    }
    public void autoClimb(){
        //go up
        //once the end limit switches are hit zoop
        //once pneumatic is done, move up front
        //once front hits mid limit switch, drive forward a certain amount of rotations
        //once those rotations are achieved, move the back up
        //once the middle back limit switch is hit, drive forward
        //once the wheels move forwar a certain amount of rotions, zoop the climber in the inital position
    }

    public void up(){

    }

    public void zoop(){

    }
    public void upFront(){

    }
    public void driveFoward(){

    }
    public void upBack(){

    }
    public void unZoop(){

    }

    @Override
    protected void initDefaultCommand() {
 
    }
}

