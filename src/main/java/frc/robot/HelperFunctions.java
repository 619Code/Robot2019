package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HelperFunctions {
    public static void configureTalon(WPI_TalonSRX talon, RobotMap.Subsystem type) {
        talon.configFactoryDefault();
        switch (type) {
        case LIFT:
            talon.configFactoryDefault();
            talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kPIDLOOPIDX,
                    RobotMap.kTIMEOUT_MS);
            talon.setSensorPhase(true);

            talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTIMEOUT_MS);
            talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTIMEOUT_MS);

            talon.configNominalOutputForward(0, RobotMap.kTIMEOUT_MS);
            talon.configNominalOutputReverse(0, RobotMap.kTIMEOUT_MS);
            talon.configPeakOutputForward(1, RobotMap.kTIMEOUT_MS);
            talon.configPeakOutputReverse(-1, RobotMap.kTIMEOUT_MS);

            talon.selectProfileSlot(RobotMap.kSLOTIDX, RobotMap.kPIDLOOPIDX);
            talon.config_kP(RobotMap.kSLOTIDX, RobotMap.LIFT_kP, RobotMap.kTIMEOUT_MS);
            talon.config_kI(RobotMap.kSLOTIDX, RobotMap.LIFT_kI, RobotMap.kTIMEOUT_MS);
            talon.config_kD(RobotMap.kSLOTIDX, RobotMap.LIFT_kD, RobotMap.kTIMEOUT_MS);
            talon.config_kF(RobotMap.kSLOTIDX, RobotMap.LIFT_kF, RobotMap.kTIMEOUT_MS);

            talon.configMotionCruiseVelocity(15000, RobotMap.kTIMEOUT_MS);
            talon.configMotionAcceleration(6000, RobotMap.kTIMEOUT_MS);

            talon.setSelectedSensorPosition(0, RobotMap.kPIDLOOPIDX, RobotMap.kTIMEOUT_MS);
            break;
        case ARM:
            break;
        case INTAKE:
            talon.configFactoryDefault();
            talon.setNeutralMode(NeutralMode.Brake);
            break;
        }
    }
}