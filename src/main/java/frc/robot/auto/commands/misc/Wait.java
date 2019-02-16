package frc.robot.auto.commands.misc;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

  double _time = 0;

  public Wait(double time) {
    _time = time;
  }

  @Override
  protected boolean isFinished() {
    Timer.delay(_time);
    return true;
  }
}
