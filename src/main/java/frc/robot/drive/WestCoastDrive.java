package frc.robot.drive;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.hardware.Controller;
import frc.robot.maps.RobotMap;
import frc.robot.subsystems.HelperFunctions;

public class WestCoastDrive extends Subsystem{
    CANSparkMax leftMaster, leftFront, leftRear, rightMaster, rightFront, rightRear;
    DifferentialDrive drive;
    CANPIDController leftPID, rightPID;

    public enum Mode {
        CURVATURE, ARCADE, TANK, GTA;
    }

    public WestCoastDrive() {
        initDrive();
    }

    public void initDrive() {
        leftMaster = new CANSparkMax(RobotMap.LEFT_MIDDLE, MotorType.kBrushless);
        leftFront = new CANSparkMax(RobotMap.LEFT_FRONT, MotorType.kBrushless);
        leftRear = new CANSparkMax(RobotMap.LEFT_REAR, MotorType.kBrushless);
        rightMaster = new CANSparkMax(RobotMap.RIGHT_MIDDLE, MotorType.kBrushless);
        rightFront = new CANSparkMax(RobotMap.RIGHT_FRONT, MotorType.kBrushless);
        rightRear = new CANSparkMax(RobotMap.RIGHT_REAR, MotorType.kBrushless);
        leftMaster.setIdleMode(IdleMode.kCoast);
        rightMaster.setIdleMode(IdleMode.kCoast);
        leftRear.setIdleMode(IdleMode.kCoast);
        leftFront.setIdleMode(IdleMode.kCoast);
        rightRear.setIdleMode(IdleMode.kCoast);
        rightFront.setIdleMode(IdleMode.kCoast);
        leftMaster.setRampRate(RobotMap.RAMP_RATE);
        rightMaster.setRampRate(RobotMap.RAMP_RATE);
        leftFront.follow(leftMaster);
        leftRear.follow(leftMaster);
        rightFront.follow(rightMaster);
        rightRear.follow(rightMaster);

        leftPID = leftMaster.getPIDController();

        leftPID.setP(RobotMap.DRIVE_kP);
        leftPID.setI(RobotMap.DRIVE_kI);
        leftPID.setD(RobotMap.DRIVE_kD);
        leftPID.setIZone(RobotMap.DRIVE_kIZONE);
        leftPID.setFF(RobotMap.DRIVE_KFF);
        leftPID.setOutputRange(RobotMap.DRIVE_MINOUTPUT, RobotMap.DRIVE_MAXOUTPUT);

        rightPID = leftMaster.getPIDController();

        rightPID.setP(RobotMap.DRIVE_kP);
        rightPID.setI(RobotMap.DRIVE_kI);
        rightPID.setD(RobotMap.DRIVE_kD);
        rightPID.setIZone(RobotMap.DRIVE_kIZONE);
        rightPID.setFF(RobotMap.DRIVE_KFF);
        rightPID.setOutputRange(RobotMap.DRIVE_MINOUTPUT, RobotMap.DRIVE_MAXOUTPUT);
        
        drive = new DifferentialDrive(leftMaster, rightMaster);
        drive.setMaxOutput(RobotMap.DRIVE_OUTPUT_MAX);
    }

    public void drive(Mode mode, Controller driver) {
        double speed = -getSpeed(mode, driver);
        double rotation = getRotation(mode, driver);
        switch (mode) {
        case CURVATURE:
            curveDrive(speed, rotation);
            break;
        case ARCADE:
            arcadeDrive(speed, rotation);
            break;
        case TANK:
            tankDrive(speed, rotation);
            break;
        case GTA:
            curveDrive(speed, rotation);
            break;
        }
    }

    public void setLeftMotors(double speed) {
        leftMaster.set(speed);
    }

    public void setRightMotors(double speed) {
        rightMaster.set(speed);
    }

    public void setLeftandRight(double left, double right){
        leftMaster.set(left);
        rightMaster.set(right);
    }

    public double  getLeftEncoderValue() {
        return (int)leftRear.getEncoder().getPosition();
    }

    public double getRightEncoderValue() {
        return (int)rightMaster.getEncoder().getPosition();
    }
    
    public double getLeftEncoderInches(){
        return RobotMap.WHEEL_DIAMETER*Math.PI*(getLeftEncoderValue()/RobotMap.ENCODER_TICK_PER_REV);
    }

    public double getRightEncoderInches(){
        return RobotMap.WHEEL_DIAMETER*Math.PI*(getRightEncoderValue()/RobotMap.ENCODER_TICK_PER_REV);
    }

    public double getSpeed(Mode mode, Controller driver) {
        switch (mode) {
        case CURVATURE:
        case ARCADE:
        case TANK:
            return HelperFunctions.deadzone(driver.getY(RobotMap.SPEED_HAND));
        case GTA:
            return HelperFunctions.deadzone(driver.getTriggerAxis(RobotMap.RIGHT_HAND) - driver.getTriggerAxis(RobotMap.LEFT_HAND));
        default:
            // curvature drive default
            return HelperFunctions.deadzone(driver.getY(RobotMap.SPEED_HAND));
        }
    }

    public double getRotation(Mode mode, Controller driver) {
        switch (mode) {
        case CURVATURE:
        return HelperFunctions.deadzone(driver.getX(RobotMap.ROT_HAND));
        case ARCADE:
        case TANK:
            return HelperFunctions.deadzone(driver.getY(RobotMap.ROT_HAND));
        case GTA:
            return HelperFunctions.deadzone(driver.getX(RobotMap.ROT_HAND));
        default:
            // curvature drive default
            return HelperFunctions.deadzone(driver.getX(RobotMap.ROT_HAND));
        }
    }

    public void curveDrive(double speed, double rotation) {
        drive.curvatureDrive(speed * RobotMap.DRIVE_SPEED_MAX, -rotation * RobotMap.DRIVE_ROT_MAX, true);
    }

    public void arcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(speed * RobotMap.DRIVE_SPEED_MAX, -rotation * RobotMap.DRIVE_ROT_MAX, true);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed * RobotMap.DRIVE_SPEED_MAX, -rightSpeed * RobotMap.DRIVE_ROT_MAX, true);
    }
    public void moveDriveToTarget(double rotaitons){
        double targetPos =  (RobotMap.TICKSPERROT_NEO_ENC*RobotMap.RATIO_DRIVE*rotaitons);
        leftPID.setReference(targetPos, ControlType.kPosition);
        rightPID.setReference(targetPos, ControlType.kPosition);    
    }

    @Override
    protected void initDefaultCommand() {}
}