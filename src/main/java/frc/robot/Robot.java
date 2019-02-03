
package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.auto.AutoThread;
import frc.robot.drive.WestCoastDrive;
import frc.robot.hardware.Controller;
import frc.robot.maps.RobotMap;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.teleop.TeleopThread;
import frc.robot.auto.AutoThread;
import frc.robot.threading.ThreadManager;

public class Robot extends TimedRobot {
  Controller driver;
  WestCoastDrive sunKist;
  AutoThread auto;

  Arm arm;
  Hatch hatch;
  Intake intake;
  Lift lift;
  Climb climb;

  ThreadManager threadManager;
  TeleopThread teleopThread;
  AutoThread autoThread;

  public AHRS navX;

  @Override
  public void robotInit() {
    driver = new Controller(0);
    initNavX();
    initManipulators();
    threadManager = new ThreadManager();
    threadManager.killAllThreads();
  }

  public void initManipulators() {
    sunKist = new WestCoastDrive();
    
    lift = new Lift();
    // intake = new Intake();
    // hatch = new Hatch();
    arm = new Arm();
    climb = new Climb(sunKist);
  }

  public void initNavX() {
    navX = new AHRS(Port.kMXP);
    navX.reset();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    threadManager.killAllThreads();
    autoThread = new AutoThread(threadManager, sunKist, navX);
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit(){
    threadManager.killAllThreads();
    teleopThread = new TeleopThread(threadManager, arm, hatch, intake, lift, climb);
  }

  @Override
  public void teleopPeriodic() {
    //sunKist.drive(WestCoastDrive.Mode.CURVATURE); 
  }

  @Override
  public void testInit() {
    threadManager.killAllThreads();
    //lift.moveLiftToTarget(0.5);
  }

  @Override
  public void testPeriodic() {
    //lift.moveLift(driver.getY(RobotMap.LEFT_HAND));
    arm.moveArm(0.3*driver.getY(RobotMap.LEFT_HAND));
  }

  @Override
  public void disabledInit(){
    threadManager.killAllThreads();
  }
}
