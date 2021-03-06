package frc.robot.auto.commands.hatch;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchGrab extends Command {
  boolean _dir;

  public HatchGrab(boolean dir) {
    _dir = dir;
    requires(Robot.Hatch);
  }

  @Override
  protected void initialize() {
    Robot.Hatch.grab(_dir);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
