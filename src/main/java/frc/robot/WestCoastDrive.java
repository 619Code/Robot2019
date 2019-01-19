package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class WestCoastDrive {
    CANSparkMax leftMaster, leftFront, leftRear, rightMaster, rightFront, rightRear;
    DifferentialDrive drive;

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
        drive.setMaxOutput(RobotMap.OUTPUT_MAX);
    }

    public void CurveDrive(double speed, double rotation) {
        drive.curvatureDrive(speed * RobotMap.SPEED_MAX, -rotation * RobotMap.ROT_MAX, true);
    }
    public void ArcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(speed * RobotMap.SPEED_MAX, -rotation * RobotMap.ROT_MAX, true);
    }
    public void TankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed * RobotMap.SPEED_MAX, -rightSpeed * RobotMap.ROT_MAX, true);
    }
}