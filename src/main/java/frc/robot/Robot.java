
package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import easypath.EasyPath;
import easypath.EasyPathConfig;
import easypath.PathUtil;
import edu.wpi.first.wpilibj.SPI.Port;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.auto.Auto;
import frc.robot.drive.WestCoastDrive;
import frc.robot.hardware.Controller;
import frc.robot.hardware.LimitSwitch;
import frc.robot.maps.ControllerMap;
import frc.robot.maps.RobotMap;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.HelperFunctions;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.teleop.TeleopThread;
import frc.robot.threading.ThreadManager;
import frc.robot.vision.GRIPVision;

public class Robot extends TimedRobot {
  WestCoastDrive sunKist;

  Arm arm;
  Hatch hatch;
  Intake intake;
  Lift lift;
  Grabber grabber;
  Climb climb;

  ThreadManager threadManager;
  TeleopThread teleopThread;
  Auto auto;
  Compressor c;

  AHRS navX;

  EasyPathConfig config;

  VisionThread visionThread;

  @Override
  public void robotInit() {
    initNavX();
    initManipulators();
    initAuto();
    threadManager = new ThreadManager();
    threadManager.killAllThreads();
  }

  public void initVision(){
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(RobotMap.IMG_WIDTH, RobotMap.IMG_HEIGHT);
    Object imgLock = new Object();
    visionThread = new VisionThread(camera, new GRIPVision(), pipeline -> {
        if (!pipeline.cvCannyOutput().empty()) {
            Rect r = Imgproc.boundingRect(pipeline.cvCannyOutput());
            synchronized (imgLock) {
                double centerX = r.x + (r.width / 2);
            }
        }
    });
    visionThread.start();
  }

  public void initManipulators() {
    sunKist = new WestCoastDrive();
    
    lift = new Lift();
    // intake = new Intake();
    hatch = new Hatch();
    arm = new Arm();
    grabber = new Grabber();
    //climb = new Climb(sunKist);
    c = new Compressor(RobotMap.PCM_CAN_ID);
    c.setClosedLoopControl(true);
  }

  public void initNavX() {
    navX = new AHRS(Port.kMXP);
    navX.reset();
  }

  public void initAuto(){
    config = new EasyPathConfig(
      sunKist, 
      sunKist::setLeftandRight, 
      () -> PathUtil.defaultLengthDrivenEstimator(sunKist::getLeftEncoderInches, sunKist::getRightEncoderInches),  
      navX::getAngle,
      sunKist::initDrive, 
      0.07);

    EasyPath.configure(config);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    threadManager.killAllThreads();
    auto = new Auto();
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit(){
    threadManager.killAllThreads();
    teleopThread = new TeleopThread(threadManager, arm, hatch, intake, lift, grabber, climb);
  }

  @Override
  public void teleopPeriodic() {
    sunKist.drive(teleopThread.getDriveMode(), ControllerMap._primary);
  }

  Controller driver;
  Controller secondary;
  LimitSwitch limitSwitch;
  @Override
  public void testInit() {
   // limitSwitch = new LimitSwitch(6);
    driver = new Controller(0);
    secondary = new Controller(1);
    threadManager.killAllThreads();
    //lift.moveLiftToTarget(0.5);
  }

  @Override
  public void testPeriodic() {
    //sunKist.drive(WestCoastDrive.Mode.CURVATURE, driver); 
    //System.out.println(limitSwitch.get());
    //lift.moveLift(driver.getY(RobotMap.LEFT_HAND));
    arm.moveArm(HelperFunctions.deadzone(0.3*driver.getY(RobotMap.LEFT_HAND)));
  }

  @Override
  public void disabledInit(){
    threadManager.killAllThreads();
  }
}
