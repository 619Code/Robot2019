package frc.robot.helper;

public class Process{
    private Action[] _actions;

    public Process() {};

    public Process(Action[] actions){
        _actions = actions;
    }

    public void startProcess(){
        if(_actions != null){
            for(Action action : _actions){
                action.start();
            }
        }
    }
}