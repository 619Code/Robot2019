/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class autoAlign extends Command {
  boolean foundFirstRectangle;
  boolean foundSecondRectangle;
  boolean rightDistance;
  boolean isFinished = false;
  double turnDir;
  double currentAngle;
  double speed;

  //TEST TURN DIRECTIONS

  public autoAlign(char direction) {
      requires(Robot.sunKist);
      if(direction == 'r') turnDir = 0.2;
      if(direction == 'l') turnDir = -0.2;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAngle = Robot.sunKist.getNavXAngle();
    if(!foundFirstRectangle){
      Robot.sunKist.setLeftandRight(turnDir, -turnDir);
      if(Robot.visionThread.getRectangles() > 0){
        foundFirstRectangle = true;
      }
      Robot.sunKist.setLeftandRight(0, 0);
    }
    if(foundFirstRectangle && !foundSecondRectangle){
      Robot.sunKist.setLeftandRight(0.3+turnDir, 0.3-turnDir);
      if(Robot.visionThread.getRectangles() > 1){
        foundSecondRectangle = true;
      }
    }
    if(foundFirstRectangle && foundSecondRectangle && !rightDistance)
    {
      speed = (currentAngle)*0.3;
      Robot.sunKist.setLeftMotors(0.3+speed);
      Robot.sunKist.setRightMotors(0.3+speed);
      if(speed < 0.05){
        isFinished = true;
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.sunKist.setLeftandRight(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
