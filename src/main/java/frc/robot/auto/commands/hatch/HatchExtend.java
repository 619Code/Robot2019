package frc.robot.auto.commands.hatch;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchExtend extends Command {
  Value _dir;

  public HatchExtend(Value dir) {
    _dir = dir;
    requires(Robot.Hatch);
  }

  @Override
  protected void initialize() {
    Robot.Hatch.extend(_dir);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
