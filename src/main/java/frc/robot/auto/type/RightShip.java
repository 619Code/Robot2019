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

public class RightShip extends CommandGroup{

    public RightShip(){
        Robot.sunKist.resetNavX(); 
        addSequential(new FollowPath(Paths.getStraightLinePath(99+10),Speeds.setSpeed(0.3)));
        addSequential(new TurnToAngle(0, 0.005), 0.5);
        addSequential(new HatchGrab(false));
        addSequential(new FollowPath(Paths.RIGHTHATCHSHIP, Speeds.setSpeed(0.6)));
    }
}
