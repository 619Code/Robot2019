package frc.robot.maps;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Robot;
import frc.robot.hardware.Controller;
import frc.robot.helper.Action;
import frc.robot.helper.Process;
import frc.robot.maps.RobotMap.ARM_TARGETS;
import frc.robot.maps.RobotMap.LIFT_TARGETS;
import frc.robot.subsystems.HelperFunctions;

public class ControllerMap {

    public static Controller Primary = new Controller(0);
    public static Controller Secondary = new Controller(1);

    private static final Process GATHERHATCH = new Process(new Action[] { new Action() {
        public void start() {
            Robot.Hatch.extend(Value.kForward);
        }
    }, new Action() {
        public void start() {
            Robot.Hatch.grab(true);
        }
    } });

    private static final Process GRABHATCH = new Process(new Action[] { new Action() {
        public void start() {
            Robot.Hatch.extend(Value.kReverse);
        }
    }, new Action() {
        public void start() {
            Robot.Hatch.grab(false);
        }
    } });

    private static final Process ALIGNHATCH = new Process(new Action[] { new Action() {
        public void start() {
            Robot.Hatch.extend(Value.kForward);
        }
    }, new Action() {
        public void start() {
            Robot.Hatch.grab(false);
        }
    } });

    private static final Process DEPLOYHATCH = new Process(new Action[] { new Action() {
        public void start() {
            Robot.Hatch.extend(Value.kReverse);
        }
    }, new Action() {
        public void start() {
            Robot.Hatch.grab(true);
        }
    } });

    public static class ArmControl {
        // returns 0 for lowest position, 1 for second-lowest and so on (4 positions and
        // is only called when left bumper is down)
        public static ARM_TARGETS goToPosition() {
            switch(Secondary.getPOV()){
                case 0:
                    return ARM_TARGETS.HIGH;
                case 90:
                    return ARM_TARGETS.MIDDLE;
                case 180:
                    return ARM_TARGETS.LOWER;
                case 270:
                    return ARM_TARGETS.MIDDLE;
                case -1:
                    return ARM_TARGETS.NULL_POSITION;
            }
            return ARM_TARGETS.NULL_POSITION;
        }

        public static double move() {
            return HelperFunctions.deadzone(Secondary.getY(Hand.kRight));
        }
    }

    public static class ClimbControl {
        static boolean pressed = false;
        static boolean state = false;

        public static boolean climb() {
            if (Secondary.getStartButton() && !pressed) {
                pressed = true;
                state = !state;
            } else if (!Secondary.getStartButton() && pressed) {
                pressed = false;
            }
            return state;
        }
    }

    public static class GrabberControl {
        public static double grab() {
            double intakeSpeed = RobotMap.GRABBER_SPEED
                    * HelperFunctions.deadzone(Secondary.getTriggerAxis(RobotMap.LEFT_HAND));
            double outakeSpeed = RobotMap.GRABBER_SPEED
                    * HelperFunctions.deadzone(Secondary.getTriggerAxis(RobotMap.RIGHT_HAND));
            return intakeSpeed > 0 ? intakeSpeed : outakeSpeed;
        }
    }

    public static class HatchControl {
        public static Process getProcess() {
            if (Secondary.getAButton())
                return GATHERHATCH;
            if (Secondary.getBButton())
                return GRABHATCH;
            if (Secondary.getYButton())
                return ALIGNHATCH;
            if (Secondary.getXButton())
                return DEPLOYHATCH;
            return new Process();
        }
    }

    public static class IntakeControl {
        public static double spin() {
            if (Secondary.getBumper(Hand.kLeft))
                return RobotMap.INTAKE_SPEED;
            if (Secondary.getBumper(Hand.kRight))
                return -RobotMap.INTAKE_SPEED;
            return 0;
        }

        // grab right axis from secondary joystick
        public static int raiseOrLower() {
            return Secondary.getPOV();
        }
    }

    public static class LiftControl {
        // returns 0 for lowest position, 1 for second-lowest and so on (3 positions
        // only called when left bumper is NOT down)
        public static LIFT_TARGETS goToPosition() {
            if (!Secondary.getBumper(RobotMap.LEFT_HAND)) {
                switch (Secondary.getButtonPressed()) {
                case ABUTTON:
                    return RobotMap.LIFT_TARGETS.LOWER;
                case BBUTTON:
                    return RobotMap.LIFT_TARGETS.MIDDLE;
                case YBUTTON:
                    return RobotMap.LIFT_TARGETS.HIGH;
                }
            }
            return RobotMap.LIFT_TARGETS.NULL_POSITION;
        }

        public static double move() {
            return HelperFunctions.deadzone(Secondary.getY(Hand.kLeft));
        }
    }

    public static class DriveControl {
        public static void speedUpdate() {
            if (Primary.getTriggerAxis(Hand.kLeft) > 0) {
                RobotMap.DRIVE_SPEED_MAX = 0.2;
                RobotMap.DRIVE_ROT_MAX = 0.2;
                // } else if (Primary.getTriggerAxis(Hand.kRight) > 0) {
                // RobotMap.DRIVE_SPEED_MAX = 1.0;
                // RobotMap.DRIVE_ROT_MAX = 0.5;
            } else {
                RobotMap.DRIVE_SPEED_MAX = 0.2;
                RobotMap.DRIVE_ROT_MAX = 0.2;
            }
        }
    }
}