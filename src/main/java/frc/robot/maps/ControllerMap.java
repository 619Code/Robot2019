package frc.robot.maps;

import frc.robot.hardware.Controller;

public class ControllerMap{
    Controller _primary, _secondary;

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

    //returns 0 for lowest position, 1 for second-lowest and so on (3 positions only called when left bumper is NOT down)
    public int getLiftPosition(){
        if(!_secondary.getBumper(RobotMap.LEFT_HAND)){
            switch(_secondary.getButtonPressed()){
                case ABUTTON:
                    return 0;
                case BBUTTON:
                    return 1;
                case YBUTTON:
                    return 2;
            }
        }
        return -1;
    }

    //returns 0 for lowest position, 1 for second-lowest and so on (4 positions and is only called when left bumper is down)
    public int getArmPosition(){
        if(_secondary.getBumper(RobotMap.LEFT_HAND)){
            switch(_secondary.getButtonPressed()){
                case ABUTTON:
                    return 0;
                case BBUTTON:
                    return 1;
                case YBUTTON:
                    return 2;
                case XBUTTON:
                    return 3;
            }
        }
        return -1;
    }

    //grab left axis from secondary joystick
    public double spinInake(){
        return _secondary.getY(RobotMap.LEFT_HAND);
    }

    //grab right axis from secondary joystick
    public double moveIntake(){
        return _secondary.getY(RobotMap.RIGHT_HAND);

    }

    //when right bumper is grabbed start climb proccess by returning true
    public boolean climb(){
        return _secondary.getBumper(RobotMap.RIGHT_HAND);
    }
}