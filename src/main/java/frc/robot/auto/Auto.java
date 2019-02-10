package frc.robot.auto;

import easypath.FollowPath;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.auto.paths.Paths;
import frc.robot.auto.paths.Speeds;

public class Auto extends CommandGroup{
    FollowPath m_autoCommand;

    public Auto(){
        addSequential(new FollowPath(Paths.TESTCURVEYPATH, Speeds.DEFAULT));
    }
}