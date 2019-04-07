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
        //STILL A DUPLICATE OF RIGHT TWO HATCH
        Robot.sunKist.resetNavX(); 
        addSequential(new FollowPath(Paths.RIGHTFIRSTHATCH, Speeds.setSpeed(0.3)));
        addSequential(new FollowPath(Paths.getStraightLinePath(10), Speeds.setSpeed(-0.3)));
        addSequential(new FollowPath(Paths.RIGHTINTAKEHATCH, Speeds.setSpeed(0.3)));
        addSequential(new FollowPath(Paths.getStraightLinePath(10), Speeds.setSpeed(-0.3)));
        addSequential(new FollowPath(Paths.RIGHTSECONDHATCH, Speeds.setSpeed(-0.3)));
        addSequential(new TurnToAngle(90, 2));
        addSequential(new FollowPath(Paths.getStraightLinePath(33), Speeds.setSpeed(0.3)));
    }
}
