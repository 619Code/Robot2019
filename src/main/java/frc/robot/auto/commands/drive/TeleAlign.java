/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.maps.ControllerMap;

public class TeleAlign extends Command {
  public TeleAlign() {
  }

  @Override
  protected void initialize() {
    Robot.sunKist.setDriveOverride(true);
  }

  @Override
  protected boolean isFinished() {
    return ControllerMap.Primary.getAButton();
  }

  @Override
  protected void execute() {
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.sunKist.setDriveOverride(false);
  }
}