/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.drive.WestCoastDrive;

public class TurnToAngle extends Command {

  private double _targetAngle;
  private double _currentAngle;
  private double speed;
  private WestCoastDrive _sunKist;
  private double _kP;

  public TurnToAngle(double targetAngle, double kP) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    _sunKist = Robot.sunKist;
    requires(_sunKist);
    _targetAngle = targetAngle;
    _kP = kP;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    _currentAngle = _sunKist.getNavXAngle();
    speed = (_currentAngle-_targetAngle)*_kP;
    _sunKist.setLeftMotors(speed);
    _sunKist.setRightMotors(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Math.round(_currentAngle) == Math.round(_targetAngle)) return true;
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
