package frc.robot.auto.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import frc.robot.auto.Auto.SwitchState;

public class TypeManager {
    Map<SwitchState, AutoType> autoTypes = new HashMap<>();

    public TypeManager(){
        addAutoType(SwitchState.LEFTSHIP, new LeftShip());
        addAutoType(SwitchState.RIGHTSHIP, new RightShip());
        addAutoType(SwitchState.LEFTROCKET, new LeftRocket());
        addAutoType(SwitchState.RIGHTROCKET, new RightRocket());
    }

    public void addAutoType(SwitchState state, AutoType autoType){
        autoTypes.put(state, autoType);
    }

    public void startAuto(SwitchState state){
        if(!state.equals(SwitchState.NOAUTO))
            autoTypes.get(state).start();
    }
}