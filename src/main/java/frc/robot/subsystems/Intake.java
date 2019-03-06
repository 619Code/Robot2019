package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.maps.ControllerMap;
import frc.robot.maps.RobotMap;

public class Intake extends Subsystem{
    private TalonSRX spinny;
    private DoubleSolenoid wrist;

    public Intake() {
        spinny = new TalonSRX(RobotMap.INTAKE);
        HelperFunctions.configureTalon(spinny, RobotMap.Manipulators.INTAKE);
        wrist = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_WRIST_CHANNEL[0], RobotMap.INTAKE_WRIST_CHANNEL[1]);
    }

    public void spin(){
	    double speed = ControllerMap.IntakeControl.spin();
        spinny.set(ControlMode.PercentOutput, speed);
    }

    public void spin(double speed){
        spinny.set(ControlMode.PercentOutput, speed);
    }

    public void raiseOrLower(){
        int dir = ControllerMap.IntakeControl.raiseOrLower(); 
        if(dir==0)
            raise();
        else if(dir == 180)
            lower();
        else if(dir == -1)
            stop();
    }

    private void raise() {
        wrist.set(Value.kReverse);
    }

    private void lower() {
        wrist.set(Value.kForward);
    }

    private void stop() {
        wrist.set(Value.kOff);
    }

    @Override
    protected void initDefaultCommand() {}
}
