package frc.robot.threading;

public abstract class RobotThread extends Thread{
    public volatile boolean isRunning = false;
    private volatile boolean isSuspended = false;
    private int delay = 0;

    public RobotThread(int delay, ThreadManager threadManager){
        this.delay = delay;
        threadManager.addThread(this);
    }

    public RobotThread(ThreadManager threadManager){
        threadManager.addThread(this);
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void startRunning(){
        isRunning = true;
    }

    public void stopRunning(){
        this.isRunning = false;
    }

    public void setSuspended(boolean suspended){
        this.isSuspended = suspended;
    }

    public void run(){
        isRunning = true;
        begin();
        while(isRunning){
            if(isSuspended)
                continue;
            try{
                cycle();
                sleep(delay);
            } catch(Exception ex) {
                ex.printStackTrace();
                System.out.println("-----Exception from Robot Thread.-----");
            }
        }
    }

    abstract protected void cycle();

    protected void begin(){
    }

    protected void onDestroy(){
        isRunning = false;
    }

}