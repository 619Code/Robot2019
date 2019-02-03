package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
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
    Solenoid _zoop;
    WestCoastDrive _sunKist;
    private final boolean zoopDir = true;
    public Climb(WestCoastDrive sunkist){
        _sunKist = sunkist;
        _front = new WPI_TalonSRX(RobotMap.LEFT_CLIMB);
        _back = new WPI_TalonSRX(RobotMap.RIGHT_CLIMB);
        _zoop = new Solenoid(RobotMap.ZOOP_CHANNEL);
        _frontMiddle = new LimitSwitch(RobotMap.FRONT_MIDDLE_CLIMB_SWITCH);
        _frontEnd = new LimitSwitch(RobotMap.FRONT_END_CLIMB_SWITCH);
        _backMiddle = new LimitSwitch(RobotMap.BACK_MIDDLE_CLIMB_SWITCH);
        _backEnd = new LimitSwitch(RobotMap.BACK_END_CLIMB_SWITCH);
        HelperFunctions.configureTalon(_front, Manipulators.CLIMB);
        HelperFunctions.configureTalon(_back, Manipulators.CLIMB);
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
    public class up extends Command{
        public void execute() {
            while (!_frontEnd.get() || !_backEnd.get()){
                _front.set(1);
                _back.set(1);
            }
        } 
		protected boolean isFinished() {
			return false;
		} 
    }
    public class zoop extends Command{
        public void execute() {
            _zoop.set(zoopDir);
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
            _sunKist.moveDriveToTarget(1);
        } 
		protected boolean isFinished() {
			return false;
		} 
    }
    public class unZoop extends Command{
        public void execute() {
            _zoop.set(!zoopDir);
        } 
		protected boolean isFinished() {
			return false;
		} 
    }
    protected boolean isFinished() {
        return false;
    }

}

