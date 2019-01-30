package frc.robot.trajectories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import frc.robot.RobotMap;

public class WaypointGenerator{

    final String TRAJECTORY_PATH = "src/main/java/frc/robot/trajectories/";

    ArrayList<Waypoint> leftWaypoints;
    ArrayList<Waypoint> rightWaypoints;
    ArrayList<Waypoint> midWaypoints;

    public WaypointGenerator(){}

    public ArrayList<Waypoint> generateWaypoints(RobotMap.AutoType type){
        switch(type){
            case LINE:
            grabLineWaypoints();
        }
        return midWaypoints;
    }

    public void grabLineWaypoints()
    {
        String left = TRAJECTORY_PATH + "line_left.csv";
        String right = TRAJECTORY_PATH + "line_right.csv";
        
        leftWaypoints = grabCSVWaypoints(left);
        rightWaypoints = grabCSVWaypoints(right);
        midWaypoints = calcMidWaypoints();
        
    }

    public ArrayList<Waypoint> grabCSVWaypoints(String csvPath){
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))){
            while(((line = br.readLine()) != null)){
                double[] data = Arrays.stream(line.split(", ")).mapToDouble(Double::parseDouble).toArray();
                Waypoint waypoint = new Waypoint(data[0], data[1], data[2]);
                waypoints.add(waypoint);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return waypoints;
    }

    public ArrayList<Waypoint> calcMidWaypoints(){
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
        for(int i = 0; i < leftWaypoints.size(); i++){
            Waypoint left = leftWaypoints.get(i);
            Waypoint right = rightWaypoints.get(i);
            Waypoint mid = new Waypoint((left.getX()+right.getX())/2, (left.getY()+right.getY())/2, left.getHeading());
            waypoints.add(mid);
        }
        return waypoints;
    }
}