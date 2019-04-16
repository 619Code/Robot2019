package frc.robot.subsystems;

import javax.lang.model.util.ElementScanner6;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.helper.PID;
import frc.robot.maps.ControllerMap;
import frc.robot.maps.RobotMap;

public class Arm extends Subsystem{

    private CANSparkMax flexin;
    private CANPIDController flexController;
    private CANEncoder flexEncoder;

    private int lastTargetIdx;
    boolean intaking;

    private PID pid;
    double cte, dt;

    double time, prevTime;

    double speedValue;

    double encoderPosition;

    public Arm() {
        flexin = new CANSparkMax(RobotMap.ARM, MotorType.kBrushless);
        flexin.setIdleMode(IdleMode.kBrake);
        flexEncoder = flexin.getEncoder();
        pid = new PID(RobotMap.ARM_kP, RobotMap.ARM_kI, RobotMap.ARM_kD);
        lastTargetIdx = 0;
        time = 0;
        prevTime = 0;
    }

    /**
     * INTAKE BALL ENC = -8.5
     * LOWER GOAL ENC = -14.7
     * MIDDLE GOAL ENC = -57
     * HIGH GOAL ENC = -116
     * CARGO SHIP ENC = -40
     */
    public void intakePosition() {
        //System.out.println("encoder " + flexEncoder.getPosition());
        intaking = ControllerMap.ArmControl.goToIntakePosition();
        if(intaking){
            encoderPosition = flexEncoder.getPosition()-ControllerMap.ArmControl.lastEncoderPosition;
            time = System.currentTimeMillis();
            cte = encoderPosition - RobotMap.ARM_TARGETS.get(0);
            dt = time - prevTime;
            pid.updateError(cte, dt);
            speedValue = -pid.totalError();
            // System.out.println("ERROR: " + speedValue + "ENCODER POS TO DESIRED: " + flexEncoder.getPosition() + " to " + RobotMap.ARM_TARGETS.get(0));
            // System.out.println("CTE: " + cte);
            if(speedValue < RobotMap.ARM_MINOUTPUT){
                speedValue = RobotMap.ARM_MINOUTPUT;
            }
            else if(speedValue > RobotMap.ARM_MAXOUTPUT){
                speedValue = RobotMap.ARM_MAXOUTPUT;
            }
            flexin.set(speedValue);
            prevTime = time;
        }  
    }

    public void move() {
        if(!intaking) flexin.set(ControllerMap.ArmControl.move());
        //System.out.println("ENCODER POS: " + flexEncoder.getPosition());
    }

    public void move(double speed) {
        flexin.set(speed);
    }

    public double getEncoderPosition(){
        return flexEncoder.getPosition();
    }
    

    @Override
    protected void initDefaultCommand() {}
}
