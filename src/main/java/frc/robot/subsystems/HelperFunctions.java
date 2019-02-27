package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANPIDController;

import frc.robot.maps.RobotMap;

public class HelperFunctions {
    public static void configureTalon(TalonSRX talon, RobotMap.Manipulators type) {
        talon.configFactoryDefault();
        switch (type) {
        case LIFT:
            talon.setNeutralMode(NeutralMode.Brake);
            talon.configFactoryDefault();
            talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, RobotMap.kPIDLOOPIDX,
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
        case GRABBER:
            talon.setNeutralMode(NeutralMode.Brake);
            break;
        default:
            break;
        }
    }

    public static void configureSpark(CANPIDController pidController, RobotMap.Manipulators type) {
        switch (type) {
        case LIFT:
            break;
        case ARM:
            break;
        case INTAKE:
            break;
        }
    }

    public static void configurePIDController(CANPIDController controller, RobotMap.Manipulators type){
        switch(type){
            case LIFT:
                break;
            case ARM:
                System.out.println("CONFIGED ARM");
                controller.setP(RobotMap.ARM_kP);
                controller.setI(RobotMap.ARM_kI);
                controller.setD(RobotMap.ARM_kD);
                controller.setFF(RobotMap.ARM_kF);
                controller.setIZone(RobotMap.ARM_kIZONE);
                controller.setOutputRange(RobotMap.ARM_MINOUTPUT, RobotMap.ARM_MAXOUTPUT);
                break;
            case INTAKE:
                break;
        }
    }

    public static double deadzone(double input) {
        return Math.abs(input) < RobotMap.DEADZONE ? 0 : input;
    }
}