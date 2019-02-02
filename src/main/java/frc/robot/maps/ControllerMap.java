package frc.robot.maps;

import frc.robot.hardware.Controller;
import frc.robot.maps.RobotMap.ARM_TARGETS;
import frc.robot.maps.RobotMap.LIFT_TARGETS;
import frc.robot.subsystems.HelperFunctions;

public class ControllerMap{
    static Controller _primary, _secondary;

    public ControllerMap(){
        _primary = new Controller(0);
        _secondary = new Controller(1);
    }

    /**
     * @return the _primary
     */
    public Controller get_primary() {
        return _primary;
    }

    /**
     * @return the _secondary
     */
    public Controller get_secondary() {
        return _secondary;
    }

    //returns 0 for lowest position, 1 for second-lowest and so on (3 positions
    // only called when left bumper is NOT down)
    public static LIFT_TARGETS getLiftPosition(){
        if(!_secondary.getBumper(RobotMap.LEFT_HAND)){
            switch(_secondary.getButtonPressed()){
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

    //returns 0 for lowest position, 1 for second-lowest and so on (4 positions and
    // is only called when left bumper is down)
    public static ARM_TARGETS getArmPosition(){
        if(_secondary.getBumper(RobotMap.LEFT_HAND)){
            switch(_secondary.getButtonPressed()){
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

    //grab left axis from secondary joystick
    public static double spinInake(){
        return HelperFunctions.deadzone(_secondary.getY(RobotMap.LEFT_HAND));
    }

    //grab right axis from secondary joystick
    public static int moveIntake(){
        if (HelperFunctions.deadzone(_secondary.getTriggerAxis(RobotMap.RIGHT_HAND))>0){
            return 1;
        }
        else if (HelperFunctions.deadzone(_secondary.getTriggerAxis(RobotMap.LEFT_HAND))>0){
            return 0;
        }
        return -1;
    }

    //when right bumper is grabbed start climb proccess by returning true
    public static boolean climb(){
        return _secondary.getBumper(RobotMap.RIGHT_HAND);
    }

    public static double moveHatch(){
        return HelperFunctions.deadzone(_secondary.getY(RobotMap.RIGHT_HAND));
    }
}