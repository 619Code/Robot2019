package frc.robot.auto.type;

import easypath.FollowPath;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.auto.commands.hatch.*;
import frc.robot.auto.commands.drive.*;
import frc.robot.auto.commands.misc.*;
import frc.robot.auto.variables.Paths;
import frc.robot.auto.variables.Speeds;

public class LeftShip extends CommandGroup{

    public LeftShip(){
        Robot.sunKist.resetNavX(); 
        addSequential(new FollowPath(Paths.getStraightLinePath(99),Speeds.setSpeed(0.2)));
        addSequential(new TurnToAngle(0, 0.005), 2);
        addSequential(new Wait(1));
        addSequential(new HatchGrab(false));
        addSequential(new HatchExtend(Value.kForward));
        addSequential(new FollowPath(Paths.LEFTHATCHSHIP, Speeds.setSpeed(0.3)));
        addSequential(new HatchGrab(true));
        addSequential(new FollowPath(Paths.getStraightLinePath(12), Speeds.setSpeed(-0.3)));
    }
}
