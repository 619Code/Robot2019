/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoAlign extends Command {
  boolean foundFirstRectangle = false;
  boolean foundSecondRectangle = false;
  boolean rightDistance = false;
  boolean isFinished = false;
  double turnDir;
  double currentAngle;
  double speed;

  //TEST TURN DIRECTIONS

  public AutoAlign(char direction) {
      requires(Robot.sunKist);
      if(direction == 'r') turnDir = 0.1;
      if(direction == 'l') turnDir = -0.1;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //currentAngle = Robot.sunKist.getNavXAngle();
    currentAngle = Robot.sunKist.getGyroAngle();
    if(!foundFirstRectangle){
      System.out.println("turnDir: " + turnDir + " -turnDir:" + -turnDir);
      Robot.sunKist.setLeftandRight(turnDir, -turnDir);
      System.out.println(Robot.visionThread.getRectangles());
      if(Robot.visionThread.getRectangles() > 0){
        foundFirstRectangle = true;
      }
      System.out.println("no rectangle");
      //Robot.sunKist.setLeftandRight(0, 0);
    }
    if(foundFirstRectangle && !foundSecondRectangle){
      System.out.println("found first rectangle");
      Robot.sunKist.setLeftandRight(0.1+turnDir, 0.1-turnDir);
      if(Robot.visionThread.getRectangles() > 1){
        foundSecondRectangle = true;
      }
    }
    if(foundFirstRectangle && foundSecondRectangle && !rightDistance)
    {
      System.out.println("found second rectanlge");
      speed = (currentAngle)*0.03;

      speed += 0.1;
      if(speed > 0.2){
        speed = 0.2;
      }

      Robot.sunKist.setLeftMotors(-(speed));
      Robot.sunKist.setRightMotors(speed);
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
