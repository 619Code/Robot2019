package frc.robot.auto;

import com.kauailabs.navx.frc.AHRS;

import easypath.FollowPath;
import easypath.PathUtil;
import frc.robot.drive.WestCoastDrive;
import frc.robot.trajectories.WaypointGenerator;


public class Auto{
    WestCoastDrive _sunKist;
    WaypointGenerator wpGenerator;
    AHRS _navX;

    FollowPath m_autoCommand;

    public Auto(){
        m_autoCommand = new FollowPath(
            PathUtil.createStraightPath(36.0), x -> {
              if (x < 0.5) return 0.25;
              else return 0.5;
            });
    }
}