package frc.robot.auto.type;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.auto.Auto.SwitchState;

public class TypeManager {
    Map<SwitchState, CommandGroup> autoTypes = new HashMap<>();

    public TypeManager(){
        addAutoType(SwitchState.LEFTSHIP, new LeftShip());
        addAutoType(SwitchState.RIGHTSHIP, new RightShip());
        addAutoType(SwitchState.LEFTROCKET, new LeftRocket()); 
        addAutoType(SwitchState.RIGHTROCKET, new RightRocket());
    }

    public void addAutoType(SwitchState state, CommandGroup autoType){
        autoTypes.put(state, autoType);
    }

    public void startAuto(SwitchState state){
        if(!state.equals(SwitchState.NOAUTO))
            autoTypes.get(state).start();
    }
}