package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.maps.RobotMap;

public class Grabber{
    //ratio is 1:20
    WPI_TalonSRX _left, _right;

    public Grabber(){
        _left = new WPI_TalonSRX(RobotMap.LEFT_GRABBER);
        _right = new WPI_TalonSRX(RobotMap.RIGHT_GRABBER);
        _right.setInverted(true);
        _right.follow(_left);
    }

    public void grab(double speed){
        _left.set(ControlMode.PercentOutput, speed);
    }
}

