package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {
    private WPI_TalonSRX leftSpinny, rightSpinny;
    private Solenoid wrist;

    // TODO: figure out which way is up and which way is down
    private final boolean up = true;

    public Intake() {
        leftSpinny = new WPI_TalonSRX(RobotMap.LEFT_INTAKE);
        rightSpinny = new WPI_TalonSRX(RobotMap.RIGHT_INTAKE);
        HelperFunctions.configureTalon(leftSpinny, RobotMap.Subsystem.INTAKE);
        HelperFunctions.configureTalon(rightSpinny, RobotMap.Subsystem.INTAKE);
        motorSetup();
        wrist = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_WRIST_CHANNEL);
    }

    private void motorSetup() {
        leftSpinny.setInverted(true);
        leftSpinny.follow(rightSpinny);
    }

    public void gather() {
        intake();
        lower();
        // maybe figure out a way to make the robot move slower when gathering?
    }

    public void stow() {
        stop();
        raise();
    }

    private void intake() {
        rightSpinny.set(RobotMap.INTAKE_SPEED);
    }

    public void outake() {
        rightSpinny.set(-RobotMap.INTAKE_SPEED);
    }

    private void stop() {
        rightSpinny.set(0);
    }

    private void raise() {
        wrist.set(up);
    }

    private void lower() {
        wrist.set(!up);
    }
}