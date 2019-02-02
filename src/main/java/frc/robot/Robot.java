
package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.auto.Auto;
import frc.robot.drive.WestCoastDrive;
import frc.robot.hardware.Controller;
import frc.robot.maps.RobotMap;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.threading.TeleopThread;
import frc.robot.threading.ThreadManager;

public class Robot extends TimedRobot {
  Controller driver;
  WestCoastDrive sunKist;
  Auto auto;

  Arm arm;
  Hatch hatch;
  Intake intake;
  Lift lift;

  ThreadManager threadManager;
  TeleopThread teleopThread;

  public AHRS navX;

  @Override
  public void robotInit() {
    driver = new Controller(0);
    initNavX();
    initManipulators();
    threadManager = new ThreadManager();
    threadManager.killAllThreads();
    //auto = new Auto(sunKist, navX);
  }

  public void initManipulators() {
    sunKist = new WestCoastDrive();
    
    lift = new Lift();
    // intake = new Intake();
    // hatch = new Hatch();
    arm = new Arm();
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
  }

  @Override
  public void autonomousPeriodic() {
    auto.run();
  }

  @Override
  public void teleopInit(){
    threadManager.killAllThreads();
    teleopThread = new TeleopThread(threadManager, arm, hatch, intake, lift);
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
