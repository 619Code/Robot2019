package frc.robot.maps;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import java.util.ArrayList;

public class RobotMap {

    public enum Manipulators {
        LIFT, ARM, INTAKE, HATCH, GRABBER, CLIMB;
    }

    public enum AutoType {
        LINE;
    }
    public enum LIFT_TARGETS{
        LOWER(-8.5),MIDDLE(-14.7),HIGH(-57),NULL_POSITION(-116),CARGO(-40);
        private final double rotations;
        LIFT_TARGETS(double rotations){this.rotations = rotations;}
        public double getValue() {return rotations;}

    }
    
    /**
     *Idx 0 = intake
     *Idx 1 = lower
     *Idx 2 = cargo
     *Idx 3 = middle
     *Idx 4 = high
     */
    public static final ArrayList<Double> ARM_TARGETS = new ArrayList<>(){
	    {
		    add(-10.3);
		    add(-14.7);
		    add(-40.0);
		    add(-57.0);
		    add(-116.0);
	    }
    }; 

    // Robot Dimesnions
    public static final double ROBOT_LENGTH = 35.125;
    public static final double ROBOT_WIDTH = 25.0;
    public static final double WHEEL_DIAMETER = 3.940-0.2;
    public static final double WHEEL_WIDTH = 0.0508;

    // CAN IDs
    public static final int LEFT_FRONT = 10;
    public static final int LEFT_MIDDLE = 11;
    public static final int LEFT_REAR = 12;

    public static final int RIGHT_FRONT = 13;
    public static final int RIGHT_MIDDLE = 14;
    public static final int RIGHT_REAR = 15;

    public static final int LEFT_LIFT = 20;
    public static final int RIGHT_LIFT = 21;

    public static final int INTAKE = 30;

    public static final int ARM = 40;

    public static final int FRONT_CLIMB = 50;
    public static final int BACK_CLIMB = 51;

    public static final int LEFT_GRABBER = 60;
    public static final int RIGHT_GRABBER = 61;

    // pneumatics
    public static final int PCM_CAN_ID = 1; 
    public static final int[] INTAKE_WRIST_CHANNEL = {4, 5};
    public static final int HATCH_GRABBER_CHANNEL = 1;
    public static final int[] HATCH_EXTEND_CHANNEL = {6, 7};
    public static final int FRONT_CHANNEL = 0;
    public static final int BACK_CHANNEL = 2;

    //auto switches
    public static final int LEFTAUTOSWITCH = 0;
    public static final int RIGHTAUTOSWITCH = 3;
    public static final int SHIPAUTOSWITCH = 1;
    public static final int ROCKETAUTOSWITCH = 2;

    // ramp constant (only works if in coast)
    public static final double RAMP_RATE = 0.3;
    public static final double DEADZONE = 0.05;

    // motor outputs
    public static final double DRIVE_OUTPUT_MAX = 1.0;
    public static double DRIVE_ROT_MAX = 0.3;
    public static double DRIVE_SPEED_MAX = 0.5;

    public static final double INTAKE_SPEED = 0.65;
    public static final double GRABBER_SPEED = 0.60;
    public static final double GRABBER_COUNTER = -0.3;
    public static final double LIFT_SPEED = 1.00;

    // controller public static finals
    public static final Hand SPEED_HAND = Hand.kLeft;
    public static final Hand ROT_HAND = Hand.kRight;
    public static final Hand RIGHT_HAND = Hand.kRight;
    public static final Hand LEFT_HAND = Hand.kLeft;

    // pathfinder constants
    public static final double DT = 0.05;
    public static final double MAX_VELOCITY = 1.7;
    public static final double MAX_ACCEL = 2.0;
    public static final double MAX_JERK = 60.0;
    public static final int ENCODER_TICK_PER_REV = 6;

    //Drive PID
    public static final double DRIVE_kP = 0;
	public static final double DRIVE_kI = 0;
	public static final double DRIVE_kD = 0;
    public static final int DRIVE_kIZONE = 0;
    public static final double DRIVE_KFF = 0;
    public static final double DRIVE_MAXOUTPUT = 0;
    public static final double DRIVE_MINOUTPUT = 0;

    // Auto Encoder P Value
    public static final double AUTO_kP = 0.045;

    //Lift PID
    public static final double LIFT_kP = 0.03;
	public static final double LIFT_kI = 0;
	public static final double LIFT_kD = 0;
	public static final double LIFT_kF = 0;
	public static final int LIFT_kIZONE = 0;
    public static final double LIFE_PEAK_OUTPUT = 0;
    
    //Arm PID
    public static final double ARM_kP = 0.1;
	public static final double ARM_kI = 0.0;
    public static final double ARM_kD = 7.0;
    public static final double ARM_kF = 0;
    public static final int ARM_kIZONE = 0;
    public static final double ARM_MAXOUTPUT = 0.7;
    public static final double ARM_MINOUTPUT = -0.7;

    //Climb Constants
    public static final int CLIMB_SWITCH = -1; //INITALIZE WHEN ON THE ROBOT

    //Motion Magic Constants
    public static final int kSLOTIDX = 0;
    public static final int kPIDLOOPIDX = 0;
    public static final int kTIMEOUT_MS = 30;

    //gear ratios
    public static final int RATIO_LIFT = 70;
    public static final int RATIO_ARM = 81;
    public static final int RATIO_DRIVE = 7;

    //encoder ticks per rotation
    public static final int TICKSPERROT_VERSAPLANETARY_ENC = 1024;
    public static final int TICKSPERROT_NEO_ENC = 42;

    public static final int IMG_WIDTH = 320;
	public static final int IMG_HEIGHT = 240;
}
