package frc.robot.trajectories;

import java.util.ArrayList;

import frc.robot.maps.RobotMap;
import jaci.pathfinder.Waypoint;

public class Test{
    public static void main(String[] args){
        WaypointGenerator wg = new WaypointGenerator();
        ArrayList<Waypoint> waypoints = wg.generateWaypoints(RobotMap.AutoType.LINE);
        
        for(Waypoint waypoint : waypoints){
            System.out.println(waypoint.x + ", " + waypoint.y + ", " + waypoint.angle);
        }
    }
}