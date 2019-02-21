package frc.robot.teleop;

import frc.robot.threading.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Robot;
import frc.robot.drive.WestCoastDrive;
import frc.robot.drive.WestCoastDrive.Mode;
import frc.robot.hardware.Controller;
import frc.robot.subsystems.*;
import frc.robot.maps.ControllerMap;
import frc.robot.maps.RobotMap;

public class TeleopThread extends RobotThread {

    Mode driveMode;

    public TeleopThread(ThreadManager threadManager) {
        super(threadManager);

        // init drivetrain (default mode is curvature)
        driveMode = Mode.CURVATURE;

        start();
    }

    @Override
    protected void cycle() {
        Robot.Arm.moveToTarget();
        Robot.Lift.moveToTarget();
        Robot.Arm.move();
        Robot.Lift.move();
        Robot.Intake.spin();
        Robot.Intake.raiseOrLower();
        Robot.Hatch.grab();
        Robot.Hatch.extend();
        Robot.Grabber.grab();

        // _climb.startClimb(ControllerMap.isClimbReady());
    }

    public Mode getDriveMode(){
        return driveMode;
    }
} 