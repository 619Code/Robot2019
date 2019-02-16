package frc.robot.auto.commands.hatch;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchExtend extends Command {
  boolean _dir;

  public HatchExtend(boolean dir) {
    _dir = dir;
    requires(Robot.hatch);
  }

  @Override
  protected void initialize() {
    Robot.hatch.extend(_dir);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
