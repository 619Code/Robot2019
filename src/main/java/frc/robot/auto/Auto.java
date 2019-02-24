package frc.robot.auto;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.auto.type.TypeManager;
import frc.robot.maps.RobotMap;

public class Auto{
    
    private DigitalInput left, right, ship, rocket;
    private TypeManager typeManager;

    public enum SwitchState{
        LEFTSHIP, RIGHTSHIP, LEFTROCKET, RIGHTROCKET, NOAUTO;
    }

    public Auto(){
        typeManager = new TypeManager();

        left = new DigitalInput(RobotMap.LEFTAUTOSWITCH);
        right = new DigitalInput(RobotMap.RIGHTAUTOSWITCH);
        ship = new DigitalInput(RobotMap.SHIPAUTOSWITCH);
        rocket = new DigitalInput(RobotMap.ROCKETAUTOSWITCH);
    }

    public void start(){
        typeManager.startAuto(getSwitchState());
    }

    public SwitchState getSwitchState(){
        if(left.get() && ship.get())
            return SwitchState.LEFTSHIP;
        else if(right.get() && ship.get())
            return SwitchState.RIGHTSHIP;
        else if(left.get() && rocket.get())
            return SwitchState.LEFTROCKET;
        else if(right.get() && rocket.get())
            return SwitchState.RIGHTROCKET;
        return SwitchState.NOAUTO;
    }
}