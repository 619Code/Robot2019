
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  Joystick driver;
  WestCoastDrive drive;

  @Override
  public void robotInit() {
    driver = new Joystick(0);
    drive = new WestCoastDrive();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  public double deadzone(double input) {
    return (Math.abs(input) < RobotMap.DEADZONE) ? 0 : input;
  }

  public double getSpeed(int mode) {
    // TODO: Create modes
    double speed = 0;
    switch (mode) {
    case 1:
    }

    return speed;
  }

  public double getRotation() {
    return deadzone(driver.getRawAxis(4));
  }

  @Override
  public void teleopPeriodic() {
    double speed = getSpeed(4); // Mode 1: arcade, Mode 2: curvature, Mode 3: Tank, Mode: 4 GTA drive
    double rotation = getRotation();
    drive.CurveDrive(speed, rotation);
  }

  @Override
  public void testPeriodic() {
  }
}
