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
    public static CANEncoder flexEncoder;

    public Arm() {
        flexin = new CANSparkMax(RobotMap.ARM, MotorType.kBrushless);
        flexin.setIdleMode(IdleMode.kBrake);
        flexController = flexin.getPIDController();
        flexEncoder = flexin.getEncoder();

        HelperFunctions.configurePIDController(flexController, RobotMap.Manipulators.ARM);
        flexController.setP(RobotMap.ARM_kP);
        flexController.setI(RobotMap.ARM_kI);
        flexController.setD(RobotMap.ARM_kD);
        flexController.setFF(RobotMap.ARM_kF);
        flexController.setIZone(RobotMap.ARM_kIZONE);
        flexController.setOutputRange(RobotMap.ARM_MINOUTPUT, RobotMap.ARM_MAXOUTPUT);
    }

    /**
     * INTAKE BALL ENC = -8.5
     * LOWER GOAL ENC = -14.7
     * MIDDLE GOAL ENC = -57
     * HIGH GOAL ENC = -116
     * CARGO SHIP ENC = -40
     */
    public void moveToTarget() {
        RobotMap.ARM_TARGETS target = ControllerMap.ArmControl.goToPosition();
        if (target.equals(RobotMap.ARM_TARGETS.NULL_POSITION)){
            flexController.setReference(flexEncoder.getPosition(), ControlType.kPosition);
            return;
        }
        //System.out.println("TARGET POS: " + target.getValue() + "CURRENT POS: " + flexEncoder.getPosition());
        //double targetPos = (RobotMap.TICKSPERROT_NEO_ENC * RobotMap.RATIO_ARM) * target.getValue();
        flexController.setReference(target.getValue(), ControlType.kPosition);
        //flexController.setReference(target.getValue(), ControlType.kPosition);
    }

    public void move() {
        flexin.set(ControllerMap.ArmControl.move());
        //System.out.println("ENCODER POS: " + flexEncoder.getPosition());
    }

    public void move(double speed) {
        flexin.set(speed);
    }

    @Override
    protected void initDefaultCommand() {}
}