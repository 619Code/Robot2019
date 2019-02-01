package frc.robot.auto;

import frc.robot.RobotMap;
import frc.robot.RobotMap.AutoType;
import frc.robot.drive.WestCoastDrive;
import frc.robot.trajectories.WaypointGenerator;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;
import jaci.pathfinder.followers.EncoderFollower;

import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

public class Auto{
    Waypoint[] points;
    public Trajectory trajectory;
    Trajectory.Config config;
    EncoderFollower left;
    EncoderFollower right;
    WestCoastDrive _sunKist;
    WaypointGenerator wpGenerator;
    AHRS _navX;

    public Auto(WestCoastDrive sunKist, AHRS navX){
        _sunKist = sunKist;
        _navX = navX;
        wpGenerator = new WaypointGenerator();
        ArrayList<Waypoint> waypointList = wpGenerator.generateWaypoints(AutoType.LINE);
        points = waypointList.toArray(new Waypoint[waypointList.size()]);
        
        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
                                        RobotMap.DT, RobotMap.MAX_VELOCITY, RobotMap.MAX_ACCEL, RobotMap.MAX_JERK);

        trajectory = Pathfinder.generate(points, config);
        TankModifier modifier = new TankModifier(trajectory).modify(0.5);

        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());

        left.configureEncoder(sunKist.getLeftEncoderValue(), RobotMap.ENCODER_TICK_PER_REV, RobotMap.WHEEL_DIAMETER);
        right.configureEncoder(sunKist.getRightEncoderValue(), RobotMap.ENCODER_TICK_PER_REV, RobotMap.WHEEL_DIAMETER);

        left.configurePIDVA(RobotMap.kP, RobotMap.kI, RobotMap.kD, 1/RobotMap.MAX_VELOCITY, 0);
        right.configurePIDVA(RobotMap.kP, RobotMap.kI, RobotMap.kD, 1/RobotMap.MAX_VELOCITY, 0);
    }

    public void run(){
        double l = left.calculate(_sunKist.getLeftEncoderValue());
        double r = left.calculate(_sunKist.getRightEncoderValue());

        double gyro_heading = _navX.getFusedHeading();
        double desired_heading = Pathfinder.r2d(left.getHeading());

        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading -gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;

        _sunKist.setLeftMotors(l + turn);
        _sunKist.setRightMotors(r - turn);
    }
}