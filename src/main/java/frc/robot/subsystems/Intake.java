package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {
    private WPI_TalonSRX spinny;
    private Solenoid wrist;

    // TODO: figure out which way is up and which way is down
    private final boolean up = true;

    public Intake() {
        spinny = new WPI_TalonSRX(RobotMap.INTAKE);
        HelperFunctions.configureTalon(spinny, RobotMap.Subsystem.INTAKE);
        wrist = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_WRIST_CHANNEL);
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
        spinny.set(RobotMap.INTAKE_SPEED);
    }

    public void outake() {
        spinny.set(-RobotMap.INTAKE_SPEED);
    }

    private void stop() {
        spinny.set(0);
    }

    private void raise() {
        wrist.set(up);
    }

    private void lower() {
        wrist.set(!up);
    }
}