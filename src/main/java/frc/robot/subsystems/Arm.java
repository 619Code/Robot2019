package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.maps.ControllerMap;
import frc.robot.maps.RobotMap;

public class Arm extends Subsystem{

    private CANSparkMax flexin;

    private CANPIDController flexController;

    private CANEncoder flexEncoder;

    public Arm() {
        flexin = new CANSparkMax(RobotMap.ARM, MotorType.kBrushless);
        flexin.setIdleMode(IdleMode.kBrake);
        flexController = flexin.getPIDController();
        flexEncoder = flexin.getEncoder();

        HelperFunctions.configurePIDController(flexController, RobotMap.Manipulators.ARM);
    }

    public void moveToTarget() {
        RobotMap.ARM_TARGETS target = ControllerMap.Arm.goToPosition();
        if (target.equals(RobotMap.ARM_TARGETS.NULL_POSITION))
            return;
        double targetPos = (RobotMap.TICKSPERROT_NEO_ENC * RobotMap.RATIO_ARM) * target.getValue();
        flexController.setReference(targetPos, ControlType.kPosition);
    }

    public void move() {
        flexin.set(ControllerMap.Arm.move());
    }

    public void move(double speed) {
        flexin.set(speed);
    }

    @Override
    protected void initDefaultCommand() {}
}