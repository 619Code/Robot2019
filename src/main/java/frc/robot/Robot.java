
package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import easypath.EasyPath;
import easypath.EasyPathConfig;
import easypath.PathUtil;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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
import frc.robot.vision.VisionProcess;

public class Robot extends TimedRobot {
  public static WestCoastDrive sunKist;

  public static Arm arm;
  public static Hatch hatch;
  public static Intake intake;
  public static Lift lift;
  public static Grabber grabber;
  public static Climb climb;

  VisionProcess visionProcess;

  ThreadManager threadManager;
  TeleopThread teleopThread;
  CommandGroup auto;
  Compressor c;

  AHRS navX;

  EasyPathConfig config;

  VisionThread visionThread;

  @Override
  public void robotInit() {
    initNavX();
    initManipulators();
    initAuto();
    initVision();
    threadManager = new ThreadManager();
    threadManager.killAllThreads();
  }

  public void initManipulators() {
    sunKist = new WestCoastDrive(navX);
    
    lift = new Lift();
    // intake = new Intake();
    hatch = new Hatch();
    arm = new Arm();
    grabber = new Grabber();
    //climb = new Climb(sunKist);
    // c = new Compressor(RobotMap.PCM_CAN_ID);
    // c.setClosedLoopControl(true);
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
      sunKist::getNavXAngle,
      sunKist::initDrive, 
      RobotMap.AUTO_kP);

    config.setSwapDrivingDirection(true);
    config.setSwapTurningDirection(true);

    EasyPath.configure(config);
  }

  public void initVision(){
    visionProcess = new VisionProcess();
    new Thread(() -> {
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(320, 240);
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Vision", 320, 240);

      Mat source = new Mat();
      Mat output = new Mat();
      
      while(!Thread.interrupted()){
        cvSink.grabFrame(source);
        output = visionProcess.process(source);
        outputStream.putFrame(output);
      }

    }).start();
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    threadManager.killAllThreads();
    sunKist.initAutoDrive();
    auto = new Auto();
    auto.start();
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit(){
    threadManager.killAllThreads();
    sunKist.initTeleopDrive();
    teleopThread = new TeleopThread(threadManager, arm, hatch, intake, lift, grabber, climb);
  }

  @Override
  public void teleopPeriodic() {
    sunKist.drive(teleopThread.getDriveMode(), ControllerMap._primary);
    //System.out.println("left inches: " + sunKist.getLeftEncoderInches() + " right inches: " + sunKist.getRightEncoderInches());
  }

  // --------------------------------------

  Controller driver;
  Controller secondary;
  LimitSwitch limitSwitch;
  @Override
  public void testInit() {
   // limitSwitch = new LimitSwitch(6);
    driver = new Controller(0);
    secondary = new Controller(1);
    threadManager.killAllThreads();
    lift.moveLiftToTarget(RobotMap.LIFT_TARGETS.MIDDLE);
    //lift.moveLiftToTarget(0.5);
  }

  @Override
  public void testPeriodic() {
    System.out.println(lift.getLeftLift().getSelectedSensorPosition());
    //lift.moveLift(HelperFunctions.deadzone(driver.getY(Hand.kLeft)));
    //sunKist.setLeftMotors(driver.getY(Hand.kLeft));
    //sunKist.setRightMotors(driver.getY(Hand.kLeft));
    //sunKist.drive(WestCoastDrive.Mode.CURVATURE, driver); 
    //System.out.println(limitSwitch.get());
    //lift.moveLift(driver.getY(RobotMap.LEFT_HAND));
    //arm.moveArm(HelperFunctions.deadzone(0.3*driver.getY(RobotMap.LEFT_HAND)));
  }

  @Override
  public void disabledInit(){
    threadManager.killAllThreads();
  }
}
