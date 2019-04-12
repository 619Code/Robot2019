package frc.robot.auto.type;

import easypath.FollowPath;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.auto.commands.hatch.*;
import frc.robot.auto.commands.drive.*;
import frc.robot.auto.commands.misc.*;
import frc.robot.auto.variables.Paths;
import frc.robot.auto.variables.Speeds;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftTwoHatch extends CommandGroup{

    public LeftTwoHatch(){
        Robot.sunKist.resetNavX();
        // GET FRONT  HATCH
        addSequential(new FollowPath(Paths.getStraightLinePath(99+10), Speeds.setSpeed(0.3)));
        addSequential(new FollowPath(Paths.LEFTFIRSTHATCH, Speeds.setSpeed(0.5)));
        Robot.sunKist.resetNavX();
        addSequential(new TeleAlign());

        // GET ANOTHER HATCH
        addSequential(new FollowPath(Paths.getStraightLinePath(5), Speeds.setSpeed(-0.5)));
        addSequential(new TurnToAngle(90, 0.005), 0.5);
        Robot.sunKist.resetNavX();
        addSequential(new FollowPath(Paths.LEFTINTAKEHATCH, Speeds.setSpeed(0.5)));
        addSequential(new TeleAlign());

        // GET IN POSITION FOR SECOND HATCH
        addSequential(new FollowPath(Paths.getStraightLinePath(5), Speeds.setSpeed(-0.5)));
        addSequential(new TurnToAngle(180, 0.005), 0.5);
        Robot.sunKist.resetNavX();
        addSequential(new FollowPath(Paths.LEFTSECONDHATCH, Speeds.setSpeed(0.5)));
    }
}