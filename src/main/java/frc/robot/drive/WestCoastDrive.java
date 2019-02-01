package frc.robot.drive;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.hardware.Controller;

public class WestCoastDrive {
    CANSparkMax leftMaster, leftFront, leftRear, rightMaster, rightFront, rightRear;
    DifferentialDrive drive;

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

        drive = new DifferentialDrive(leftMaster, rightMaster);
        drive.setMaxOutput(RobotMap.DRIVE_OUTPUT_MAX);
    }

    public void drive(Mode mode, Controller driver) {
        double speed = getSpeed(mode, driver);
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

    public int getLeftEncoderValue() {
        return (int)leftMaster.getEncoder().getPosition();
    }

    public int getRightEncoderValue() {
        return (int)rightMaster.getEncoder().getPosition();
    }

    public double getSpeed(Mode mode, Controller driver) {
        switch (mode) {
        case CURVATURE:
        case ARCADE:
        case TANK:
            return deadzone(driver.getY(RobotMap.SPEED_HAND));
        case GTA:
            return deadzone(driver.getTriggerAxis(RobotMap.RIGHT_HAND) - driver.getTriggerAxis(RobotMap.LEFT_HAND));
        default:
            // curvature drive default
            return deadzone(driver.getY(RobotMap.SPEED_HAND));
        }
    }

    public double getRotation(Mode mode, Controller driver) {
        switch (mode) {
        case CURVATURE:
        case ARCADE:
        case TANK:
        case GTA:
            return deadzone(driver.getX(RobotMap.ROT_HAND));
        default:
            // curvature drive default
            return deadzone(driver.getX(RobotMap.ROT_HAND));
        }
    }

    public double deadzone(double input) {
        return Math.abs(input) < RobotMap.DEADZONE ? 0 : input;
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
}