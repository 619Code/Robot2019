/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.drive.WestCoastDrive;

public class DriveForward extends Command {
  WestCoastDrive sunKist = Robot.sunKist;
  double currentTime;
  double startTime;
  double _seconds;
  double _speed;
  public DriveForward(double seconds, double speed) {
    requires(Robot.sunKist);
    _seconds = seconds;
    _speed = speed;
    startTime = System.currentTimeMillis();
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    currentTime = System.currentTimeMillis()-startTime;
    //System.out.println(_speed);
  }

  @Override
  protected boolean isFinished() {
    sunKist.setLeftandRight(_speed, _speed);
    if(currentTime > _seconds*1000) return true;
    return false;
  }
}
