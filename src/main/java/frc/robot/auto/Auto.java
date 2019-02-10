package frc.robot.auto;

import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

import easypath.FollowPath;
import easypath.PathUtil;
import frc.robot.drive.WestCoastDrive;
import frc.robot.maps.RobotMap;
import frc.robot.maps.RobotMap.AutoType;
import frc.robot.trajectories.WaypointGenerator;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

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