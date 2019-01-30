
package frc.robot;

import java.io.File;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.drive.WestCoastDrive;
import frc.robot.auto.Auto;

public class Robot extends TimedRobot {
  Joystick driver;
  WestCoastDrive sunKist;
  Auto auto;

  @Override
  public void robotInit() {
    driver = new Joystick(0);
    sunKist = new WestCoastDrive(driver);
    auto = new Auto(sunKist);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    auto.run();
  }

  @Override
  public void teleopPeriodic() {
    sunKist.drive(WestCoastDrive.Mode.CURVATURE);  
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }
}
