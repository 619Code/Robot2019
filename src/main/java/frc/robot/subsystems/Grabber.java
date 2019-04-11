package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.maps.ControllerMap;
import frc.robot.maps.RobotMap;
import frc.robot.maps.RobotMap.Manipulators;

public class Grabber extends Subsystem{
    //ratio is 1:20
    TalonSRX _left, _right;

    public Grabber() {
        _left = new TalonSRX(RobotMap.LEFT_GRABBER);
        _right = new TalonSRX(RobotMap.RIGHT_GRABBER);
        HelperFunctions.configureTalon(_left, Manipulators.GRABBER);
        HelperFunctions.configureTalon(_right, Manipulators.GRABBER);

        _right.setInverted(true);
        _left.follow(_right);
    }

    public void grab(){
        double speed = ControllerMap.GrabberControl.grab();
        if(speed == 0)
            _right.set(ControlMode.PercentOutput, RobotMap.GRABBER_COUNTER);
        else
            _right.set(ControlMode.PercentOutput, speed);
    }

    public void grab(double speed) {
        _right.set(ControlMode.PercentOutput, speed + RobotMap.GRABBER_COUNTER);
    }

    @Override
    protected void initDefaultCommand() {}
}

