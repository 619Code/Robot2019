package frc.robot.teleop;

import frc.robot.threading.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Robot;
import frc.robot.drive.WestCoastDrive.Mode;
import frc.robot.maps.ControllerMap;
import frc.robot.maps.ControllerMap.DriveControl;
import frc.robot.maps.ControllerMap.HatchControl;
import frc.robot.maps.ControllerMap.ArmControl;
import frc.robot.subsystems.Arm;

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
        ArmControl.resetEncoderPosition();
        if(!ArmControl.isPIDOverride()) Robot.Arm.intakePosition();
        Robot.Arm.move();
        Robot.Lift.move();
        Robot.Intake.spin();
        Robot.Intake.raiseOrLower();
        HatchControl.getProcess().startProcess();
        Robot.Grabber.grab();
        DriveControl.speedUpdate();
        Robot.Climb.climb();
        
    }

    public Mode getDriveMode(){
        return driveMode;
    }
} 
