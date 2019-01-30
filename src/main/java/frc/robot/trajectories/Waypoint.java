package frc.robot.trajectories;

public class Waypoint{
    private double _x, _y, _heading;

    public Waypoint(double x, double y, double heading){
        _x = x;
        _y = y;
        _heading = heading;
    }

    public double getX(){
        return _x;
    }

    public double getY(){
        return _y;
    }

    public double getHeading(){
        return _heading;
    }

    public String toString()
    {
        return _x + ", " + _y + ", " + _heading;
    }
}