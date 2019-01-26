
package frc.robot;

import java.io.File;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.WestCoastDrive.Mode;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class Robot extends TimedRobot {
  Joystick driver;
  WestCoastDrive sunKist;
  Waypoint[] points = new Waypoint[] { new Waypoint(-4, -1, Pathfinder.d2r(-45)), new Waypoint(-2, -2, 0),
      new Waypoint(0, 0, 0) };
  double f = 0.0;
  Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
      RobotMap.DT, RobotMap.MAX_VELOCITY, RobotMap.MAX_ACCEL, RobotMap.MAX_JERK);
  public Trajectory trajectory = Pathfinder.generate(points, config);
  TankModifier modifier = new TankModifier(trajectory).modify(0.5);
  // EncoderFollower left;
  // EncoderFollower right;

  @Override
  public void robotInit() {
    driver = new Joystick(0);
    sunKist = new WestCoastDrive(driver);
    // left = new EncoderFollower(modifier.getLeftTrajectory());
    // right = new EncoderFollower(modifier.getRightTrajectory());
    // left.configureEncoder(sunKist.getLeftEncoderValue(),
    // RobotMap.ENCODER_TICK_PER_REV, RobotMap.WHEEL_DIAMETER);
    // right.configureEncoder(sunKist.getRightEncoderValue(),
    // RobotMap.ENCODER_TICK_PER_REV, RobotMap.WHEEL_DIAMETER);
    // left.configurePIDVA(RobotMap.kP, RobotMap.kI, RobotMap.kD,
    // 1/RobotMap.MAX_VELOCITY, 0);
    // right.configurePIDVA(RobotMap.kP, RobotMap.kI, RobotMap.kD,
    // 1/RobotMap.MAX_VELOCITY, 0);
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    // double l = left.calculate(sunKist.getLeftEncoderValue());
    // double r = left.calculate(sunKist.getRightEncoderValue());

    // double gyro_heading = sunKist.navX.getFusedHeading();
    // double desired_heading = Pathfinder.r2d(left.getHeading());

    // double angleDifference = Pathfinder.boundHalfDegrees(desired_heading -
    // gyro_heading);
    // double turn = 0.8 * (-1.0/80.0) * angleDifference;

    // sunKist.setLeftMotors(l + turn);
    // sunKist.setRightMotors(r - turn);
  }

  @Override
  public void teleopPeriodic() {
    sunKist.drive(Mode.CURVATURE);

  }

  @Override
  public void testInit() {
    File myFile = new File("trajectory.csv");
    Pathfinder.writeToCSV(myFile, trajectory);
  }

  @Override
  public void testPeriodic() {
  }
}
