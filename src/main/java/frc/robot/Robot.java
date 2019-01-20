
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.WestCoastDrive.Mode;

public class Robot extends TimedRobot 
{
  Joystick driver;
  WestCoastDrive driveBase;

  @Override
  public void robotInit() 
  {
    driver = new Joystick(0);
    driveBase = new WestCoastDrive(driver);
  }

  @Override
  public void robotPeriodic() 
  {
  }

  @Override
  public void autonomousInit() 
  {
  }

  @Override
  public void autonomousPeriodic() 
  {
  }

  @Override
  public void teleopPeriodic() 
  {
    driveBase.drive(Mode.CURVATURE);
  }

  @Override
  public void testPeriodic() 
  {
  }
}
