package frc.robot.threading;

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
    WestCoastDrive sunKist;
    Mode driveMode;
    Controller primary, secondary;

    public TeleopThread(ThreadManager threadManager, 
                        Arm arm, Hatch hatch, Intake intake, Lift lift) {
        super(threadManager);
        
        // init manipulators
        _arm = arm;
        _hatch = hatch;
        _intake = intake;
        _lift = lift;
        
        // init drivetrain (default mode is curvature)
        sunKist = new WestCoastDrive();
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
        // _hatch.extendOrClose(ControllerMap.moveHatch());

        //sunKist.drive(driveMode, primary);
    }
} 