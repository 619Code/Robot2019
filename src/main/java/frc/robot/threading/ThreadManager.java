package frc.robot.threading;

import java.util.Vector;

public class ThreadManager{
    private Vector threadList = new Vector();

    public void addThread(RobotThread thread){
        threadList.addElement(thread);
    }

    public void killAllThreads(){
        for(int i = 0; i < threadList.size(); i++){
            RobotThread t = (RobotThread)threadList.elementAt(i);
            if(t != null) 
                t.stopRunning();
            else
                threadList.removeElementAt(i);
        }
    }
}