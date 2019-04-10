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
    _sunKist = Robot.sunKist;
    requires(_sunKist);
    _targetAngle = targetAngle;
    _kP = kP;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //_currentAngle = _sunKist.getNavXAngle();
    _currentAngle = _sunKist.getGyroAngle();
    speed = (_currentAngle-_targetAngle)*_kP;
    _sunKist.setLeftMotors(speed);
    _sunKist.setRightMotors(speed);
  }

  //TODO: CREATE A BETTER OPERATOR TO KNOW WHEN IT IS FINISHED
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
