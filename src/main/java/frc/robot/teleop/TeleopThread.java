package frc.robot.teleop;

import frc.robot.threading.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.drive.WestCoastDrive;
import frc.robot.drive.WestCoastDrive.Mode;
import frc.robot.hardware.Controller;
import frc.robot.subsystems.*;
import frc.robot.maps.ControllerMap;

public class TeleopThread extends RobotThread {

    Arm _arm;
    Hatch _hatch;
    Intake _intake;
    Lift _lift;
    Climb _climb;
    Grabber _grabber;
    Mode driveMode;
    Controller primary, secondary;

    public TeleopThread(ThreadManager threadManager, 
                        Arm arm, Hatch hatch, Intake intake, Lift lift, Grabber grabber, Climb climb) {
        super(threadManager);
        
        // init manipulators
        _arm = arm;
        _hatch = hatch;
        _intake = intake;
        _lift = lift;
        _grabber = grabber;
        _climb = climb;

        // init drivetrain (default mode is curvature)
        driveMode = Mode.CURVATURE;

        // init controllers
        primary = new Controller(0);
        secondary = new Controller(1);

        start();
    }

    @Override
    protected void cycle() {
        // _arm.moveArmToTarget(ControllerMap.getArmPosition());
        // _lift.moveLiftToTarget(ControllerMap.getLiftPosition());
        // _intake.moveIntake(ControllerMap.spinInake());
        // _intake.raiseOrLower(ControllerMap.moveIntake());
        _hatch.grabState(ControllerMap.moveHatchGrab());
        _hatch.extendState(ControllerMap.moveHatchExtend());
        //_grabber.grab(ControllerMap.intakeGrabber() == 0 ? ControllerMap.outakeGrabber() : ControllerMap.intakeGrabber());
        // _climb.startClimb(ControllerMap.isClimbReady());
    }

    public Mode getDriveMode(){
        return driveMode;
    }
} 