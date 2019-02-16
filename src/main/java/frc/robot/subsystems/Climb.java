package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.maps.RobotMap;
import frc.robot.maps.RobotMap.Manipulators;
import frc.robot.drive.WestCoastDrive;
import frc.robot.hardware.LimitSwitch;

public class Climb extends CommandGroup{
    //ratio is 1:16
    WPI_TalonSRX _front, _back;
    LimitSwitch _frontMiddle,_frontEnd, _backEnd, _backMiddle;
    Solenoid _zoop, _boost;
    WestCoastDrive _sunKist;
    private final boolean ZOOP_DIR = true;
    private final boolean BOOST_DIR = true;
    boolean dir;
    public Climb(WestCoastDrive sunkist){
        _sunKist = sunkist;
        _front = new WPI_TalonSRX(RobotMap.FRONT_CLIMB);
        _back = new WPI_TalonSRX(RobotMap.BACK_CLIMB);
        _zoop = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.ZOOP_CHANNEL);
        _boost = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.BOOST_CHANNEL);
        _frontMiddle = new LimitSwitch(RobotMap.FRONT_MIDDLE_CLIMB_SWITCH);
        _frontEnd = new LimitSwitch(RobotMap.FRONT_END_CLIMB_SWITCH);
        _backMiddle = new LimitSwitch(RobotMap.BACK_MIDDLE_CLIMB_SWITCH);
        _backEnd = new LimitSwitch(RobotMap.BACK_END_CLIMB_SWITCH);

        //not configuring anything atm maybe in the future
        HelperFunctions.configureTalon(_front, Manipulators.CLIMB);
        HelperFunctions.configureTalon(_back, Manipulators.CLIMB);

        dir = false;
    }
    public void autoClimb(){
        //go up
        addSequential(new up());
        //once the end limit switches are hit zoop
        addSequential(new zoop());
        //once pneumatic is done, move up front
        addSequential(new upFront());
        //once front hits mid limit switch, drive forward a certain amount of rotations
        addSequential(new driveFoward());
        //once those rotations are achieved, move the back up
        addSequential(new upBack());
        //once the middle back limit switch is hit, drive forward
        addSequential(new driveFoward());
        //once the wheels move forwar a certain amount of rotions, zoop the climber in the inital position
        addSequential(new unZoop());
    }

    public void startClimb(boolean ready){
        if(ready) autoClimb();
    }

    public void moveBoost(){
        dir = !dir;
        _boost.set(dir);
        Timer.delay(0.3);
    }

    public void moveFront(double speed){
        //System.out.println("FRONT V: " + _front.getMotorOutputVoltage());
        if(speed <= 0 && !_frontMiddle.get())
        {
            _front.set(ControlMode.PercentOutput, speed);
            System.out.println("NOT GOING PAST FRONTMIDDLE");
        }
        else if(speed >= 0 && !_frontEnd.get())
        {
            _front.set(ControlMode.PercentOutput, speed);
            System.out.println("NOT GOING PAST FRONTEND");
        }
        else if(speed != 0){
            System.out.println("HITTING LIMIT SWITCH");
        }
    }

    public void moveBack(double speed){
        System.out.println("BACK V: " + _back.getMotorOutputVoltage());
        if(speed <= 0 && !_backMiddle.get())
        {
            _back.set(ControlMode.PercentOutput, speed);
            System.out.println("NOT GOING PAST BACKMIDDLE");
        }
        else if(speed >= 0 && !_backEnd.get())
        {
            _back.set(ControlMode.PercentOutput, speed);
            System.out.println("NOT GOING PAST BACKEND");
        }
        else if(speed != 0){
            System.out.println("HITTING LIMIT SWITCH");
        }   
    }

    public class up extends Command{
        public void execute() {
            _boost.set(BOOST_DIR);
            while (!_frontEnd.get() || !_backEnd.get()){ 
                _front.set(1);
                _back.set(1);
            }
            _boost.set(!BOOST_DIR);
        } 
		protected boolean isFinished() {
			return false;
		} 
    }

    public class zoop extends Command{
        public void execute() {
            _zoop.set(ZOOP_DIR);
        } 
		protected boolean isFinished() {
			return false;
		} 
    }

    public class upFront extends Command{
        public void execute() {
            while(!_frontMiddle.get()){
                _front.set(-1);
            }
        } 
		protected boolean isFinished() {
			return false;
		} 
    }

    public class upBack extends Command{
        public void execute() {
            while(!_backMiddle.get()){
                _back.set(-1);
            }
        } 
		protected boolean isFinished() {
			return false;
		} 
    }

    public class driveFoward extends Command{
        public void execute() {
            _sunKist.moveDriveToTarget((RobotMap.ROBOT_LENGTH/2)/(2*Math.PI*(RobotMap.WHEEL_DIAMETER/2)));
        } 
		protected boolean isFinished() {
			return false;
		} 
    }

    public class unZoop extends Command{
        public void execute() {
            _zoop.set(!ZOOP_DIR);
        } 
		protected boolean isFinished() {
			return false;
		} 
    }
}

