package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class WestCoastDrive {
    CANSparkMax leftMaster, leftFront, leftRear, rightMaster, rightFront, rightRear;
    DifferentialDrive drive;
    public AHRS navX; 
    Joystick driver;

    enum Mode {
        CURVATURE, ARCADE, TANK, GTA;
    }

    public WestCoastDrive(Joystick joy) {
        driver = joy;
        initNavX();
        initDrive();
    }
    public void initNavX(){
        navX = new AHRS(Port.kMXP);
        navX.reset();
    }

    public void initDrive() {
        leftMaster = new CANSparkMax(RobotMap.LEFT_MIDDLE, MotorType.kBrushless);
        // leftFront = new CANSparkMax(RobotMap.LEFT_FRONT, MotorType.kBrushless);
        // leftRear = new CANSparkMax(RobotMap.LEFT_REAR, MotorType.kBrushless);
        rightMaster = new CANSparkMax(RobotMap.RIGHT_MIDDLE, MotorType.kBrushless);
        // rightFront = new CANSparkMax(RobotMap.RIGHT_FRONT, MotorType.kBrushless);
        //rightRear = new CANSparkMax(RobotMap.RIGHT_REAR, MotorType.kBrushless);
        leftMaster.setIdleMode(IdleMode.kCoast);
        rightMaster.setIdleMode(IdleMode.kCoast);
        // leftRear.setIdleMode(IdleMode.kCoast);
        // leftFront.setIdleMode(IdleMode.kCoast);
        // rightRear.setIdleMode(IdleMode.kCoast);
        // rightFront.setIdleMode(IdleMode.kCoast);
        leftMaster.setRampRate(RobotMap.RAMP_RATE);
        rightMaster.setRampRate(RobotMap.RAMP_RATE);
        // leftFront.follow(leftMaster);
        // leftRear.follow(leftMaster);
        // rightFront.follow(rightMaster);
        // rightRear.follow(rightMaster);

        drive = new DifferentialDrive(leftMaster, rightMaster);
        drive.setMaxOutput(RobotMap.OUTPUT_MAX);
    }

    public void drive(Mode mode) {
        double speed = getSpeed(mode);
        double rotation = getRotation(mode);
        switch (mode) {
        case CURVATURE:
            curveDrive(speed, rotation);
        case ARCADE:
            arcadeDrive(speed, rotation);
        case TANK:
            tankDrive(speed, rotation);
        case GTA:
            curveDrive(speed, rotation);
        }
    }
    public void setLeftMotors(double speed){
        leftMaster.set(speed);
    }
    public void setRightMotors(double speed){
        rightMaster.set(speed);
    }
    // public int getLeftEncoderValue() {
    //     return (int)leftMaster.getEncoder().getPosition();

    // }

    // public int getRightEncoderValue() {
    //     return (int)rightMaster.getEncoder().getPosition();

    // }
    public double getHeading(){
        return navX.getYaw();
    }

    public double getSpeed(Mode mode) {
        switch (mode) {
        case CURVATURE:
        case ARCADE:
        case TANK:
            return deadzone(driver.getRawAxis(RobotMap.SPEED_AXIS));
        case GTA:
            return deadzone(driver.getRawAxis(RobotMap.RIGHT_TRIGGER) - driver.getRawAxis(RobotMap.LEFT_TRIGGER));
        default:
            // curvature drive default
            return deadzone(driver.getRawAxis(RobotMap.SPEED_AXIS));
        }
    }

    public double getRotation(Mode mode) {
        switch (mode) {
        case CURVATURE:
        case ARCADE:
        case TANK:
            return deadzone(driver.getRawAxis(RobotMap.ROT_AXIS));
        case GTA:
            return deadzone(driver.getRawAxis(RobotMap.GTA_ROT_AXIS));
        default:
            // curvature drive default
            return deadzone(driver.getRawAxis(RobotMap.ROT_AXIS));
        }
    }


    public double deadzone(double input) {
        return Math.abs(input) < RobotMap.DEADZONE ? 0 : input;
    }

    public void curveDrive(double speed, double rotation) {
        drive.curvatureDrive(speed * RobotMap.SPEED_MAX, -rotation * RobotMap.ROT_MAX, true);
    }

    public void arcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(speed * RobotMap.SPEED_MAX, -rotation * RobotMap.ROT_MAX, true);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed * RobotMap.SPEED_MAX, -rightSpeed * RobotMap.ROT_MAX, true);
    }
}