package frc.robot.auto;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.maps.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;
import java.util.Map;
import java.util.HashMap;

import frc.robot.auto.type.*;

public class Auto{
    
    private DigitalInput left, right, ship, rocket;
    private Map<SwitchState, CommandGroup> autoTypes = new HashMap<>();
    private CommandGroup chosenAuto;

    public enum SwitchState{
        LEFTSHIP, RIGHTSHIP, LEFTROCKET, RIGHTROCKET, LEFTSIDE, RIGHTSIDE, RIGHTTWOHATCH, LEFTTWOHATCH, NOAUTO;
    }

    public Auto(){
	    addAutoTypes();
        left = new DigitalInput(RobotMap.LEFTAUTOSWITCH);
        right = new DigitalInput(RobotMap.RIGHTAUTOSWITCH);
        ship = new DigitalInput(RobotMap.SHIPAUTOSWITCH);
        rocket = new DigitalInput(RobotMap.ROCKETAUTOSWITCH);
    }

    public void addAutoTypes(){
    	autoTypes.put(SwitchState.LEFTSHIP, new LeftShip());
	    autoTypes.put(SwitchState.RIGHTSHIP, new RightShip());
	    autoTypes.put(SwitchState.LEFTROCKET, new LeftRocket());
        autoTypes.put(SwitchState.RIGHTROCKET, new RightRocket());
        autoTypes.put(SwitchState.LEFTSIDE, new LeftSide());
        autoTypes.put(SwitchState.RIGHTSIDE, new RightSide());
        autoTypes.put(SwitchState.RIGHTTWOHATCH, new RightTwoHatch());
        autoTypes.put(SwitchState.LEFTTWOHATCH, new LeftTwoHatch());
    }

    public void start(){
        SwitchState state = getSwitchState();
	    if(!state.equals(SwitchState.NOAUTO))
	        chosenAuto = autoTypes.get(state);
	    else
    	    chosenAuto = new CommandGroup();
	    chosenAuto.start();
    }	

    public void stop(){
        chosenAuto.cancel();
    }

    public SwitchState getSwitchState(){
        //return SwitchState.LEFTSHIP;
        if(left.get() && ship.get() && rocket.get())
            return SwitchState.LEFTSIDE;
        else if(right.get() && ship.get() && rocket.get())
            return SwitchState.RIGHTSIDE;
        else if(left.get() && ship.get())
            return SwitchState.LEFTSHIP;
        else if(right.get() && ship.get())
            return SwitchState.RIGHTSHIP;
        else if(left.get() && rocket.get())
            return SwitchState.LEFTROCKET;
        else if(right.get() && rocket.get())
            return SwitchState.RIGHTROCKET;
        else if(right.get())
            return SwitchState.RIGHTTWOHATCH;
        else if(left.get())
            return SwitchState.LEFTTWOHATCH;
        return SwitchState.NOAUTO;
    }
}
