package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HelperFunctions {

    enum Subsystem {
        LIFT, ARM, INTAKE, HATCH;
    }

    public static void configureTalon(WPI_TalonSRX talon, Subsystem type) {
        talon.configFactoryDefault();
        switch (type) {
        case LIFT:
            talon.configFactoryDefault();
            talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kPIDLoopIdx,
                    RobotMap.kTimeoutMs);
            talon.setSensorPhase(true);
            talon.setInverted(false);

            talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
            talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);

            talon.configNominalOutputForward(0, RobotMap.kTimeoutMs);
            talon.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
            talon.configPeakOutputForward(1, RobotMap.kTimeoutMs);
            talon.configPeakOutputReverse(-1, RobotMap.kTimeoutMs);

            talon.selectProfileSlot(RobotMap.kSlotIdx, RobotMap.kPIDLoopIdx);
            talon.config_kF(RobotMap.kSlotIdx, RobotMap.liftkF, RobotMap.kTimeoutMs);
            talon.config_kP(RobotMap.kSlotIdx, RobotMap.liftkP, RobotMap.kTimeoutMs);
            talon.config_kI(RobotMap.kSlotIdx, RobotMap.liftkI, RobotMap.kTimeoutMs);
            talon.config_kD(RobotMap.kSlotIdx, RobotMap.liftkD, RobotMap.kTimeoutMs);

            talon.configMotionCruiseVelocity(15000, RobotMap.kTimeoutMs);
            talon.configMotionAcceleration(6000, RobotMap.kTimeoutMs);

            talon.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
            break;
        case ARM:
            break;
        case INTAKE:
            talon.configFactoryDefault();
            talon.setNeutralMode(NeutralMode.Brake);
            break;
        case HATCH:
            break;
        }
    }
}