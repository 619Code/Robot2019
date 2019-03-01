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
        //TODO: 
        Robot.sunKist.resetNavX(); 
        addSequential(new FollowPath(Paths.getStraightLinePath(24),Speeds.setSpeed(0.2)));
    }
}
