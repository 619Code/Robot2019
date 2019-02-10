package frc.robot.auto;

import easypath.FollowPath;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.auto.commands.hatch.HatchExtend;
import frc.robot.auto.commands.hatch.HatchGrab;
import frc.robot.auto.paths.Paths;
import frc.robot.auto.paths.Speeds;

public class Auto extends CommandGroup{
    FollowPath m_autoCommand;

    public Auto(){
        addSequential(new HatchGrab(false));
        addSequential(new HatchExtend(true));
        addSequential(new FollowPath(Paths.TESTCURVEYPATH, Speeds.DEFAULT));
        addSequential(new HatchGrab(true));
        addSequential(new FollowPath(Paths.STRAIGHTLINE, Speeds.REVERSE_DEFAULT));
    }
}