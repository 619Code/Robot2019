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

public class RightTwoHatch extends CommandGroup{

    public RightTwoHatch(){
        Robot.sunKist.resetNavX(); 
        //GET FRONT  HATCH
        addSequential(new FollowPath(Paths.RIGHTFIRSTHATCH, Speeds.setSpeed(0.3)));
        addSequential(new HatchExtend(Value.kForward));
        addSequential(new HatchGrab(false));
        addSequential(new Wait(0.1));
        addSequential(new HatchExtend(Value.kReverse));

        //GET ANOTHER HATCH
        addSequential(new FollowPath(Paths.getStraightLinePath(10), Speeds.setSpeed(-0.3)));
        addSequential(new FollowPath(Paths.RIGHTINTAKEHATCH, Speeds.setSpeed(0.3)));
        addSequential(new HatchExtend(Value.kForward));
        addSequential(new HatchGrab(true));
        addSequential(new Wait(0.1));
        addSequential(new HatchExtend(Value.kReverse));

        //GET IN POSITION FOR SECOND HATCH
        addSequential(new FollowPath(Paths.getStraightLinePath(10), Speeds.setSpeed(-0.3)));
        addSequential(new FollowPath(Paths.RIGHTSECONDHATCH, Speeds.setSpeed(-0.3)));
        addSequential(new TurnToAngle(90, 2));

        //GET SECOND HATCH
        addSequential(new FollowPath(Paths.getStraightLinePath(33), Speeds.setSpeed(0.3)));
        addSequential(new HatchExtend(Value.kForward));
        addSequential(new HatchGrab(false));
        addSequential(new Wait(0.1));
        addSequential(new HatchExtend(Value.kReverse));
    }
}
