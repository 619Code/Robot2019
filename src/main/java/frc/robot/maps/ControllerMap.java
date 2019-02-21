package frc.robot.maps;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.hardware.Controller;
import frc.robot.maps.RobotMap.ARM_TARGETS;
import frc.robot.maps.RobotMap.LIFT_TARGETS;
import frc.robot.subsystems.HelperFunctions;

public class ControllerMap{
    /**
     * Controls
     * A = gathering mode
     * Y = hold down hatch extend and hatch grab closed, when not pressed hatch is not extended and hatch grab is open
     * left joystick = lift up/down
     * right joystick = arm up/down
     * B = intake raise or lower toggle
     * right/left bumpers = intake/outake cargo intake
     * right/left triggers = intake/outake grabber
     */
    public static Controller Primary = new Controller(0);
    public static Controller Secondary = new Controller(1);

    public static class Arm{
        //returns 0 for lowest position, 1 for second-lowest and so on (4 positions and
        // is only called when left bumper is down)
        public static ARM_TARGETS goToPosition(){
            if(Secondary.getBumper(RobotMap.LEFT_HAND)){
                switch(Secondary.getButtonPressed()){
                    case ABUTTON:
                        return RobotMap.ARM_TARGETS.BACK;
                    case BBUTTON:
                        return RobotMap.ARM_TARGETS.LOWER;
                    case YBUTTON:
                        return RobotMap.ARM_TARGETS.MIDDLE;
                    case XBUTTON:
                        return RobotMap.ARM_TARGETS.HIGH;
                }
            }
            return RobotMap.ARM_TARGETS.NULL_POSITION;
        }
    
        public static double move(){
            if(Secondary.getBumper(RobotMap.LEFT_HAND)){
                return HelperFunctions.deadzone(Secondary.getY(RobotMap.RIGHT_HAND));
            }
            return 0;
        }
    }

    public static class Climb{
        //when right bumper is grabbed start climb proccess by returning true
        // public static boolean climb(){
        //     return Secondary.getBumper(RobotMap.RIGHT_HAND);
        // }

    
        // public static boolean isClimbReady(){
        //     if (Primary.getStickButton(RobotMap.RIGHT_HAND) && Secondary.getStickButton(RobotMap.RIGHT_HAND))
        //         return true; 
        //     else 
        //         return false;
        // }
    }

    public static class Grabber{
        public static double grab(){
            double intakeSpeed = HelperFunctions.deadzone(Secondary.getTriggerAxis(RobotMap.LEFT_HAND));
            double outakeSpeed = HelperFunctions.deadzone(Secondary.getTriggerAxis(RobotMap.RIGHT_HAND));
            if(intakeSpeed > 0) return RobotMap.GRABBER_SPEED*HelperFunctions.deadzone(-Secondary.getTriggerAxis(RobotMap.LEFT_HAND));
            else if(outakeSpeed > 0) return RobotMap.GRABBER_SPEED*HelperFunctions.deadzone(Secondary.getTriggerAxis(RobotMap.RIGHT_HAND));
            return 0;
        }
    }

    public static class Hatch{
        public static double grab(){
            if(!Secondary.getBumper(Hand.kLeft)){
                return HelperFunctions.deadzone(-Secondary.getY(RobotMap.RIGHT_HAND));
            }
            return 0;
        }
    
        public static double extend(){
            if(!Secondary.getBumper(Hand.kLeft)){
                return HelperFunctions.deadzone(-Secondary.getY(RobotMap.LEFT_HAND));
            }
            return 0;
        }
    }

    public static class Intake{
        public static double spin(){
            //System.out.println(_secondary.getPOV());
            if(!Secondary.getBumper(RobotMap.LEFT_HAND)){
                if(Secondary.getPOV() == 0) return RobotMap.INTAKE_SPEED;
                if(Secondary.getPOV() == 180) return -RobotMap.INTAKE_SPEED;
            }
            return 0;
        }
    
        //grab right axis from secondary joystick
        public static double raiseOrLower(){
            if(Secondary.getBumper(Hand.kLeft)){
                return HelperFunctions.deadzone(Secondary.getY(Hand.kLeft));
            }
            return 0;
        }
    }

    public static class Lift{
         //returns 0 for lowest position, 1 for second-lowest and so on (3 positions
        // only called when left bumper is NOT down)
        public static LIFT_TARGETS goToPosition(){
            if(!Secondary.getBumper(RobotMap.LEFT_HAND)){
                switch(Secondary.getButtonPressed()){
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

        public static double move(){
            if(Secondary.getBumper(RobotMap.LEFT_HAND)){
                if(Secondary.getPOV() == 0) return -RobotMap.LIFT_SPEED;
                if(Secondary.getPOV() == 180) return RobotMap.LIFT_SPEED;
            }
            return 0;
        }
    }
}