package frc.robot.hardware;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch {
	
	DigitalInput limitSwitch;
	
	public LimitSwitch(int channel){
		limitSwitch = new DigitalInput(channel);
	}
	
	public boolean get() {
        return !limitSwitch.get();
    }
	
}